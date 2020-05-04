import com.intellij.database.model.DasColumn
import com.intellij.database.model.DasTable
import com.intellij.database.util.Case
import com.intellij.database.util.DasUtil

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

FILES.chooseDirectoryAndSave("Choose directory", "Choose where to store generated files") { dir ->
    String dateTime = DateTimeFormatter.ISO_DATE_TIME.format(LocalDateTime.now())
    StringBuilder dbml = new StringBuilder()
    List<Map<String, String>> relationColumns = new ArrayList<>()
    List<String> tableNames = new ArrayList<>()

    SELECTION.filter { it instanceof DasTable }.each { generateTableDBML(it, dbml, relationColumns, tableNames) }

    generateTableRelationDBML(tableNames, relationColumns, dbml)

    def file = new File(dir, dateTime + ".dbml")
    def fileWriter = new FileWriter(file, true)

    fileWriter.write(dbml.toString())
    fileWriter.flush()
}

def generateTableDBML(DasTable table, StringBuilder dbml, List<Map<String, String>> relationColumns, List<String> tableNames) {
    tableNames.add(table.name)
    dbml.append("Table ${table.getName()} {\n")
    DasUtil.getColumns(table).each() { DasColumn column ->
        appendColumnNameAndType(column, dbml)
        appendAttributeAndComment(column, dbml)
        addRelationColumn(column, relationColumns)
    }
    if (table.getComment() != null && !table.getComment().isBlank()) {
        dbml.append("\n  Note: '${table.getComment()}'\n")
    }
    dbml.append("}\n\n")
}

private void appendAttributeAndComment(DasColumn column, StringBuilder dbml) {
    def attributes = getAttributeCount(column)

    if (attributes.size() > 0 || column.comment != null) {
        dbml.append(" [")
    }

    if (attributes.size() > 0) {
        attributes.each {
            switch (it) {
                case DasColumn.Attribute.PRIMARY_KEY:
                    dbml.append("pk")
                    if (attributes.size() > 1) {
                        dbml.append(", ")
                    }
                    break
                case DasColumn.Attribute.AUTO_GENERATED:
                    dbml.append("increment")
                    break
                default:
                    break
            }
        }
    }

    if (column.comment != null) {
        if (attributes.size() > 0) {
            dbml.append(",")
        }
        dbml.append(" Note: '${column.comment}'")
    }
    if (attributes.size() > 0 || column.comment != null) {
        dbml.append("]")
    }
    dbml.append("\n")
}

private void appendColumnNameAndType(DasColumn column, StringBuilder dbml) {
    dbml.append("  ${column.name} ${Case.LOWER.apply(column.getDataType().getSpecification())}")
}


List<DasColumn.Attribute> getAttributeCount(DasColumn column) {
    def attributes = [DasColumn.Attribute.PRIMARY_KEY, DasColumn.Attribute.AUTO_GENERATED]
    def columnAttributes = new ArrayList<DasColumn.Attribute>()
    attributes.each { it ->
        if (column.getTable().getColumnAttrs(column).contains(it)) {
            columnAttributes.add(it)
        }
    }
    return columnAttributes
}

void addRelationColumn(DasColumn dasColumn, List<Map<String, String>> relationColumns) {
    if (dasColumn.name.endsWith("_no")) {
        def map = new HashMap<String, String>()
        map.put("tableName", dasColumn.tableName)
        map.put("key", dasColumn.name)
        relationColumns.add(map)
    }
}

void generateTableRelationDBML(List<String> tableNames, ArrayList<Map<String, String>> relationColumns, StringBuilder dbml) {
    relationColumns.each { columnMap ->
        tableNames.each { tableName ->
            if (tableName.endsWith(columnMap.get("key").substring(0, columnMap.get("key").indexOf("_no")))) {
                dbml.append("Ref: ${tableName}.no < ${columnMap.get("tableName")}.${columnMap.get("key")}\n")
            }
        }
    }
}
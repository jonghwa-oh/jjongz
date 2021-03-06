import com.intellij.database.model.DasTable
import com.intellij.database.util.Case
import com.intellij.database.util.DasUtil

typeMapping = [
        (~/(?i)tinyint\(1\)/)             : "Boolean",
        (~/(?i)tinyint/)                  : "Int",
        (~/(?i)bigint/)                   : "Long",
        (~/(?i)int/)                      : "Int",
        (~/(?i)float|double|decimal|real/): "Double",
        (~/(?i)datetime|timestamp/)       : "LocalDateTime",
        (~/(?i)date/)                     : "LocalDate",
        (~/(?i)time/)                     : "LocalTime",
        (~/(?i)/)                         : "String",
        (~/(?i)enum/)                     : "enum",
]

FILES.chooseDirectoryAndSave("Choose directory", "Choose where to store generated files") { dir ->
    SELECTION.filter { it instanceof DasTable }.each { generate(it, dir) }
}

def generate(table, dir) {
    def tableName = table.getName()
    def fields = calcFields(table)
    new File(dir, tableName + ".dbml").withPrintWriter { out -> generate(out, tableName, fields) }
}

def generate(out, tableName, fields) {
    out.println "Table $tableName {"
    fields.each() {
        out.print "  ${it.colName} ${it.orgType}"
        if (it.comment != null) {
            out.println "[ Note: '${it.comment}']"
        }
    }
    out.println "}"
}

def calcFields(table) {
    DasUtil.getColumns(table).reduce([]) { fields, col ->
        def spec = Case.LOWER.apply(col.getDataType().getSpecification())
        def typeStr = typeMapping.find { p, t -> p.matcher(spec).find() }.value
        fields += [[
                           colName     : col.getName(),
                           type        : typeStr,
                           orgType     : spec,
                           nullable    : !col.isNotNull(),
                           isPrimary   : DasUtil.isPrimary(col),
                           isAutoInc   : DasUtil.isAutoGenerated(col),
                           isForeignKey: DasUtil.isForeign(col),
                           isIndex     : DasUtil.isIndexColumn(col),
                           comment     : col.getComment(),
                           annos       : ""]]
    }
}

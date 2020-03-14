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
    def className = javaName(table.getName(), true)
    def fields = calcFields(table)
    def path = dir.path.replace('/', '.')
    def packageName = path.substring(path.indexOf(".com") + 1)
    new File(dir, className + "Repository.kt").withPrintWriter { out -> generate(out, packageName, tableName, className, fields) }
}

def generate(out, packageName, tableName, className, fields) {
    out.println "package $packageName"
    out.println ""
    out.println ""
    out.println "import com.howbuild.backend.domain.rds.entity.$className"
    out.println "import org.springframework.data.jpa.repository.JpaRepository"
    out.println ""
    out.println "interface ${className}Repository: JpaRepository<$className, Long> {"
    fields.each() {
        if (it.primary || it.isAutoInc) {
            out.println ""
            out.println "   fun findBy${it.name}(no: ${it.type}): $className"
            out.println ""
        } else if ( it.isIndex ) {
            out.println ""
            out.println "   fun findBy${it.name}(no: ${it.type}): $className"
            out.println ""
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
                           primary     : DasUtil.isPrimary(col),
                           isAutoInc   : DasUtil.isAutoGenerated(col),
                           isForeignKey: DasUtil.isForeign(col),
                           isIndex     : DasUtil.isIndexColumn(col),
                           annos       : ""]]
    }
}

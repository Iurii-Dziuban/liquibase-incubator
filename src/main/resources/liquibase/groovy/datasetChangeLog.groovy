package liquibase.groovy

// groovy dsl changelog example with writing to file, invoking java/groovy code

import org.liquibase.samples.CustomLogger

databaseChangeLog {
    def mainAuthor = databaseChangeLog.changeLogParameters.getValue("mainAuthor", databaseChangeLog)
    changeSet(author: "${mainAuthor}", id: "001", context:"test, production") {
        new CustomLogger().log("Testing")
        new File('target/data.csv').withPrintWriter { pw ->
            pw.println "name,role"
            pw.println "Iurii,owner"
            pw.println "vixentael,contributer"
            pw.println "lampapos,contributer"
        }
        createTable(tableName: "transactions") {
            column(name: "id", type: "INT") {
                constraints(nullable: "false")
            }
            column(name: "name", type: "varchar(50)") {
                constraints(nullable: "false")
            }
        }
        rollback {
            dropTable(tableName:"transactions")
        }
    }

    changeSet(author: "Misha", id: "002", context:"test, production", failOnError: "true", runInTransaction: "true") {
        sql{
            """ALTER TABLE transactions ADD COLUMN date VARCHAR(20);"""
        }
        rollback {
            """ALTER TABLE transactions ADD COLUMN date VARCHAR(50);"""
        }
    }

    changeSet (id:"tag-1.0", context: "test, production", author:"Iurii") {
        tagDatabase(tag: "1.0")
    }

    include(file:"liquibase/xml/db.changelog-included.xml")
}
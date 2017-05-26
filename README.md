# liquibase-incubator
[![Open Source Love](https://badges.frapsoft.com/os/v2/open-source.svg?v=103)](https://github.com/ellerbrock/open-source-badge/)    

[![Build Status](https://travis-ci.org/Iurii-Dziuban/liquibase-incubator.svg?branch=master)](https://travis-ci.org/Iurii-Dziuban/liquibase-incubator)
[![Coverage Status](https://coveralls.io/repos/github/Iurii-Dziuban/liquibase-incubator/badge.svg?branch=master)](https://coveralls.io/github/Iurii-Dziuban/liquibase-incubator?branch=master)
[![Dependency Status](https://www.versioneye.com/user/projects/57b8ae91fc18270041a9aa9c/badge.svg?style=flat-square)](https://www.versioneye.com/user/projects/57b8ae91fc18270041a9aa9c)
[![contributions welcome](https://img.shields.io/badge/contributions-welcome-brightgreen.svg?style=flat)](https://github.com/Iurii-Dziuban/liquibase-incubator/issues)

A project with demo of liquibase capabilities with spring framework based on http://www.liquibase.org/documentation/spring.html

H2 database is used in demo org.liquibase.samples because of ease of use

# Checks

Jacoco code coverage, pmd, checkstyle, enforcer, findbugs

# Project structure
Project structure is simple. Basically it is a maven project:
- Samples under `src/test/java/org.liquibase.org.samples`

Resources
- DB scripts with test tables and data `src/main/resources/db-scripts`
- Spring application contexts used in the org.liquibase.samples under `src/main/resources/liquibase`
- Liquibase properties under `src/main/resources/liquibase` used by changelog `groovy/datasetChangeLog.groovy` and `xml/datasetChangeLog.xml` additional changelog file `db.changelog-included.xml`
- Log4j configuration under `src/main/resources/log4j.xml`

# Pom.xml
Libraries:
- liquibase
- liquibase groovy dsl
- groovy
- spring framework
- spring jdbc for spring jdbc migrations
- commons-dbcp2 for database connection pool
- h2 file based database for ease of db usage
- log4j logging (possibility to configure) via slf4j

# Building project
`mvn clean package`

# Demo & Features

- `XmlDslLiquibaseSpringTest` based on xml implementation as default and declarative.
- `GroovyDslLiquibaseSpringTest` based on groovy dsl implementation with power to include groovy code
No parameters required. Just run the main method in the class

`Comments in the files and logging should be sufficient to play with org.liquibase.samples to see results and experiment with sql migrations`

## Features covered
- Liquibase maven plugin usage
  - `mvn liquibase:update` to run migrations
  - `mvn liquibase:generateChangeLog` to generate migration file based on existing db
- XML based example with xml power and Groovy DSL example with groovy power
- SpringLiquibase class integration to spring
- Configuration of parameters in datasetChangelog files via properties
- Pure sql and database independent migrations
- Usage of contexts to be executed on specific environments
- Usage of preconditions to make checks before migration execution
- Usage of including other change set files into configuration
- Usage of runOnChange runInTransaction for particular change
- Usage of tags
- Usage of default rollback & rollback file generation
- Meta tables databasechangelog and databasechangeloglock creation
- Log4j integration

# liquibase-incubator
[![Build Status](https://travis-ci.org/Iurii-Dziuban/liquibase-incubator.svg?branch=master)](https://travis-ci.org/Iurii-Dziuban/liquibase-incubator)

A project with demo of liquibase capabilities with spring framework based on http://www.liquibase.org/documentation/spring.html

H2 database is used in demo samples because of ease of use

# Project structure
Project structure is simple. Basically it is a maven project:
- Samples under `src/main/java/org.liquibase.samples`

Resources
- DB scripts with test tables and data `src/main/resources/db-scripts`
- Spring application contexts used in the samples under `src/main/resources/liquibase`
- Liquibase properties under `src/main/resources/liquibase` used by changelog `datasetChangeLog.xml` and additional changelog file db.changelog-included.xml
- Log4j configuration under `src/main/resources/log4j.xml`

# Pom.xml
Libraries: liquibase, spring framework, spring jdbc for spring jdbc migrations, commons-dbcp2 for database connection pool, h2 file based database for ease of db usage, log4j logging (possibility to configure) via slf4j.

# Building project
`mvn clean install`

# Running samples
Samples are located under `src/main/java/org.liquibase.samples` directly

- LiquibaseSpringExample

No parameters required. Just run the main method in the class

`Comments in the files and logging should be sufficient to play with samples to see results and experiment with sql migrations`

# Features
- Liquibase maven plugin usage
  - `mvn liquibase:update` to run migrations
  - `mvn liquibase:generateChangeLog` to generate migration file based on existing db
- SpringLiquibase class integration to spring
- Configuration of parameters in datasetChangelog files via properties
- Pure sql and database independent migrations
- Usage of contexts to be executed on specific environments
- Usage of preconditions to make checks before migration execution
- Usage of including other change set files into configuration
- Usage of runOnChange runInTransaction for particular change
- Log4j integration

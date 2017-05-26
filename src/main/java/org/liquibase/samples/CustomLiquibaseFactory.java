package org.liquibase.samples;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.ApplicationContext;

import java.sql.Connection;

/**
 * Factory to create pure Liquibase instance not just SpringLiquibase
 * Created by iurii.dziuban on 24.08.2016.
 */
public class CustomLiquibaseFactory {

    public Liquibase createLiquibase(Connection c, ApplicationContext context, String changeLogFilename) throws LiquibaseException {
        SpringLiquibase springLiquibase = new SpringLiquibase();
        springLiquibase.setResourceLoader(context);
        SpringLiquibase.SpringResourceOpener resourceAccessor = springLiquibase.new SpringResourceOpener(changeLogFilename);
        Liquibase liquibase = new Liquibase(changeLogFilename, resourceAccessor, createDatabase(c));
        liquibase.setIgnoreClasspathPrefix(true);
        return liquibase;
    }

    private Database createDatabase(Connection c) throws DatabaseException {
        return DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(c));
    }
}

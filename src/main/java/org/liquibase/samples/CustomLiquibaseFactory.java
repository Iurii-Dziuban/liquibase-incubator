package org.liquibase.samples;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseConnection;
import liquibase.database.DatabaseFactory;
import liquibase.database.OfflineConnection;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;
import liquibase.resource.ResourceAccessor;
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
        Liquibase liquibase = new Liquibase(changeLogFilename, resourceAccessor, createDatabase(c, resourceAccessor));
        liquibase.setIgnoreClasspathPrefix(true);
        return liquibase;
    }

    private Database createDatabase(Connection c, ResourceAccessor resourceAccessor) throws DatabaseException {
        DatabaseConnection liquibaseConnection;
        if (c == null) {
            liquibaseConnection = new OfflineConnection("offline:unknown", resourceAccessor);
        } else {
            liquibaseConnection = new JdbcConnection(c);
        }
        Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(liquibaseConnection);
        return database;
    }
}

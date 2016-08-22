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
import liquibase.util.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by iurii.dziuban on 26.07.2016.
 */

/**
 * Programmatic example to use SpringLiquibase for migrations. Configuring liquibase class programmatically to invoke specific methods, for instance, rollback
 */
public class LiquibaseSpringExample {

    public static final String CHANGE_LOG_FILE = "classpath:liquibase/datasetChangeLog.xml";

    public static void main(String[] args) throws SQLException, LiquibaseException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(new String[]{"classpath:liquibase/application-context.xml"});
        DataSource dataSource = applicationContext.getBean(DataSource.class);
        applicationContext.getBean(SpringLiquibase.class);

        // Only for test purposes...
        Liquibase liquibase = createLiquibase(dataSource.getConnection(), applicationContext);
        liquibase.rollback("1.0", "test, production");
    }

    private static Liquibase createLiquibase(Connection c, ClassPathXmlApplicationContext context) throws LiquibaseException {
        SpringLiquibase springLiquibase = new SpringLiquibase();
        springLiquibase.setResourceLoader(context);
        SpringLiquibase.SpringResourceOpener resourceAccessor = springLiquibase.new SpringResourceOpener(CHANGE_LOG_FILE);
        Liquibase liquibase = new Liquibase(CHANGE_LOG_FILE, resourceAccessor, createDatabase(c, resourceAccessor));
        liquibase.setIgnoreClasspathPrefix(true);
        return liquibase;
    }

    private static Database createDatabase(Connection c, ResourceAccessor resourceAccessor) throws DatabaseException {
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

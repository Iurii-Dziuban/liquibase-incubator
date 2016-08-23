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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by iurii.dziuban on 26.07.2016.
 */

/**
 * Programmatic example to use SpringLiquibase for migrations. Configuring liquibase class programmatically to invoke specific methods, for instance, rollback
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:liquibase/application-context.xml"})
public class LiquibaseSpringTest {

    public static final String CHANGE_LOG_FILE = "classpath:liquibase/datasetChangeLog.xml";

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void test() throws SQLException, LiquibaseException {
        DataSource dataSource = applicationContext.getBean(DataSource.class);
        applicationContext.getBean(SpringLiquibase.class);

        // Only for test purposes...
        Liquibase liquibase = createLiquibase(dataSource.getConnection(), applicationContext);
        liquibase.rollback("1.0", "test, production");
    }

    private Liquibase createLiquibase(Connection c, ApplicationContext context) throws LiquibaseException {
        SpringLiquibase springLiquibase = new SpringLiquibase();
        springLiquibase.setResourceLoader(context);
        SpringLiquibase.SpringResourceOpener resourceAccessor = springLiquibase.new SpringResourceOpener(CHANGE_LOG_FILE);
        Liquibase liquibase = new Liquibase(CHANGE_LOG_FILE, resourceAccessor, createDatabase(c, resourceAccessor));
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

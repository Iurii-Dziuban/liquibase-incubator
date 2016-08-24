package org.liquibase.samples;

import liquibase.Liquibase;
import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by iurii.dziuban on 26.07.2016.
 */

/**
 * Programmatic example to use SpringLiquibase for migrations. Configuring liquibase class programmatically to invoke specific methods, for instance, rollback
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:liquibase/xml/xml-dsl-application-context.xml"})
public class XmlDslLiquibaseSpringTest {

    private static final String CHANGE_LOG_FILENAME = "classpath:liquibase/xml/datasetChangeLog.xml";

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void test() throws SQLException, LiquibaseException {
        DataSource dataSource = applicationContext.getBean(DataSource.class);
        applicationContext.getBean(SpringLiquibase.class);

        // Only for test purposes...
        Liquibase liquibase = new CustomLiquibaseFactory().createLiquibase(dataSource.getConnection(), applicationContext, CHANGE_LOG_FILENAME);
        liquibase.rollback("1.0", "test, production");
    }

}

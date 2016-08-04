package org.liquibase.samples;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by iurii.dziuban on 26.07.2016.
 */
public class LiquibaseSpringExample {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(new String[]{"classpath:liquibase/application-context.xml"});

        SpringLiquibase liquibase = applicationContext.getBean(SpringLiquibase.class);

    }

}

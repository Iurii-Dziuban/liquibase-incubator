package org.liquibase.samples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple Logger to show ability to call code from groovy dsl config
 * Created by iurii.dziuban on 24.08.2016.
 */
public class CustomLogger {
    private Logger LOG = LoggerFactory.getLogger(CustomLogger.class);

    public void log(String message) {
        LOG.info(message);
    }
}

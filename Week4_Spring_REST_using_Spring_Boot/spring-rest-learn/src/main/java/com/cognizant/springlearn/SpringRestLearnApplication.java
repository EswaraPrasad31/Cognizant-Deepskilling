package com.cognizant.springlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point of the Spring Boot application.
 */
@SpringBootApplication
public class SpringRestLearnApplication {

    // SLF4J Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringRestLearnApplication.class);

    /**
     * Default constructor.
     */
    public SpringRestLearnApplication() {
        LOGGER.info("START");
        LOGGER.info("END");
    }

    /**
     * The main method that bootstraps and runs the application.
     * 
     * @param args Command-line arguments passed to the application
     */
    public static void main(String[] args) {
        LOGGER.info("START");
        SpringApplication.run(SpringRestLearnApplication.class, args);
        LOGGER.info("END");
    }
}

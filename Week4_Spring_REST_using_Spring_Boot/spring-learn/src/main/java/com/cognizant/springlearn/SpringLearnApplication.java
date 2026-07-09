package com.cognizant.springlearn;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring Boot main application class.
 * This class coordinates the execution of all the Hands-on exercises.
 */
@SpringBootApplication
public class SpringLearnApplication {

    // LOGGER configuration for the main application class (Hands-on 3)
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringLearnApplication.class);

    public static void main(String[] args) {
        LOGGER.info("START");

        // Start the Spring Boot Web application context
        ApplicationContext springBootContext = SpringApplication.run(SpringLearnApplication.class, args);
        LOGGER.debug("Spring Boot Context started successfully.");

        // Execute all Hands-on exercises automatically
        displayDate();
        displayCountry();
        demonstrateScopes();
        displayCountries();

        LOGGER.info("END");
    }

    /**
     * Hands-on 2: Loading date-format.xml and formatting date
     * 
     * ClassPathXmlApplicationContext loads XML configuration files from the classpath.
     * It serves as the Spring container that initializes and manages beans.
     */
    public static void displayDate() {
        LOGGER.info("START");

        // Load the XML configuration context containing the dateFormat bean
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("date-format.xml")) {
            LOGGER.debug("Loaded date-format.xml context.");

            // Retrieve the bean by ID and cast it to SimpleDateFormat class
            SimpleDateFormat format = context.getBean("dateFormat", SimpleDateFormat.class);
            LOGGER.debug("Retrieved bean 'dateFormat': {}", format);

            try {
                // Parse date string
                Date parsedDate = format.parse("31/12/2018");
                // Log parsed Date with debug log (No System.out.println)
                LOGGER.debug("Parsed Date (31/12/2018): {}", parsedDate);
            } catch (ParseException e) {
                LOGGER.error("Failed to parse date string.", e);
            }
        } // Context closes automatically here because ClassPathXmlApplicationContext is AutoCloseable

        LOGGER.info("END");
    }

    /**
     * HANDS ON 4: Loading country.xml and displaying country bean
     */
    public static void displayCountry() {
        LOGGER.info("START");

        // Load XML configuration
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("country.xml")) {
            LOGGER.debug("Loaded country.xml context for displayCountry.");

            // Retrieve country bean
            Country country = context.getBean("country", Country.class);

            // Log country using LOGGER.debug
            LOGGER.debug("Retrieved Country: {}", country);
        }

        LOGGER.info("END");
    }

    /**
     * Hands-on 5: Singleton vs Prototype Scope Demonstration
     * 
     * Singleton Scope vs Prototype Scope:
     * - Singleton (default): A single bean instance is created per container.
     *   The constructor runs only once, and getBean() returns the same reference.
     * - Prototype: A new bean instance is created on each lookup.
     *   The constructor runs on every retrieve request, returning unique references.
     */
    public static void demonstrateScopes() {
        LOGGER.info("START");

        LOGGER.debug("======================= SCOPE DEMONSTRATION =======================");
        
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("country.xml")) {
            
            // 1. Singleton scope check
            LOGGER.debug("Retrieving Singleton bean 'country' first time...");
            Country singleton1 = context.getBean("country", Country.class);
            
            LOGGER.debug("Retrieving Singleton bean 'country' second time...");
            Country singleton2 = context.getBean("country", Country.class);
            
            LOGGER.debug("Comparing Singleton references (singleton1 == singleton2): {}", (singleton1 == singleton2));
            LOGGER.debug("Notice: The constructor 'Inside Country Constructor' was logged ONLY ONCE during context startup.");

            // 2. Prototype scope check
            LOGGER.debug("Retrieving Prototype bean 'prototypeCountry' first time...");
            Country prototype1 = context.getBean("prototypeCountry", Country.class);
            
            LOGGER.debug("Retrieving Prototype bean 'prototypeCountry' second time...");
            Country prototype2 = context.getBean("prototypeCountry", Country.class);
            
            LOGGER.debug("Comparing Prototype references (prototype1 == prototype2): {}", (prototype1 == prototype2));
            LOGGER.debug("Notice: The constructor 'Inside Country Constructor' was logged for EACH retrieval.");
        }

        LOGGER.debug("===================================================================");
        LOGGER.info("END");
    }

    /**
     * HANDS ON 6: Collections Injection Demonstration
     */
    @SuppressWarnings("unchecked")
    public static void displayCountries() {
        LOGGER.info("START");

        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("country.xml")) {
            LOGGER.debug("Loaded country.xml context for displayCountries.");

            // Retrieve the ArrayList bean containing Country references
            ArrayList<Country> countries = context.getBean("countryList", ArrayList.class);
            LOGGER.debug("Retrieved countryList size: {}", countries.size());

            // Iterate and display all countries using debug logs
            for (Country country : countries) {
                LOGGER.debug("Country: {}", country);
            }
        }

        LOGGER.info("END");
    }
}

package com.cognizant.springlearn.service;

import com.cognizant.springlearn.model.Country;
import com.cognizant.springlearn.exception.CountryNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for Country business operations.
 * 
 * Dependency Injection & Container Concepts:
 * - @Service registers this class as a Spring-managed service bean.
 * - ApplicationContext: The central interface to Spring's IoC container, responsible for 
 *   instantiating, configuring, and managing the lifecycle of beans.
 * - ClassPathXmlApplicationContext: A specific implementation of ApplicationContext that loads 
 *   bean configuration from XML configuration files located on the classpath (like country.xml).
 */
@Service
public class CountryService {

    // SLF4J Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(CountryService.class);

    private List<Country> countryList;

    /**
     * Constructor loads countryList from XML.
     * 
     * XML Bean & ClassPathXmlApplicationContext Explanation:
     * - We create a new ClassPathXmlApplicationContext passing "country.xml".
     * - Spring parses the file, instantiates the defined beans (in, us, de, jp, countryList),
     *   and injects the references into the countryList bean.
     * - We retrieve the 'countryList' bean and store it.
     */
    @SuppressWarnings("unchecked")
    public CountryService() {
        LOGGER.info("START");
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("country.xml")) {
            LOGGER.debug("Successfully loaded ClassPathXmlApplicationContext with country.xml");
            // Retrieve the ArrayList bean defined in XML
            this.countryList = context.getBean("countryList", ArrayList.class);
            LOGGER.debug("Retrieved countryList bean from XML context. Size: {}", this.countryList.size());
        }
        LOGGER.info("END");
    }

    /**
     * Retrieves the default country (India).
     * 
     * @return Country object representing India
     */
    public Country getCountry() {
        LOGGER.info("START");
        Country indiaCountry = null;
        for (Country country : countryList) {
            if ("IN".equalsIgnoreCase(country.getCode())) {
                indiaCountry = country;
                break;
            }
        }
        LOGGER.info("END");
        return indiaCountry;
    }

    /**
     * Retrieves the complete list of countries loaded from the XML configuration.
     * 
     * @return List of Country objects
     */
    public List<Country> getCountries() {
        LOGGER.info("START");
        List<Country> list = this.countryList;
        LOGGER.info("END");
        return list;
    }

    /**
     * Case-insensitive lookup of a country by its ISO code.
     * Throws CountryNotFoundException if no matching country is found.
     * 
     * @param code The ISO code to search for
     * @return The matching Country object
     * @throws CountryNotFoundException if no country matches the code
     */
    public Country getCountry(String code) throws CountryNotFoundException {
        LOGGER.info("START");
        LOGGER.debug("Searching for country with code: {}", code);
        for (Country country : countryList) {
            if (country.getCode().equalsIgnoreCase(code)) {
                LOGGER.debug("Found matching country: {}", country);
                LOGGER.info("END");
                return country;
            }
        }
        // Log an error and throw exception if not found
        LOGGER.error("Country with code '{}' not found.", code);
        LOGGER.info("END");
        throw new CountryNotFoundException();
    }
}

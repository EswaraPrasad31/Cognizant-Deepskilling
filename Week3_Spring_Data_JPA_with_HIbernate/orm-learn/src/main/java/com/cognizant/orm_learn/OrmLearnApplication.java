package com.cognizant.orm_learn;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.cognizant.orm_learn.model.Country;
import com.cognizant.orm_learn.service.CountryService;
import com.cognizant.orm_learn.service.exception.CountryNotFoundException;

@SpringBootApplication
public class OrmLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);
    private static CountryService countryService;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
        countryService = context.getBean(CountryService.class);
        
        LOGGER.info("Inside main");

        // Retrieve and print all countries in the database
        testGetAllCountries();

        // Retrieve a specific country by its unique code
        try {
            getAllCountriesTest();
        } catch (CountryNotFoundException e) {
            LOGGER.error("Error retrieving country by code: {}", e.getMessage());
        }

        // Add a new country to the database
        try {
            testAddCountry();
        } catch (CountryNotFoundException e) {
            LOGGER.error("Error performing add operation: {}", e.getMessage());
        }

        // Update an existing country's name
        try {
            testUpdateCountry();
        } catch (CountryNotFoundException e) {
            LOGGER.error("Error performing update operation: {}", e.getMessage());
        }

        // Remove the country from the database
        testDeleteCountry();

        // Perform a search for countries matching a partial name query
        testSearchCountry();
    }

    private static void testGetAllCountries() {
        LOGGER.info("Start: testGetAllCountries");
        List<Country> countries = countryService.getAllCountries();
        LOGGER.debug("countries={}", countries);
        LOGGER.info("End: testGetAllCountries");
    }

    private static void getAllCountriesTest() throws CountryNotFoundException {
        LOGGER.info("Start: getAllCountriesTest");
        Country country = countryService.findCountryByCode("IN");
        LOGGER.debug("Country:{}", country);
        LOGGER.info("End: getAllCountriesTest");
    }

    private static void testAddCountry() throws CountryNotFoundException {
        LOGGER.info("Start: testAddCountry");
        Country country = new Country();
        country.setCode("ZZ");
        country.setName("TestCountry");
        countryService.addCountry(country);
        
        // Verify country with code ZZ was successfully inserted
        Country retrieved = countryService.findCountryByCode("ZZ");
        LOGGER.debug("Added Country retrieved: {}", retrieved);
        LOGGER.info("End: testAddCountry");
    }

    private static void testUpdateCountry() throws CountryNotFoundException {
        LOGGER.info("Start: testUpdateCountry");
        countryService.updateCountry("ZZ", "UpdatedTestCountry");
        
        // Verify name update
        Country retrieved = countryService.findCountryByCode("ZZ");
        LOGGER.debug("Updated Country retrieved: {}", retrieved);
        LOGGER.info("End: testUpdateCountry");
    }

    private static void testDeleteCountry() {
        LOGGER.info("Start: testDeleteCountry");
        countryService.deleteCountry("ZZ");
        
        // Confirm the country was removed
        try {
            Country retrieved = countryService.findCountryByCode("ZZ");
            LOGGER.error("Country ZZ was NOT deleted! Retrieved: {}", retrieved);
        } catch (CountryNotFoundException e) {
            LOGGER.debug("Country ZZ successfully deleted: {}", e.getMessage());
        }
        LOGGER.info("End: testDeleteCountry");
    }

    private static void testSearchCountry() {
        LOGGER.info("Start: testSearchCountry");
        List<Country> countries = countryService.findCountryByNameContaining("ia");
        LOGGER.debug("Countries matching 'ia': {}", countries);
        LOGGER.info("End: testSearchCountry");
    }
}



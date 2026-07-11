package com.cognizant.springlearn.controller;

import com.cognizant.springlearn.model.Country;
import com.cognizant.springlearn.exception.CountryNotFoundException;
import com.cognizant.springlearn.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller class to handle REST API requests related to Country operations.
 * 
 * Spring Annotations Explanations:
 * - @RestController: Marks this class as a RESTful controller. Every response object is serialized 
 *   directly into the HTTP response body (typically in JSON format).
 * - @RequestMapping: Used to map web requests onto specific handler classes and/or handler methods. 
 *   While we use @GetMapping here directly, @RequestMapping could be used at the class level to prefix endpoints.
 */
@RestController
public class CountryController {

    // SLF4J Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);

    private final CountryService countryService;

    /**
     * Constructor Injection (Dependency Injection):
     * - We annotate the constructor with @Autowired.
     * - Spring IoC container automatically searches for a matching bean of type CountryService 
     *   (in this case, our CountryService class marked with @Service) and injects it here.
     * 
     * @param countryService The country business service bean
     */
    @Autowired
    public CountryController(CountryService countryService) {
        LOGGER.info("START");
        this.countryService = countryService;
        LOGGER.info("END");
    }

    /**
     * Endpoint GET /country
     * Returns the default country (India) in JSON format.
     * 
     * JSON Conversion Explanation:
     * - When this method returns a Java object (Country), Spring Boot utilizes its built-in HTTP Message 
     *   Converters (specifically, Jackson library for JSON) to serialize the Java object properties 
     *   (via getter methods) directly into a JSON formatted string.
     * 
     * @return Country representation of India
     */
    @GetMapping("/country")
    public Country getCountry() {
        LOGGER.info("START");
        Country country = countryService.getCountry();
        LOGGER.info("END");
        return country;
    }

    /**
     * Endpoint GET /countries
     * Returns all configured countries.
     * 
     * JSON Conversion:
     * - The List<Country> returned is translated into a JSON Array.
     * 
     * @return List of all countries
     */
    @GetMapping("/countries")
    public List<Country> getCountries() {
        LOGGER.info("START");
        List<Country> countries = countryService.getCountries();
        LOGGER.info("END");
        return countries;
    }

    /**
     * Endpoint GET /countries/{code}
     * Returns the matching country by ISO code or throws CountryNotFoundException (resulting in a 404).
     * 
     * @PathVariable:
     * - Binds the URI template variable {code} to the method parameter 'code'.
     * - If the URL is /countries/IN, the 'code' parameter will be assigned the value "IN".
     * 
     * @param code Country ISO code
     * @return Matching country
     * @throws CountryNotFoundException if country does not exist
     */
    @GetMapping("/countries/{code}")
    public Country getCountryByCode(@PathVariable("code") String code) throws CountryNotFoundException {
        LOGGER.info("START");
        LOGGER.debug("Received request for country code: {}", code);
        Country country = countryService.getCountry(code);
        LOGGER.info("END");
        return country;
    }
}

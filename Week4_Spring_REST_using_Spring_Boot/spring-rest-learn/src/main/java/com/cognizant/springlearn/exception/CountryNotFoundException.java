package com.cognizant.springlearn.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom Exception thrown when a requested country is not found.
 * 
 * @ResponseStatus Explanation:
 * - This annotation tells Spring MVC to automatically return the specified HTTP status code (404 NOT_FOUND)
 *   and reason phrase ("Country not found") to the client when this exception is thrown from a controller handler method.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Country not found")
public class CountryNotFoundException extends Exception {

    // SLF4J Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(CountryNotFoundException.class);

    /**
     * Default constructor for the exception.
     */
    public CountryNotFoundException() {
        LOGGER.info("START");
        LOGGER.info("END");
    }
}

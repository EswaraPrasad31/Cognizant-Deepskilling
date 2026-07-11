package com.cognizant.springlearn.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Country Entity Class representing the Country model.
 * 
 * XML Bean & Dependency Injection Explanation:
 * - This class acts as a POJO (Plain Old Java Object) bean managed by the Spring IoC container.
 * - When country.xml defines beans of this class, Spring uses the default (no-argument) constructor
 *   to instantiate the objects and setter methods (setCode, setName) to perform Setter Injection.
 */
public class Country {

    // SLF4J Logger instance for tracing execution inside Country
    private static final Logger LOGGER = LoggerFactory.getLogger(Country.class);

    private String code;
    private String name;

    /**
     * Default constructor (Required by Spring for reflection-based instantiation).
     */
    public Country() {
        LOGGER.info("START");
        LOGGER.debug("Inside Country Constructor.");
        LOGGER.info("END");
    }

    /**
     * Parameterized constructor to initialize code and name.
     * 
     * @param code Country ISO code
     * @param name Country name
     */
    public Country(String code, String name) {
        LOGGER.info("START");
        LOGGER.debug("Inside Country Parameterized Constructor. Code: {}, Name: {}", code, name);
        this.code = code;
        this.name = name;
        LOGGER.info("END");
    }

    // Getter for code
    public String getCode() {
        LOGGER.info("START");
        LOGGER.debug("Inside getCode() getter. Value: {}", this.code);
        LOGGER.info("END");
        return code;
    }

    // Setter for code (Spring uses this for setter injection)
    public void setCode(String code) {
        LOGGER.info("START");
        LOGGER.debug("Inside setCode() setter. Setting value to: {}", code);
        this.code = code;
        LOGGER.info("END");
    }

    // Getter for name
    public String getName() {
        LOGGER.info("START");
        LOGGER.debug("Inside getName() getter. Value: {}", this.name);
        LOGGER.info("END");
        return name;
    }

    // Setter for name (Spring uses this for setter injection)
    public void setName(String name) {
        LOGGER.info("START");
        LOGGER.debug("Inside setName() setter. Setting value to: {}", name);
        this.name = name;
        LOGGER.info("END");
    }

    /**
     * Overridden toString method.
     */
    @Override
    public String toString() {
        LOGGER.info("START");
        String result = "Country [code=" + code + ", name=" + name + "]";
        LOGGER.info("END");
        return result;
    }
}

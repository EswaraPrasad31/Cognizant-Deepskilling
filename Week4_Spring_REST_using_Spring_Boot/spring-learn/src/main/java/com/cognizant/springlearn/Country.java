package com.cognizant.springlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hands-on 4: Country Entity Class
 * 
 * Spring Bean Concept:
 * A simple JavaBeans class (POJO) that Spring instantiates and manages.
 * The default constructor is required for reflection-based instantiation,
 * and property fields are populated using setter injection.
 */
public class Country {

    // Logger instance for logging within the Country class
    private static final Logger LOGGER = LoggerFactory.getLogger(Country.class);

    private String code;
    private String name;

    /**
      * Default constructor.
      * Required by Spring to instantiate the bean using reflection.
      */
    public Country() {
        LOGGER.debug("Inside Country Constructor.");
    }

    // Getter for code
    public String getCode() {
        LOGGER.debug("Inside getCode() getter. Value: {}", this.code);
        return code;
    }

    // Setter for code (Spring uses this for setter injection)
    public void setCode(String code) {
        LOGGER.debug("Inside setCode() setter. Setting value to: {}", code);
        this.code = code;
    }

    // Getter for name
    public String getName() {
        LOGGER.debug("Inside getName() getter. Value: {}", this.name);
        return name;
    }

    // Setter for name (Spring uses this for setter injection)
    public void setName(String name) {
        LOGGER.debug("Inside setName() setter. Setting value to: {}", name);
        this.name = name;
    }

    /**
      * Returns a string representation of the Country bean.
      */
    @Override
    public String toString() {
        return "Country [code=" + code + ", name=" + name + "]";
    }
}

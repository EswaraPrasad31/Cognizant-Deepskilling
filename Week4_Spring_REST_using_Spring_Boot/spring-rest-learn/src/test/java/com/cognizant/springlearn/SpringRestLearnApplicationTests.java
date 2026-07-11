package com.cognizant.springlearn;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;

import com.cognizant.springlearn.controller.HelloController;
import com.cognizant.springlearn.controller.CountryController;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Spring Boot Integration Tests using MockMvc.
 * 
 * MockMVC Explanation:
 * - MockMvc provides support for Spring MVC testing. It encapsulates all web application behaviors 
 *   and allows performing requests and asserting responses without starting a full HTTP server,
 *   making the tests run extremely fast while still validating controller logic, mappings, serialization, and status codes.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class SpringRestLearnApplicationTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringRestLearnApplicationTests.class);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HelloController helloController;

    @Autowired
    private CountryController countryController;

    /**
     * Test case to verify if the Application Context has loaded the controllers correctly.
     */
    @Test
    public void contextLoads() {
        LOGGER.info("START");
        assertNotNull(helloController, "HelloController should be successfully loaded in the context");
        assertNotNull(countryController, "CountryController should be successfully loaded in the context");
        LOGGER.info("END");
    }

    /**
     * Test case to verify GET /country endpoint.
     * Expected:
     * - Status 200 OK
     * - JSON payload contains code "IN" and name "India"
     */
    @Test
    public void testGetCountry() throws Exception {
        LOGGER.info("START");
        mockMvc.perform(get("/country"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("IN"))
                .andExpect(jsonPath("$.name").value("India"));
        LOGGER.info("END");
    }

    /**
     * Test case to verify GET /countries endpoint.
     * Expected:
     * - Status 200 OK
     * - Returns an array of size 4
     */
    @Test
    public void testGetCountries() throws Exception {
        LOGGER.info("START");
        mockMvc.perform(get("/countries"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].code").value("IN"))
                .andExpect(jsonPath("$[1].code").value("US"))
                .andExpect(jsonPath("$[2].code").value("DE"))
                .andExpect(jsonPath("$[3].code").value("JP"));
        LOGGER.info("END");
    }

    /**
     * Test case to verify GET /countries/{code} with an invalid code.
     * Expected:
     * - Status 404 NOT FOUND (due to @ResponseStatus on CountryNotFoundException)
     */
    @Test
    public void testGetCountryNotFound() throws Exception {
        LOGGER.info("START");
        mockMvc.perform(get("/countries/xyz"))
                .andExpect(status().isNotFound());
        LOGGER.info("END");
    }
}

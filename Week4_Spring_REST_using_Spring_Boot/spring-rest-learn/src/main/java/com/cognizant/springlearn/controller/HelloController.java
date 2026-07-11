package com.cognizant.springlearn.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello REST Controller to handle basic hello requests.
 * 
 * REST Controller & Mapping Explanations:
 * - @RestController: Indicates that this class is a controller where every method returns a domain object 
 *   directly as XML/JSON or plain text (instead of rendering a HTML view/template). It combines @Controller 
 *   and @ResponseBody.
 */
@RestController
public class HelloController {

    // SLF4J Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    /**
     * Default constructor.
     */
    public HelloController() {
        LOGGER.info("START");
        LOGGER.info("END");
    }

    /**
     * Endpoint GET /hello
     * 
     * @GetMapping: A composed annotation that acts as a shortcut for @RequestMapping(method = RequestMethod.GET).
     * It maps HTTP GET requests onto this specific handler method.
     * 
     * @return String message "Hello World!!"
     */
    @GetMapping("/hello")
    public String sayHello() {
        LOGGER.info("START");
        LOGGER.info("END");
        return "Hello World!!";
    }
}

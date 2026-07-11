package com.cognizant.springlearn.controller;

import com.cognizant.springlearn.model.Country;
import com.cognizant.springlearn.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountryController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<Country> getAllCountries() {
        LOGGER.info("START - GET /countries");
        List<Country> countries = countryService.getAllCountries();
        LOGGER.info("END - GET /countries");
        return countries;
    }

    @GetMapping("/admin")
    public String getAdminOnlyMessage() {
        LOGGER.info("START - GET /countries/admin");
        LOGGER.info("END - GET /countries/admin");
        return "Admin access granted";
    }
}

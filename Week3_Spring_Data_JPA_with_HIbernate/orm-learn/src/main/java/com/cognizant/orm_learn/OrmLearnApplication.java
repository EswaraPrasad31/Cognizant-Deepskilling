package com.cognizant.orm_learn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cognizant.orm_learn.service.CountryService;


@SpringBootApplication
public class OrmLearnApplication implements CommandLineRunner {
	@Autowired
	private CountryService countryService;
	private static final Logger LOGGER =
	        LoggerFactory.getLogger(OrmLearnApplication.class);
	public static void main(String[] args) {

	    SpringApplication.run(OrmLearnApplication.class, args);

	    LOGGER.info("Inside main");

	}
	@Override
	public void run(String... args) {

	    System.out.println("Countries");

	    countryService.getAllCountries()
	            .forEach(System.out::println);

	}

}

package com.cognizant.springlearn.service;

import com.cognizant.springlearn.model.Country;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountryService {
    private static final List<Country> COUNTRY_LIST = new ArrayList<>();

    static {
        COUNTRY_LIST.add(new Country("IN", "India"));
        COUNTRY_LIST.add(new Country("US", "United States"));
        COUNTRY_LIST.add(new Country("DE", "Germany"));
        COUNTRY_LIST.add(new Country("JP", "Japan"));
    }

    public List<Country> getAllCountries() {
        return COUNTRY_LIST;
    }

    public Country addCountry(Country country) {
        COUNTRY_LIST.add(country);
        return country;
    }
}

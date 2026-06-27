package com.cognizant.springjunit;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    public int square(int number) {

        return number * number;

    }

}
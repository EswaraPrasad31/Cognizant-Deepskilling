package com.cognizant.springjunit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CalculatorServiceTest {

    private CalculatorService calculatorService =
            new CalculatorService();

    @Test
    public void testAdd() {

        int result = calculatorService.add(10, 20);

        assertEquals(30, result);

    }

}
package com.cognizant.springjunit;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CalculatorParameterizedTest {

    private CalculatorService calculatorService =
            new CalculatorService();

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    public void testSquare(int number) {

        int result = calculatorService.square(number);

        assertTrue(result > 0);

    }

}
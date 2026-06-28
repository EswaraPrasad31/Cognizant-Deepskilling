package com.cognizant.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SampleCalculatorTest {

    @Test
    public void testAddition() {

        int firstNumber = 10;
        int secondNumber = 20;

        int result = firstNumber + secondNumber;

        assertEquals(30, result);
    }

    @Test
    public void testSubtraction() {

        int firstNumber = 20;
        int secondNumber = 10;

        int result = firstNumber - secondNumber;

        assertEquals(10, result);
    }
}
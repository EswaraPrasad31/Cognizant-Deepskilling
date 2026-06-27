package com.cognizant.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CalculatorTest {

    Calculator calculator = new Calculator();

    @Test
    public void testAdd() {

        int actualResult = calculator.add(20, 10);

        assertEquals(30, actualResult);
    }

    @Test
    public void testSubtract() {

        int actualResult = calculator.subtract(20, 10);

        assertEquals(10, actualResult);
    }

    @Test
    public void testMultiply() {

        int actualResult = calculator.multiply(20, 10);

        assertEquals(200, actualResult);
    }

    @Test
    public void testDivide() {

        int actualResult = calculator.divide(20, 10);

        assertEquals(2, actualResult);
    }
}
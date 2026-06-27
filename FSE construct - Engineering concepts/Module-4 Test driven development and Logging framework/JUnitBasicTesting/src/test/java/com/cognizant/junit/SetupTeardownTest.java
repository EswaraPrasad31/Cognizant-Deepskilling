package com.cognizant.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SetupTeardownTest {

    private Calculator calculator;

    @BeforeEach
    public void setUp() {

        System.out.println("Setting up test...");

        calculator = new Calculator();
    }

    @AfterEach
    public void tearDown() {

        System.out.println("Cleaning up after test...");
    }

    @Test
    public void testAddition() {

        // Arrange
        int firstNumber = 10;
        int secondNumber = 20;

        // Act
        int actualResult = calculator.add(firstNumber, secondNumber);

        // Assert
        assertEquals(30, actualResult);
    }

    @Test
    public void testSubtraction() {

        // Arrange
        int firstNumber = 30;
        int secondNumber = 10;

        // Act
        int actualResult = calculator.subtract(firstNumber, secondNumber);

        // Assert
        assertEquals(20, actualResult);
    }

    @Test
    public void testMultiplication() {

        // Arrange
        int firstNumber = 5;
        int secondNumber = 6;

        // Act
        int actualResult = calculator.multiply(firstNumber, secondNumber);

        // Assert
        assertEquals(30, actualResult);
    }

    @Test
    public void testDivision() {

        // Arrange
        int firstNumber = 40;
        int secondNumber = 8;

        // Act
        int actualResult = calculator.divide(firstNumber, secondNumber);

        // Assert
        assertEquals(5, actualResult);
    }
}
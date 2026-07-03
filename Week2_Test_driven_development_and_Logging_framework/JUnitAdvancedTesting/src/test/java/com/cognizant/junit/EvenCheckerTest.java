package com.cognizant.junit;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class EvenCheckerTest {

    private EvenChecker evenChecker = new EvenChecker();

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6, 8, 10, 100, 200})
    public void testEvenNumbers(int number) {

        assertTrue(evenChecker.isEven(number));
    }
}
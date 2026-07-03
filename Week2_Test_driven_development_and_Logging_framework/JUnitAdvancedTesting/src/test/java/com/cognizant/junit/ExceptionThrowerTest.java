package com.cognizant.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ExceptionThrowerTest {

    private ExceptionThrower exceptionThrower = new ExceptionThrower();

    @Test
    public void testThrowException() {

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> exceptionThrower.throwException());

        assertEquals("This is an Illegal Argument Exception.", exception.getMessage());
    }
}
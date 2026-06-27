package com.cognizant.junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class AssertionsTest {

    AssertionsExample assertionsExample = new AssertionsExample();

    @Test
    public void testAssertEquals() {

        int actualResult = assertionsExample.add(15, 10);

        assertEquals(25, actualResult);
    }

    @Test
    public void testAssertTrue() {

        assertTrue(assertionsExample.isEligibleToVote(20));
    }

    @Test
    public void testAssertFalse() {

        assertFalse(assertionsExample.isEligibleToVote(15));
    }

    @Test
    public void testAssertNull() {

        assertNull(assertionsExample.getNullValue());
    }

    @Test
    public void testAssertNotNull() {

        assertNotNull(assertionsExample.getStudentName());
    }
}
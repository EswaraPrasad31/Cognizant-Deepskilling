package com.cognizant.junit;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        EvenCheckerTest.class,
        SampleCalculatorTest.class
})
public class AllTests {

}
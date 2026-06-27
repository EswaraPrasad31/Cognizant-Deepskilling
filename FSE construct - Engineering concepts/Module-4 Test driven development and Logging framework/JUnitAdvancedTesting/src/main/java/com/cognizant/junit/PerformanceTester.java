package com.cognizant.junit;

public class PerformanceTester {

    public void performTask() {

        try {

            Thread.sleep(1000);

        } catch (InterruptedException exception) {

            exception.printStackTrace();
        }
    }
}
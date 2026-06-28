package com.cognizant.junit;

public class AssertionsExample {

    public int add(int firstNumber, int secondNumber) {
        return firstNumber + secondNumber;
    }

    public boolean isEligibleToVote(int age) {
        return age >= 18;
    }

    public String getStudentName() {
        return "Eswar";
    }

    public String getNullValue() {
        return null;
    }
}
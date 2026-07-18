package com.cognizant.loan.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoanController {

    @GetMapping("/loans/{number}")
    public LoanDetails getLoanDetails(@PathVariable("number") String number) {
        return new LoanDetails(number, "car", 400000, 3258, 18);
    }

    public static class LoanDetails {
        private String number;
        private String type;
        private double loan;
        private double emi;
        private int tenure;

        public LoanDetails(String number, String type, double loan, double emi, int tenure) {
            this.number = number;
            this.type = type;
            this.loan = loan;
            this.emi = emi;
            this.tenure = tenure;
        }

        // Getters and Setters
        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public double getLoan() {
            return loan;
        }

        public void setLoan(double loan) {
            this.loan = loan;
        }

        public double getEmi() {
            return emi;
        }

        public void setEmi(double emi) {
            this.emi = emi;
        }

        public int getTenure() {
            return tenure;
        }

        public void setTenure(int tenure) {
            this.tenure = tenure;
        }
    }
}

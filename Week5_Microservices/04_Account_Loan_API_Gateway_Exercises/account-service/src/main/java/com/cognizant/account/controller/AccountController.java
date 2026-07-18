package com.cognizant.account.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @GetMapping("/accounts/{number}")
    public AccountDetails getAccountDetails(@PathVariable("number") String number) {
        return new AccountDetails(number, "savings", 234343);
    }

    public static class AccountDetails {
        private String number;
        private String type;
        private double balance;

        public AccountDetails(String number, String type, double balance) {
            this.number = number;
            this.type = type;
            this.balance = balance;
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

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }
    }
}

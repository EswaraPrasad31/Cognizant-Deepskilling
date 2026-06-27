package com.cognizant.junit;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@TestMethodOrder(OrderAnnotation.class)
public class OrderedTests {

    @Test
    @Order(1)
    public void testLogin() {

        System.out.println("Step 1 : User Login");

        assertTrue(true);
    }

    @Test
    @Order(2)
    public void testSearchProduct() {

        System.out.println("Step 2 : Search Product");

        assertTrue(true);
    }

    @Test
    @Order(3)
    public void testAddToCart() {

        System.out.println("Step 3 : Add Product to Cart");

        assertTrue(true);
    }

    @Test
    @Order(4)
    public void testPayment() {

        System.out.println("Step 4 : Make Payment");

        assertTrue(true);
    }

    @Test
    @Order(5)
    public void testLogout() {

        System.out.println("Step 5 : User Logout");

        assertTrue(true);
    }
}
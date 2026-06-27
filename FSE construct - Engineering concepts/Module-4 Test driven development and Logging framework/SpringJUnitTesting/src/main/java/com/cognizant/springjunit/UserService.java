package com.cognizant.springjunit;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User getUserById(Long id) {

        throw new NoSuchElementException("User not found");

    }

}
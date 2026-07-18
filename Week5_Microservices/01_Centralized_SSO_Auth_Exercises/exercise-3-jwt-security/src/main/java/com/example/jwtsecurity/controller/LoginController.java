package com.example.jwtsecurity.controller;

import com.example.jwtsecurity.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {
    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public String login(@RequestParam String username) {
        return tokenProvider.createToken(username);
    }
}

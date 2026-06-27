package com.cognizant.springjunit;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.context.annotation.Import;

import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserController.class)
@Import(GlobalExceptionHandler.class)
public class UserControllerExceptionTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testUserNotFound() throws Exception {

        when(userService.getUserById(1L))
                .thenThrow(new NoSuchElementException());

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("User not found"));

    }

}
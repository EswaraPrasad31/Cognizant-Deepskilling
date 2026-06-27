package com.cognizant.mockitospring;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    public UserControllerTest() {

        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUser() {

        User user = new User(1L, "Eswar");

        when(userService.getUserById(1L)).thenReturn(user);

        User result = userController.getUser(1L).getBody();

        assertEquals("Eswar", result.getName());
    }

}
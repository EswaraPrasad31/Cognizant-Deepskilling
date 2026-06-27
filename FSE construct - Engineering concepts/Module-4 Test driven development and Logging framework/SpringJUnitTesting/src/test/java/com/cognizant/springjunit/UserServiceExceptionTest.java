package com.cognizant.springjunit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserServiceExceptionTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    public UserServiceExceptionTest() {

        MockitoAnnotations.openMocks(this);

    }

    @Test
    public void testUserNotFoundException() {

        when(userRepository.findById(1L))
                .thenReturn(Optional.empty());

        UserNotFoundException exception =
                assertThrows(UserNotFoundException.class,
                        () -> userService.getUserById(1L));

        assertEquals("User not found",
                exception.getMessage());

    }

}
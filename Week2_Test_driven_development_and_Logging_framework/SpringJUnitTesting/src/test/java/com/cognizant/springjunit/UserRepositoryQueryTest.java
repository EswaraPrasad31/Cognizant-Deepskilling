package com.cognizant.springjunit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserRepositoryQueryTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    public UserRepositoryQueryTest() {

        MockitoAnnotations.openMocks(this);

    }

    @Test
    public void testFindUserByName() {

        User user = new User(1L, "Eswar");

        when(userRepository.findByName("Eswar"))
                .thenReturn(user);

        User result = userService.getUserByName("Eswar");

        assertEquals("Eswar", result.getName());

    }

}
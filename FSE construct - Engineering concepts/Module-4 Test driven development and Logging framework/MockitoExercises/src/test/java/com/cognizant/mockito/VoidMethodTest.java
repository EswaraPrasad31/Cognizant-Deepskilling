package com.cognizant.mockito;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class VoidMethodTest {

    @Test
    public void testVoidMethod() {

        NotificationService mockNotificationService =
                Mockito.mock(NotificationService.class);

        doNothing().when(mockNotificationService)
                   .sendNotification("Welcome Eswar");

        UserService userService =
                new UserService(mockNotificationService);

        userService.registerUser("Eswar");

        verify(mockNotificationService)
                .sendNotification("Welcome Eswar");
    }
}
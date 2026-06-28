package com.cognizant.mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class VoidMethodExceptionTest {

    @Test
    public void testVoidMethodThrowsException() {

        NotificationService mockNotificationService =
                Mockito.mock(NotificationService.class);

        doThrow(new RuntimeException("Notification Failed"))
                .when(mockNotificationService)
                .sendNotification("Welcome Eswar");

        UserService userService =
                new UserService(mockNotificationService);

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> userService.registerUser("Eswar"));

        org.junit.jupiter.api.Assertions.assertEquals(
                "Notification Failed",
                exception.getMessage());
    }
}
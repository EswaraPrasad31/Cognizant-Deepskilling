package com.cognizant.mockito;

import static org.mockito.Mockito.inOrder;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

public class InteractionOrderTest {

    @Test
    public void testInteractionOrder() {

        NotificationService mockNotificationService =
                Mockito.mock(NotificationService.class);

        UserRegistrationService userRegistrationService =
                new UserRegistrationService(mockNotificationService);

        userRegistrationService.registerUser("Eswar");

        InOrder inOrder = inOrder(mockNotificationService);

        inOrder.verify(mockNotificationService)
                .sendWelcomeEmail("Eswar");

        inOrder.verify(mockNotificationService)
                .sendWelcomeSms("Eswar");
    }
}
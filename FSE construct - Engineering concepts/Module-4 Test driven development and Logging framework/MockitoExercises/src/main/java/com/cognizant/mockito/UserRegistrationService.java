package com.cognizant.mockito;

public class UserRegistrationService {

    private NotificationService notificationService;

    public UserRegistrationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void registerUser(String userName) {

        System.out.println("Registering user : " + userName);

        notificationService.sendWelcomeEmail(userName);

        notificationService.sendWelcomeSms(userName);
    }
}
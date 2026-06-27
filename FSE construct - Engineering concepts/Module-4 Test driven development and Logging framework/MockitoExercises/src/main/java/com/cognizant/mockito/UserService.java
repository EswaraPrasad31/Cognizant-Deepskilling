package com.cognizant.mockito;

public class UserService {

    private NotificationService notificationService;

    public UserService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void registerUser(String userName) {

        notificationService.sendNotification("Welcome " + userName);
    }
}
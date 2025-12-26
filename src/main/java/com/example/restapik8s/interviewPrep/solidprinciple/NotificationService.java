package com.example.restapik8s.interviewPrep.solidprinciple;

public class NotificationService {

    private Notification notification;

    public NotificationService(Notification notification) {
        this.notification = notification;
    }

    public void sendNotification(String message) {
        notification.sendNotification(message);
    }
    
}

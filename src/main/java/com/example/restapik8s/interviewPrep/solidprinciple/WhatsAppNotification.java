package com.example.restapik8s.interviewPrep.solidprinciple;

public class WhatsAppNotification implements Notification {

    @Override
    public void sendNotification(String message) {
        System.out.println("WhatsApp notification sent: " + message);
    }

}

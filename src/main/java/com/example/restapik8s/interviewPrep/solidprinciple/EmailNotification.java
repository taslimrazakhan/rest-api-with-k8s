package com.example.restapik8s.interviewPrep.solidprinciple;

public class EmailNotification implements Notification {

    @Override
    public void sendNotification(String message) {
        System.out.println("Email notification sent: " + message);
    }
}
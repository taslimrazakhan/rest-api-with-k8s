package com.example.restapik8s.interviewPrep.solidprinciple;

public class NotificationSender {

    public static void main(String[] args) {
        
        Notification emailNotification = new EmailNotification();
        NotificationService emailService = new NotificationService(emailNotification);
        emailService.sendNotification("Hello via Email!");

        Notification whatsappNotification = new WhatsAppNotification();
        NotificationService whatsappService = new NotificationService(whatsappNotification);
        whatsappService.sendNotification("Hello via WhatsApp!");
    }
}

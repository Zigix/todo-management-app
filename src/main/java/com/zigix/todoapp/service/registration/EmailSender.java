package com.zigix.todoapp.service.registration;

public interface EmailSender {

    void sendEmail(String to, String htmlTemplate);

}

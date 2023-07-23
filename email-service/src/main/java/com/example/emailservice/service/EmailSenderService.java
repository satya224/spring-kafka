package com.example.emailservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailSenderService {
    private final JavaMailSender javaMailSender;

    public EmailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(String receiverEmail, String body, String subject) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(receiverEmail);
        message.setText(body);
        message.setSubject(subject);
        javaMailSender.send(message);
        log.info("Mail Sent...");
    }
}

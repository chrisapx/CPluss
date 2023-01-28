package com.company.cpluss.notification_service.service;

import com.company.cpluss.notification_service.model.Email;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSenderImpl javaMailSender;

    public EmailService(JavaMailSenderImpl javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    public SimpleMailMessage send(Email email) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(email.getTo());
        simpleMailMessage.setSubject(email.getSubject());
        simpleMailMessage.setText(email.getBody());

        javaMailSender.send(simpleMailMessage);

        return simpleMailMessage;
    }
}

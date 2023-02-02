package com.company.cpluss.notification_service.service;

import com.company.cpluss.notification_service.model.Email;
import com.company.cpluss.notification_service.model.Emails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
@Slf4j
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
        log.info("success");
        return simpleMailMessage;
    }


    public SimpleMailMessage sendMultiple(Emails email) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(email.getTo());
        simpleMailMessage.setSubject(email.getSubject());
        simpleMailMessage.setText(email.getBody());

        javaMailSender.send(simpleMailMessage);
        log.info("success");
        return simpleMailMessage;
    }
}

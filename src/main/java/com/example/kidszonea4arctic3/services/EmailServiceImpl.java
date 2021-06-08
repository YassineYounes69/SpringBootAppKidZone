package com.example.kidszonea4arctic3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.InternetHeaders;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

@Component
public class EmailServiceImpl{

    @Autowired
    JavaMailSenderImpl sender = new JavaMailSenderImpl();

    public void sendSimpleMessage(String to, String subject, String text) throws MessagingException {



        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setText(text, true);

        helper.setFrom("kidszoneesprit@gmail.com");

        helper.setSubject(subject);
        sender.send(message);

    }
}

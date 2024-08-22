package com.greenmark.common.email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender emailSender;

    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendEmail(String to, String subject, String text)
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("jeb.seibel@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        System.out.println("Fake email sent");
        // emailSender.send(message);
    }
}

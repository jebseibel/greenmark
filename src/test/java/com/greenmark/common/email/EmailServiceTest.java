package com.greenmark.common.email;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest
class EmailServiceTest {

    @Autowired
    EmailService emailService;

    @Autowired
    JavaMailSender javaMailSender;

    @Test
    void sendEmail() {

        String to = "jeb.seibel@yahoo.com";
        String subject = "jeb.seibel@gmail.com";
        String text = "This is a test email";
        emailService.sendEmail(to, subject, text);
    }
}
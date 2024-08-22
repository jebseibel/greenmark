package com.greenmark.common.email;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailTestingController {

    private final EmailService emailService;

    public EmailTestingController(EmailService emailService) {
        this.emailService = emailService;
    }

    @RequestMapping("/send-test-email")
    public String sendTestEmail() {
        emailService.sendEmail("jeb.seibel@yahoo.com", "Email from me", "this is a test Email");
        return "Email Sent Successfully";
    }
}

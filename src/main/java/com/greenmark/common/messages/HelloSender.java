package com.greenmark.common.messages;

import com.greenmark.config.JmsConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class HelloSender {

    private final JmsTemplate jmsTemplate;

    //@Scheduled(fixedRate = 2000)
    public void sendMessage() {
        System.out.println("Sending a message");
        HelloWorldMessage message = HelloWorldMessage
                .builder()
                .uuid(UUID.randomUUID())
                .message("Hello World")
                .build();

        jmsTemplate.convertAndSend(JmsConfig.BROKER_BUY_QUEUE, message);

        System.out.println("Message sent");
    }
}

package com.greenmark.broker;

import com.greenmark.common.messages.HelloWorldMessage;
import com.greenmark.config.JmsConfig;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class BrokerOrderListener {

    @JmsListener(destination = JmsConfig.BROKER_BUY_QUEUE)
    public void listenBrokerBuy(@Payload HelloWorldMessage helloWorldMessage) {

        System.out.println("I got a message!!");
        System.out.println(helloWorldMessage);
    }

    @JmsListener(destination = JmsConfig.BROKER_SELL_QUEUE)
    public void listenBrokerSell(@Payload HelloWorldMessage helloWorldMessage) {

        System.out.println("I got a message!!");
        System.out.println(helloWorldMessage);
    }
}

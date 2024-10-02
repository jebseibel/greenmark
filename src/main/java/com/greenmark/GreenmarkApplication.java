package com.greenmark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class GreenmarkApplication {

    public static void main(String[] args) throws Exception {

//        ActiveMQServer server = ActiveMQServers.newActiveMQServer(new ConfigurationImpl()
//                .setPersistenceEnabled(false)
//                .setJournalDirectory("target/data/journal")
//                .setSecurityEnabled(false)
//                .addAcceptorConfiguration("invm", "vm://0"));
//        server.start();
        SpringApplication.run(GreenmarkApplication.class, args);
//        log.info("APPLICATION READY");
    }
}
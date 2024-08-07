package com.greenmark;

import com.greenmark.bootstrap.ModelLogic;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@Slf4j
@SpringBootApplication
public class GreenmarkApplication implements CommandLineRunner {
//
    @Autowired
    AppConfig appConfig;

    @Autowired
    ModelLogic modelLogic;

    @PostConstruct
    public void init(){
        System.out.println("Test Start");

        System.out.println(appConfig.getEmail());
        System.out.println(modelLogic.getPassStochkDaily());
        System.out.println("Finished loading stocks");
    }

    public static void main(String[] args) {
        log.info("STARTING THE APPLICATION");
        SpringApplication.run(GreenmarkApplication.class, args);
        log.info("APPLICATION FINISHED");
    }

    @Bean
    ApplicationRunner applicationRunner(Environment environment) {
        return args -> {
            log.info("message from application.properties " + environment.getProperty("message-from-application-properties"));
        };
    }

    @Override
    public void run(String... args) {
        log.info("EXECUTING : command line runner");

//        log.info("Loading data create");
//
//        LoadStocks loadStocks = new LoadStocks();
//        System.out.println(loadStocks);
//        boolean success = loadStocks.load();
//        log.info("Loading data done "+success);
    }
}
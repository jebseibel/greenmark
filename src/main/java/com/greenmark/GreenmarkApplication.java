package com.greenmark;

import com.greenmark.app.AppConfig;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class GreenmarkApplication implements CommandLineRunner {
//
    @Autowired
AppConfig appConfig;

    @PostConstruct
    public void init(){
        System.out.println("Test Start");

        System.out.println(appConfig.getEmail());
        System.out.println("Finished loading stocks");
    }

    public static void main(String[] args) {
        log.info("STARTING THE APPLICATION");
        SpringApplication.run(GreenmarkApplication.class, args);
        log.info("APPLICATION FINISHED");
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
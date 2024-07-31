package com.greenmark;

import com.greenmark.datafeed.finnhub.client.FinnhubClient;
import com.greenmark.datafeed.finnhub.models.Quote;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.core5.http.ParseException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.io.IOException;

@Slf4j
@SpringBootApplication
@EnableConfigurationProperties(AppConfig.class)
public class GreentradeApp implements CommandLineRunner {

    public static void main(String[] args) {
        log.info("STARTING THE APPLICATION");
        SpringApplication.run(GreentradeApp.class, args);
        log.info("CLOSING THE APPLICATION");
    }

    @Override
    public void run(String... args) {
        log.info("EXECUTING : command line runner");


        FinnhubClient client = new FinnhubClient();
        try {
            Quote quote = client.quote("TSLA");
            System.out.println(quote);
            System.out.println("");

            AdditionalProperties appProperties = new AdditionalProperties();
            System.out.println(appProperties.getUnit());
            System.out.println(appProperties.getMax());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    //testPropertyInjection
}
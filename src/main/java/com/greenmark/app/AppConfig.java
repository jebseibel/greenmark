package com.greenmark.app;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
@ConfigurationProperties
public class AppConfig {

    private String email;
    public String finnhubtoken;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFinnhubtoken() {
        return finnhubtoken;
    }

    public void setFinnhubtoken(String finnhubtoken) {
        this.finnhubtoken = finnhubtoken;
    }
}

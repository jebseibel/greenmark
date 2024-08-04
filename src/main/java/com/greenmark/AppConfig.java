package com.greenmark;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
@ConfigurationProperties
public class AppConfig {

    private int threadPool;
    private String email;
    public String finnhubtoken;

    public int getThreadPool() {
        return threadPool;
    }

    public void setThreadPool(int threadPool) {
        this.threadPool = threadPool;
    }

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

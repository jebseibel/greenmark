package com.greenmark.common;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "finnhub")
@Data
@Slf4j
public class DatafeedConfig {
    private String token;
    private int waitTimeInSeconds;
    private int limitPerSecond;
}

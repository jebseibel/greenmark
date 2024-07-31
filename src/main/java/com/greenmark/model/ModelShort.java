package com.greenmark.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

//https://www.baeldung.com/spring-enable-config-properties

@Data
@ConfigurationProperties(prefix = "model-short")
public class ModelShort {

    private String unit;
    private int max;

    public ModelShort() {

    }

}

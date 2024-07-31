package com.greenmark;

import com.greenmark.datafeed.finnhub.client.FinnhubClient;
import com.greenmark.model.ModelLong;
import com.greenmark.model.ModelShort;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.env.ConfigTreePropertySource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.HashMap;

@Configuration
@PropertySource("classpath:application.properties")
@EnableConfigurationProperties({ModelLong.class, ModelShort.class, FinnhubClient.class, AdditionalProperties.class})
public class AppConfig {

    @Autowired
    private AdditionalProperties additionalProperties;


    private HashMap<String, String> allProperties = new HashMap<String, String>();

//    public String getPropertyAsString(String keyString) {
//        allProperties[keyString].toString();
//    }


}

package com.greenmark;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

import java.time.Duration;

@SpringBootTest
class AppConfigTest {

    @Autowired
    AppConfig appConfig;

    @BeforeEach
    void setUp() {
        appConfig = new AppConfig();
    }

//    @Test
//    void getEmail() {
//        String email = appConfig.getEmail();
//        assertNotNull(email);
//        System.out.println(email);
//    }
//
//    @Test
//    void setEmail() {
//    }
}
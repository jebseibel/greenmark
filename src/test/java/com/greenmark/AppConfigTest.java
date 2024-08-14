package com.greenmark;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class AppConfigTest {

    @Autowired
    AppConfig appConfig;

    @BeforeEach
    void setUp() {
        appConfig = new AppConfig();
    }

    @Test
    void getEmail() {
        String email = appConfig.getEmail();
        assertNotNull(email);
        System.out.println(email);
    }

    @Test
    void setEmail() {
    }
}
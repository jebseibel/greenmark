package com.greenmark;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
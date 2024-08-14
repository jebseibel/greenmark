package com.greenmark.app.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = ModelConfigValues.class)
class ModelConfigValuesTest {

    @Autowired
    ModelConfigValues modelConfigValues;

    @Test
    void getModel01Demote() {
        BigDecimal success = new BigDecimal("1.1");
        BigDecimal value = modelConfigValues.getModel_minute01_demote();
        assertEquals(success, value);
    }

}
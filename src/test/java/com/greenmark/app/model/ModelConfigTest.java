package com.greenmark.app.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest()
class ModelConfigTest {

    @Autowired
    ModelConfigValues modelConfigValues;

    ModelConfig modelConfig;

    @BeforeEach
    void setUp() {
        this.modelConfig = new ModelConfig(modelConfigValues);
    }

    @Test
    void testModelConfig() {
        assertNotNull(modelConfig);
    }

    @Nested
    class Model01Tests {

        @Test
        void modelLogic_demote01() {
            BigDecimal success = new BigDecimal("1.1");
            ModelLogic test = modelConfig.getMinute01();
            assertEquals(success, test.getDemote());
        }

        @Test
        void modelLogic_promote01() {
            BigDecimal success = new BigDecimal("1.9");
            ModelLogic test = modelConfig.getMinute01();
            assertEquals(success, test.getPromote());
        }
    }

    @Nested
    class Model05Tests {

        @Test
        void modelLogic_demote05() {
            BigDecimal success = new BigDecimal("5.1");
            ModelLogic test = modelConfig.getMinute05();
            assertEquals(success, test.getDemote());
        }

        @Test
        void modelLogic_promote05() {
            BigDecimal success = new BigDecimal("5.9");
            ModelLogic test = modelConfig.getMinute05();
            assertEquals(success, test.getPromote());
        }
    }

    @Nested
    class Model15Tests {

        @Test
        void modelLogic_demote15() {
            BigDecimal success = new BigDecimal("15.1");
            ModelLogic test = modelConfig.getMinute15();
            assertEquals(success, test.getDemote());
        }

        @Test
        void modelLogic_promote15() {
            BigDecimal success = new BigDecimal("15.9");
            ModelLogic test = modelConfig.getMinute15();
            assertEquals(success, test.getPromote());
        }
    }

    @Nested
    class Model60Tests {

        @Test
        void modelLogic_demote60() {
            BigDecimal success = new BigDecimal("60.1");
            ModelLogic test = modelConfig.getMinute60();
            assertEquals(success, test.getDemote());
        }

        @Test
        void modelLogic_promote60() {
            BigDecimal success = new BigDecimal("60.9");
            ModelLogic test = modelConfig.getMinute60();
            assertEquals(success, test.getPromote());
        }
    }

    @Nested
    class ModelDDTests {

        @Test
        void modelLogic_demoteDD() {
            BigDecimal success = new BigDecimal("100.1");
            ModelLogic test = modelConfig.getMinuteDD();
            assertEquals(success, test.getDemote());
        }

        @Test
        void modelLogic_promoteDD() {
            BigDecimal success = new BigDecimal("100.9");
            ModelLogic test = modelConfig.getMinuteDD();
            assertEquals(success, test.getPromote());
        }
    }

    @Nested
    class ModelAllTests {

        @Test
        void modelLogic_demoteAll() {
            BigDecimal success = new BigDecimal("999.1");
            ModelLogic test = modelConfig.getMinuteAll();
            assertEquals(success, test.getDemote());
        }

        @Test
        void modelLogic_promoteAll() {
            BigDecimal success = new BigDecimal("999.9");
            ModelLogic test = modelConfig.getMinuteAll();
            assertEquals(success, test.getPromote());
        }
    }
}
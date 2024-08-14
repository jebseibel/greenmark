package com.greenmark.app.model;

import com.greenmark.bootstrap.ModelLogic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = ModelLogic.class)
class ModelLogicTest {

    @Autowired
    ModelLogic modelLogic;

    @BeforeEach
    void setUp() {
        modelLogic = new ModelLogic();
    }

    @Test
    void printModelLogic() {
        assertNotNull(modelLogic);
    }

}
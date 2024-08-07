package com.greenmark.app;

import com.greenmark.bootstrap.ModelLogic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest()
@EnableConfigurationProperties({ModelLogic.class})
class ModelLogicTest {

    @Autowired
    ModelLogic modelLogic;

    @Test
    void printModelLogic() {
        System.out.println(modelLogic);
        assertNotNull(modelLogic.getStockMinPrice());
    }

}
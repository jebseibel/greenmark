package com.greenmark.app.buckets;

import com.greenmark.common.database.domain.StockWatch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class BucketDataTest {

    @Autowired
    BucketData bucketData;

    @BeforeEach
    void setUp() {
        bucketData = new BucketData();
        bucketData.setName("test");
        bucketData.setDemote(BigDecimal.ONE);
        bucketData.setPromote(BigDecimal.TWO);

        StockWatch stockWatch1 = new StockWatch();
        stockWatch1.setSymbol("test1");
        StockWatch stockWatch2 = new StockWatch();
        stockWatch2.setSymbol("test2");
//        bucketData.addItem(stockWatch1);
//        bucketData.addItem(stockWatch2);
    }

    @Test
    void testToString() {
        System.out.println(bucketData.toString());
    }
}
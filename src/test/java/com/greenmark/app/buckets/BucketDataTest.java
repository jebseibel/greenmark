package com.greenmark.app.buckets;

import com.greenmark.common.database.domain.StockWatchDb;
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

        StockWatchDb stockWatchDb1 = new StockWatchDb();
        stockWatchDb1.setSymbol("test1");
        StockWatchDb stockWatchDb2 = new StockWatchDb();
        stockWatchDb2.setSymbol("test2");
//        bucketData.addItem(stockWatchDb1);
//        bucketData.addItem(stockWatchDb2);
    }

    @Test
    void testToString() {
        System.out.println(bucketData.toString());
    }
}
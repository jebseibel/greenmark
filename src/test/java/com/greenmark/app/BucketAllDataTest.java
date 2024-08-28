package com.greenmark.app;

import com.greenmark.app.buckets.BucketData;
import com.greenmark.common.database.domain.StockWatchDb;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BucketAllDataTest {

    @Autowired
    BucketAllData bucketAllData;

    @BeforeEach
    void setUp() {
//        BucketData bucketData = new BucketData();
//        bucketData.setName("test");
//        bucketData.setDemote(BigDecimal.ONE);
//        bucketData.setPromote(BigDecimal.TWO);

        StockWatchDb stockWatchDb01 = new StockWatchDb();
        stockWatchDb01.setSymbol("minute01");

        StockWatchDb stockWatchDb05 = new StockWatchDb();
        stockWatchDb05.setSymbol("minute05");

        StockWatchDb stockWatchDb15 = new StockWatchDb();
        stockWatchDb15.setSymbol("minute15");

        StockWatchDb stockWatchDb60 = new StockWatchDb();
        stockWatchDb05.setSymbol("minute60");

        StockWatchDb stockWatchDbDD = new StockWatchDb();
        stockWatchDbDD.setSymbol("minuteDD");

        bucketAllData.getBucket01Data().addItem(stockWatchDb01);
//        bucketAllData.getBucket05Data().addItem(stockWatchDb05);
//        bucketAllData.getBucket15Data().addItem(stockWatchDb15);
//        bucketAllData.getBucket60Data().addItem(stockWatchDb60);
//        bucketAllData.getBucketDailyData().addItem(stockWatchDbDD);

    }

    @Test
    void init() {
        assertNotNull(bucketAllData);

        System.out.println(bucketAllData);
    }

    @Test
    void testToString() {
        System.out.println(bucketAllData);
    }

    @Test
    void getBucket01Data() {

    }

    @Test
    void setBucket01Data() {
    }
}
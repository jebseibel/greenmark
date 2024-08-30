package com.greenmark.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.greenmark.app.buckets.BucketData;
import com.greenmark.app.buckets.SolarEvent;
import com.greenmark.app.buckets.SolarEventContainer;
import com.greenmark.common.database.domain.StockWatchDb;
import com.greenmark.database.DomainBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
    void createFromJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        BucketAllData allData =
                objectMapper.readValue(new File("src/test/resources/all_bucket.json"),
                        BucketAllData.class);

        List<SolarEvent> events = container.getSolarEvents();
        Collections.sort(events, Comparator.comparingInt(event -> event.getSpeedKmPerS()));

        assertEquals(100, events.get(0).getSpeedKmPerS());
        assertEquals(500, events.get(1).getSpeedKmPerS());
        assertEquals(1000, events.get(2).getSpeedKmPerS());
        assertEquals(1500, events.get(3).getSpeedKmPerS());
    }

    @Test
    void writetoJson() throws JsonProcessingException {
        BucketData bucket01 = DomainBuilder.getBucketData("minute01");
        BucketData bucket05 = DomainBuilder.getBucketData("minute05");
        BucketData bucket15 = DomainBuilder.getBucketData("minute15");
        BucketData bucket60 = DomainBuilder.getBucketData("minute60");
        BucketData bucketDD = DomainBuilder.getBucketData("daily");

        BucketAllData allData = new BucketAllData(bucket01, bucket05, bucket15, bucket60, bucketDD);
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(allData);
        System.out.println(jsonStr);
    }
}
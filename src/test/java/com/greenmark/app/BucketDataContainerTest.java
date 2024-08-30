package com.greenmark.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.greenmark.app.buckets.BucketData;
import com.greenmark.common.database.domain.StockWatchDb;
import com.greenmark.common.enums.TimeframeType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BucketDataContainerTest {

    @Autowired
    BucketDataContainer bucketDataContainer;

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

//        bucketDataContainer.getBucket01Data().addItem(stockWatchDb01);
//        bucketAllData.getBucket05Data().addItem(stockWatchDb05);
//        bucketAllData.getBucket15Data().addItem(stockWatchDb15);
//        bucketAllData.getBucket60Data().addItem(stockWatchDb60);
//        bucketAllData.getBucketDailyData().addItem(stockWatchDbDD);

    }

    @Test
    void init() {
        assertNotNull(bucketDataContainer);

        System.out.println(bucketDataContainer);
    }

    @Test
    void testToString() {
        System.out.println(bucketDataContainer);
    }

    @Test
    void getBucket01Data() {

    }

    @Test
    void createFromJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        BucketDataContainer allData =
                objectMapper.readValue(new File("src/test/resources/all_bucket.json"),
                        BucketDataContainer.class);

        List<BucketData> bucketData = allData.getBucketDataList();
        assertNotNull(bucketData);
    }

    @Test
    void writetoJson_simple() throws JsonProcessingException {
        BucketDataContainer allData = DomainBuilderApp.getBucketDataContainer();
        ObjectMapper mapper = new ObjectMapper();

        //test
        String jsonStr = mapper.writeValueAsString(allData);

        //validate
        assertNotNull(jsonStr);
    }

    @Test
    void writetoJson_pretty() throws JsonProcessingException {
        BucketDataContainer allData = DomainBuilderApp.getBucketDataContainer();
        ObjectMapper mapper = new ObjectMapper();

        //test
        String jsonStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(allData);

        //validate
        assertNotNull(jsonStr);
    }

    @Test
    void writetoJson_complex() throws JsonProcessingException {

        BucketData bucket01 = DomainBuilderApp.getBucketData("minute01",1, TimeframeType.MINUTE01, 1);
        BucketData bucket05 = DomainBuilderApp.getBucketData("minute05",2, TimeframeType.MINUTE05, 2);
        BucketData bucket15 = DomainBuilderApp.getBucketData("minute15", 3, TimeframeType.MINUTE15, 1);
        BucketData bucket60 = DomainBuilderApp.getBucketData("minute60",4, TimeframeType.MINUTE60, 3);
        BucketData bucketDD = DomainBuilderApp.getBucketData("daily",5, TimeframeType.DAILY, 5);
        List<BucketData> list = List.of(bucket01, bucket05, bucket15, bucket60, bucketDD);
        BucketDataContainer bucketDataContainer = new BucketDataContainer(list);

        //test
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(bucketDataContainer);
        System.out.println(jsonStr);

        //validate
        assertNotNull(jsonStr);
    }
}
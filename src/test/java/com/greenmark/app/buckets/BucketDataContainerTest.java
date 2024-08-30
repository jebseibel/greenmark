package com.greenmark.app.buckets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.greenmark.app.DomainBuilderApp;
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
        BucketDataContainer bucketDataContainer = new BucketDataContainer();
        bucketDataContainer.setBucketDataList(list);

        //test
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(bucketDataContainer);
        System.out.println(jsonStr);

        //validate
        assertNotNull(jsonStr);
    }
}
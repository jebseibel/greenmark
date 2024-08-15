package com.greenmark.app.buckets;

import com.greenmark.common.database.domain.BucketData;
import com.greenmark.common.database.domain.BucketMinute60Db;
import com.greenmark.database.DomainBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Bucket60ServiceTest {

    Bucket60Service service;
    String strBasis = "2";
    BigDecimal promote = DomainBuilder.getBigDecimal("3");
    BigDecimal basis = DomainBuilder.getBigDecimal(strBasis);
    BucketMinute60Db bucketBasis = DomainBuilder.getBucketMinute60DbMacd(basis);
    BigDecimal demote = DomainBuilder.getBigDecimal("1");
    BucketMinute60Db bucketPromote = DomainBuilder.getBucketMinute60DbMacd(promote);
    BucketMinute60Db bucketDemote = DomainBuilder.getBucketMinute60DbMacd(demote);

    @BeforeEach
    void setUp() {
        service = new Bucket60Service();
        service.setPromote(promote);
        service.setDemote(demote);
        service.addItem(bucketPromote);
        service.addItem(bucketBasis);
        service.addItem(bucketDemote);
    }

    @Nested
    class PromoteTests {

        BigDecimal testPromoteValue = DomainBuilder.getBigDecimal(strBasis);

        @Test
        void getAllPromote() {
            // setup
            service.setPromote(testPromoteValue);

            // test
            List<BucketData> result = service.getAllPromote();
            assertEquals(1, result.size());
        }

        @Test
        void getAllPromote_two() {
            // setup
            service.setPromote(testPromoteValue);
            BucketMinute60Db bucketTest = DomainBuilder.getBucketMinute60DbMacd(promote);
            service.addItem(bucketTest);

            // test
            List<BucketData> result = service.getAllPromote();
            assertEquals(2, result.size());
        }
    }

    @Nested
    class DemoteTests {

        BigDecimal testDemoteValue = DomainBuilder.getBigDecimal(strBasis);

        @Test
        void getAllDemote() {
            // setup
            BucketMinute60Db bucketTest = DomainBuilder.getBucketMinute60DbMacd(demote);
            service.addItem(bucketTest);
            service.setDemote(testDemoteValue);

            // test
            List<BucketData> result = service.getAllDemote();
            assertEquals(2, result.size());
        }

        @Test
        void getAllDemote_two() {
            // setup
            BucketMinute60Db bucketTest = DomainBuilder.getBucketMinute60DbMacd(demote);
            service.addItem(bucketTest);
            service.setDemote(testDemoteValue);

            // test
            List<BucketData> result = service.getAllDemote();
            assertEquals(2, result.size());
        }
    }
}
package com.greenmark.app;

import com.greenmark.common.database.domain.BucketMinute60Db;
import com.greenmark.database.db.DomainBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Bucket60ServiceTest {

    Bucket60Service service;
    String strBasis = "2";
    BigDecimal promote = DomainBuilder.getBigDecimal("3");
    BigDecimal basis = DomainBuilder.getBigDecimal(strBasis);
    BigDecimal demote = DomainBuilder.getBigDecimal("1");
    BucketMinute60Db bucketPromote = DomainBuilder.getBucketMinute60DbMacd(promote);
    BucketMinute60Db bucketBasis = DomainBuilder.getBucketMinute60DbMacd(basis);
    BucketMinute60Db bucketDemote = DomainBuilder.getBucketMinute60DbMacd(demote);

    @BeforeEach
    void setUp() {
        service = new Bucket60Service(promote, demote);
        service.addItem(bucketPromote);
        service.addItem(bucketBasis);
        service.addItem(bucketDemote);
    }

    @Test
    void addItem() {
        assertEquals(2, service.size());
        service.addItem(DomainBuilder.getBucketMinute60Db());
        assertEquals(3, service.size());
    }

    @Test
    void removeItem() {
        assertEquals(2, service.size());
        service.removeItem(bucketDemote);
        assertEquals(1, service.size());
    }

    @Test
    void getPromote(){
        BigDecimal promote = service.getPromote();
        assertNotNull(promote);
    }

    @Test
    void getDemote(){
        BigDecimal demote = service.getDemote();
        assertNotNull(demote);
    }

    @Nested
    class PromoteTests {

        BigDecimal testPromoteValue = DomainBuilder.getBigDecimal(strBasis);

        @Test
        void getAllPromote() {
            // setup
            service.setPromote(testPromoteValue);

            // test
            List<BucketMinute60Db> result = service.getAllPromote();
            assertEquals(1, result.size());
        }

        @Test
        void getAllPromote_two() {
            // setup
            service.setPromote(testPromoteValue);
            BucketMinute60Db bucketTest= DomainBuilder.getBucketMinute60DbMacd(promote);
            service.addItem(bucketTest);

            // test
            List<BucketMinute60Db> result = service.getAllPromote();
            assertEquals(2, result.size());
        }
    }

    @Nested
    class DemoteTests {

        BigDecimal testDemoteValue = DomainBuilder.getBigDecimal(strBasis);

        @Test
        void getAllDemote() {
            // setup
            BucketMinute60Db bucketTest= DomainBuilder.getBucketMinute60DbMacd(demote);
            service.addItem(bucketTest);
            service.setDemote(testDemoteValue);

            // test
            List<BucketMinute60Db> result = service.getAllDemote();
            assertEquals(1, result.size());
        }

        @Test
        void getAllDemote_two() {
            // setup
            BucketMinute60Db bucketTest= DomainBuilder.getBucketMinute60DbMacd(demote);
            service.addItem(bucketTest);
            service.setDemote(testDemoteValue);

            // test
            List<BucketMinute60Db> result = service.getAllDemote();
            assertEquals(2, result.size());
        }
    }
}
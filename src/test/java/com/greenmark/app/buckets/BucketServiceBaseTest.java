package com.greenmark.app.buckets;

import com.greenmark.common.database.domain.BucketMinute60Db;
import com.greenmark.database.DomainBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BucketServiceBaseTest {

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

    @Test
    void addItem() {
        assertEquals(3, service.size());
        service.addItem(DomainBuilder.getBucketMinute60Db());
        assertEquals(4, service.size());
    }

    @Test
    void removeItem() {
        assertEquals(3, service.size());
        service.removeItem(bucketDemote);
        assertEquals(2, service.size());
    }

    @Test
    void getPromote() {
        BigDecimal promote = service.getPromote();
        assertNotNull(promote);
    }

    @Test
    void getDemote() {
        BigDecimal demote = service.getDemote();
        assertNotNull(demote);
    }
}
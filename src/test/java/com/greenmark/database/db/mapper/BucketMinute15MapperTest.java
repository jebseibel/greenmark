package com.greenmark.database.db.mapper;

import com.greenmark.common.database.domain.BucketMinute15Db;
import com.greenmark.common.database.domain.BucketMinute15Db;
import com.greenmark.database.db.DomainBuilder;
import com.greenmark.database.db.entity.BucketMinute15;
import com.greenmark.database.db.entity.BucketMinute15;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BucketMinute15MapperTest {

    @Test
    void testToDb() {
        BucketMinute15 item = DomainBuilder.getBucketMinute15();
        BucketMinute15Db itemDb = BucketMinute15Mapper.toDb(item);

        //test
        assertEquals(item.getSymbol(), itemDb.getSymbol());
        assertEquals(item.getCurrent(), itemDb.getCurrent());
        assertEquals(item.getOpen(), itemDb.getOpen());
        assertEquals(item.getHigh(), itemDb.getHigh());
        assertEquals(item.getLow(), itemDb.getLow());
        assertEquals(item.getLow(), itemDb.getLow());
        assertEquals(item.getChanged(), itemDb.getChanged());
        assertEquals(item.getChangedPercent(), itemDb.getChangedPercent());
        assertEquals(item.getCreatedAt(), itemDb.getCreatedAt());
        assertEquals(item.getModifiedAt(), itemDb.getModifiedAt());
        assertEquals(item.getDeletedAt(), itemDb.getDeletedAt());
        assertEquals(item.getActive(), itemDb.getActive());
    }

    @Test
    void testToList() {
        BucketMinute15 item1 = DomainBuilder.getBucketMinute15();
        BucketMinute15 item2 = DomainBuilder.getBucketMinute15();
        List<BucketMinute15> items = Arrays.asList(item1, item2);
        List<BucketMinute15Db> itemDbs = BucketMinute15Mapper.toList(items);

        //test
        assertEquals(items.size(), itemDbs.size());
        assertTrue(items.size() == 2);
        assertTrue(itemDbs.size() == 2);
    }    
}
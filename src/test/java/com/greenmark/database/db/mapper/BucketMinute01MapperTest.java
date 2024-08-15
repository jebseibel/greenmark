package com.greenmark.database.db.mapper;

import com.greenmark.common.database.domain.BucketMinute01Db;
import com.greenmark.database.DomainBuilder;
import com.greenmark.database.db.entity.BucketMinute01;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class BucketMinute01MapperTest {

    @Autowired
    BucketMinute01Mapper mapper;

    @Test
    void testToDb() {
        BucketMinute01 item = DomainBuilder.getBucketMinute01();
        BucketMinute01Db itemDb = mapper.toDb(item);

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
        BucketMinute01 item1 = DomainBuilder.getBucketMinute01();
        BucketMinute01 item2 = DomainBuilder.getBucketMinute01();
        List<BucketMinute01> items = Arrays.asList(item1, item2);
        List<BucketMinute01Db> itemDbs = mapper.toList(items);

        //test
        assertEquals(items.size(), itemDbs.size());
        assertEquals(2, items.size());
        assertEquals(2, itemDbs.size());
    }
}
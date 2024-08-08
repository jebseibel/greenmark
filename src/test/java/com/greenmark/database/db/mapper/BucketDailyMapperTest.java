package com.greenmark.database.db.mapper;

import com.greenmark.common.database.domain.BucketDailyDb;
import com.greenmark.common.database.domain.BucketMinute60Db;
import com.greenmark.database.db.DomainBuilder;
import com.greenmark.database.db.entity.BucketDaily;
import com.greenmark.database.db.entity.BucketMinute60;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class BucketDailyMapperTest {

    @Autowired
    BucketDailyMapper mapper;

    @Test
    void testToDb() {
        BucketDaily item = DomainBuilder.getBucketDaily();
        BucketDailyDb itemDb = mapper.toDb(item);

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

//    @Test
//    void testToList() {
//        BucketDaily item1 = DomainBuilder.getBucketDaily();
//        BucketDaily item2 = DomainBuilder.getBucketDaily();
//        List<BucketDaily> items = Arrays.asList(item1, item2);
//        List<BucketDailyDb> itemDbs = BucketDailyMapper.toList(items);
//
//        //test
//        assertEquals(items.size(), itemDbs.size());
//        assertTrue(items.size() == 2);
//        assertTrue(itemDbs.size() == 2);
//    }
}
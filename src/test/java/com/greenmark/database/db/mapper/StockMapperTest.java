package com.greenmark.database.db.mapper;

import com.greenmark.database.db.DomainBuilder;
import com.greenmark.common.database.domain.StockDb;
import com.greenmark.database.db.entity.StockEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StockMapperTest {

    @Test
    void testToDb() {
        StockEntity item = DomainBuilder.getStock();
        StockDb itemDb = StockMapper.toDb(item);

        //test
        assertEquals(item.getExtid(), itemDb.getExtid());
        assertEquals(item.getName(), itemDb.getName());
        assertEquals(item.getDescription(), itemDb.getDescription());
        assertEquals(item.getCreatedAt(), itemDb.getCreatedAt());
        assertEquals(item.getModifiedAt(), itemDb.getModifiedAt());
        assertEquals(item.getDeletedAt(), itemDb.getDeletedAt());
        assertEquals(item.getActive(), itemDb.getActive());
    }
}
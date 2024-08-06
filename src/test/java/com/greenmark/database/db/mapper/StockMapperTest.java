package com.greenmark.database.db.mapper;

import com.greenmark.common.database.domain.StockDb;
import com.greenmark.database.db.DomainBuilder;
import com.greenmark.common.database.domain.StockDb;
import com.greenmark.database.db.entity.Stock;
import com.greenmark.database.db.entity.Stock;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StockMapperTest {

    @Test
    void testToDb() {
        Stock item = DomainBuilder.getStock();
        StockDb itemDb = StockMapper.toDb(item);

        //test
        assertEquals(item.getExtid(), itemDb.getExtid());
        assertEquals(item.getName(), itemDb.getName());
        assertEquals(item.getSymbol(), itemDb.getSymbol());
        assertEquals(item.getCreatedAt(), itemDb.getCreatedAt());
        assertEquals(item.getModifiedAt(), itemDb.getModifiedAt());
        assertEquals(item.getDeletedAt(), itemDb.getDeletedAt());
        assertEquals(item.getActive(), itemDb.getActive());
    }

    @Test
    void testToList() {
        Stock item1 = DomainBuilder.getStock();
        Stock item2 = DomainBuilder.getStock();
        List<Stock> items = Arrays.asList(item1, item2);
        List<StockDb> itemDbs =StockMapper.toList(items);

        //test
        assertEquals(items.size(), itemDbs.size());
        assertTrue(items.size() == 2);
        assertTrue(itemDbs.size() == 2);
    }
}
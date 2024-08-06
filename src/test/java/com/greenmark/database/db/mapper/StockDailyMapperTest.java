package com.greenmark.database.db.mapper;

import com.greenmark.common.database.domain.StockDailyDb;

import com.greenmark.database.db.DomainBuilder;
import com.greenmark.database.db.entity.StockDaily;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StockDailyMapperTest {

    @Test
    void testToDb() {
        StockDaily item = DomainBuilder.getStockDaily();
        StockDailyDb itemDb = StockDailyMapper.toDb(item);

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
       StockDaily item1 = DomainBuilder.getStockDaily();
       StockDaily item2 = DomainBuilder.getStockDaily();
        List<StockDaily> items = Arrays.asList(item1, item2);
        List<StockDailyDb> itemDbs =StockDailyMapper.toList(items);

        //test
        assertEquals(items.size(), itemDbs.size());
        assertTrue(items.size() == 2);
        assertTrue(itemDbs.size() == 2);
    }    
}
package com.greenmark.database.db.mapper;

import com.greenmark.common.database.domain.StockDailyDb;

import com.greenmark.database.db.DomainBuilder;
import com.greenmark.database.db.entity.StockDaily;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StockNightlyMapperTest {

    @Test
    void testToDb() {
        StockDaily item = DomainBuilder.getStockNightly();
        StockDailyDb itemDb = StockDailyMapper.toDb(item);

        //test
        assertEquals(item.getSymbol(), itemDb.getSymbol());
        assertEquals(item.getOpen(), itemDb.getOpen());
        assertEquals(item.getClose(), itemDb.getClose());
        assertEquals(item.getHigh(), itemDb.getHigh());
        assertEquals(item.getLow(), itemDb.getLow());
        assertEquals(item.getVolume(), itemDb.getVolume());
        assertEquals(item.getMacd(), itemDb.getMacd());
        assertEquals(item.getStochk(), itemDb.getStochk());
        assertEquals(item.getCreatedAt(), itemDb.getCreatedAt());
        assertEquals(item.getModifiedAt(), itemDb.getModifiedAt());
        assertEquals(item.getDeletedAt(), itemDb.getDeletedAt());
        assertEquals(item.getActive(), itemDb.getActive());
    }
}
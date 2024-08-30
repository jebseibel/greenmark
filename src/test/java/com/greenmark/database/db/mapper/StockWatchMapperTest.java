package com.greenmark.database.db.mapper;

import com.greenmark.common.database.domain.StockWatchDb;
import com.greenmark.database.DomainBuilderDatabase;
import com.greenmark.database.db.entity.StockWatch;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class StockWatchMapperTest {

    @Autowired
    StockWatchMapper mapper;

    @Test
    void testToDb() {
        StockWatch item = DomainBuilderDatabase.getStockWatchDaily();
        StockWatchDb itemDb = mapper.toDb(item);

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
        StockWatch item1 = DomainBuilderDatabase.getStockWatchDaily();
        StockWatch item2 = DomainBuilderDatabase.getStockWatchDaily();
        List<StockWatch> items = Arrays.asList(item1, item2);
        List<StockWatchDb> itemDbs = mapper.toList(items);

        //test
        assertEquals(items.size(), itemDbs.size());
        assertEquals(2, items.size());
        assertEquals(2, itemDbs.size());
    }
}
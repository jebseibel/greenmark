package com.greenmark.database.db.mapper;

import com.greenmark.common.database.domain.StockDb;
import com.greenmark.database.DomainBuilder;
import com.greenmark.database.db.entity.Stock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class StockMapperTest {

    @Autowired
    StockMapper mapper;

    @Test
    void testToDb() {
        Stock item = DomainBuilder.getStock();
        StockDb itemDb = mapper.toDb(item);

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
        List<StockDb> itemDbs = mapper.toList(items);

        //test
        assertEquals(items.size(), itemDbs.size());
        assertEquals(2, items.size());
        assertEquals(2, itemDbs.size());
    }
}
package com.greenmark.database.db.mapper;

import com.greenmark.common.database.domain.Stock;
import com.greenmark.database.DomainBuilderDatabase;
import com.greenmark.database.db.entity.StockDb;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class StockDbMapperTest {

    @Autowired
    StockMapper mapper;

    @Test
    void testToDb() {
        StockDb item = DomainBuilderDatabase.getStock();
        Stock itemDb = mapper.toEntity(item);

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
        StockDb item1 = DomainBuilderDatabase.getStock();
        StockDb item2 = DomainBuilderDatabase.getStock();
        List<StockDb> items = Arrays.asList(item1, item2);
        List<Stock> itemDbs = mapper.toList(items);

        //test
        assertEquals(items.size(), itemDbs.size());
        assertEquals(2, items.size());
        assertEquals(2, itemDbs.size());
    }
}
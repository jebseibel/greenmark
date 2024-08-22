package com.greenmark.database.db.mapper;

import com.greenmark.common.database.domain.ScenarioDb;
import com.greenmark.database.DomainBuilder;
import com.greenmark.database.db.entity.Scenario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ScenarioMapperTest {

    @Autowired
    ScenarioMapper mapper;

    @Test
    void testToDb() {
        Scenario item = DomainBuilder.getScenario();
        ScenarioDb itemDb = mapper.toDb(item);

        //test
        assertEquals(item.getExtid(), itemDb.getExtid());
        assertEquals(item.getName(), itemDb.getName());
        assertEquals(item.getDescription(), itemDb.getDescription());
        assertEquals(item.getCreatedAt(), itemDb.getCreatedAt());
        assertEquals(item.getModifiedAt(), itemDb.getModifiedAt());
        assertEquals(item.getDeletedAt(), itemDb.getDeletedAt());
        assertEquals(item.getActive(), itemDb.getActive());
    }

    @Test
    void testToList() {
        Scenario item1 = DomainBuilder.getScenario();
        Scenario item2 = DomainBuilder.getScenario();
        List<Scenario> items = Arrays.asList(item1, item2);
        List<ScenarioDb> itemDbs = mapper.toList(items);

        //test
        assertEquals(items.size(), itemDbs.size());
        assertEquals(2, items.size());
        assertEquals(2, itemDbs.size());
    }
}
package com.greenmark.database.db.mapper;

import com.greenmark.database.db.DomainBuilder;
import com.greenmark.common.database.domain.ScenarioDb;
import com.greenmark.database.db.entity.Scenario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
}
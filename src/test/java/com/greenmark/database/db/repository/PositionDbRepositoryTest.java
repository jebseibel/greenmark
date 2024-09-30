package com.greenmark.database.db.repository;

import com.greenmark.common.enums.ActiveEnum;
import com.greenmark.database.DomainBuilderDatabase;
import com.greenmark.database.db.entity.PositionDb;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PositionDbRepositoryTest {

    @Autowired
    private PositionRepository repository;

    @Nested
    class SuiteCrud {

        @Test
        void create() {
            PositionDb item = DomainBuilderDatabase.getPosition();
            assertNull(item.getId());
            PositionDb result = repository.save(item);

            //test
            assertNotNull(result);
            assertTrue(result.getId() != 0);
            assertNotNull(item.getCreatedAt());
        }

        @Test
        void update() {
            PositionDb item = DomainBuilderDatabase.getPosition();
            assertNull(item.getId());
            assertNull(item.getModifiedAt());
            PositionDb record = repository.save(item);

            //now update
            String changedSymbol = DomainBuilderDatabase.getSymbolRandom();
            record.setSymbol(changedSymbol);
            record.setModifiedAt(LocalDateTime.now());
            PositionDb resultUpdate = repository.save(record);

            //test
            assertNotNull(resultUpdate);
            assertEquals(resultUpdate.getSymbol(), changedSymbol);
            assertNotNull(resultUpdate.getModifiedAt());
        }

        @Test
        void delete() {
            PositionDb item = DomainBuilderDatabase.getPosition();
            assertNull(item.getId());
            assertNull(item.getDeletedAt());
            PositionDb record = repository.save(item);

            //now update
            record.setDeletedAt(LocalDateTime.now());
            PositionDb resultUpdate = repository.save(record);

            //test
            assertNotNull(resultUpdate);
            assertNotNull(resultUpdate.getDeletedAt());
        }
    }

    @Nested
    class SuiteFind {

        @Test
        void findById() {
            String name = "name" + DomainBuilderDatabase.randomString();
            PositionDb record = DomainBuilderDatabase.getPosition(name);
            PositionDb item = repository.save(record);
            PositionDb result = repository.findById(item.getId()).get();

            //test
            assertNotNull(result);
            assertEquals(result.getName(), name);
        }

        @Test
        void findByExtid() {
            String extid = UUID.randomUUID().toString();
            PositionDb record = DomainBuilderDatabase.getPosition();
            record.setExtid(extid);

            repository.save(record);
            PositionDb result = repository.findByExtid(extid).get();

            //test
            assertNotNull(result);
            assertEquals(result.getExtid(), extid);
        }

        @Test
        void findByName() {
            String name = "name" + DomainBuilderDatabase.randomString();
            PositionDb record = DomainBuilderDatabase.getPosition(name);

            repository.save(record);
            PositionDb result = repository.findByName(name).get();

            //test
            assertNotNull(result);
            assertEquals(result.getName(), name);
        }

        @Test
        void findActive_toList() {
            String symbol1 = DomainBuilderDatabase.getSymbolRandom();
            String symbol2 = DomainBuilderDatabase.getSymbolRandom();
            PositionDb record1 = DomainBuilderDatabase.getPosition(symbol1);
            PositionDb record2 = DomainBuilderDatabase.getPosition(symbol2);
            repository.save(record1);
            repository.save(record2);

            List<PositionDb> results = repository.findByActive(ActiveEnum.ACTIVE.value);

            //test
            assertNotNull(results);
            assertTrue(results.size() > 1);
        }

        @Test
        void findActive_checkNoInactive() {
            String symbol1 = DomainBuilderDatabase.getSymbolRandom();
            String symbol2 = DomainBuilderDatabase.getSymbolRandom();
            PositionDb record1 = DomainBuilderDatabase.getPosition(symbol1);
            PositionDb record2 = DomainBuilderDatabase.getPosition(symbol2);
            record2.setActive(ActiveEnum.INACTIVE.value);
            repository.save(record1);
            repository.save(record2);

            List<PositionDb> results = repository.findByActive(ActiveEnum.ACTIVE.value);

            //test
            assertNotNull(results);
            assertTrue(results.size() > 0);
            assertFalse(results.contains(record2));
        }
    }
}
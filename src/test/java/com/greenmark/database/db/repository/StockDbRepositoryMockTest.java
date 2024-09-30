package com.greenmark.database.db.repository;

import com.greenmark.common.enums.ActiveEnum;
import com.greenmark.database.DomainBuilderDatabase;
import com.greenmark.database.db.entity.StockDb;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StockDbRepositoryMockTest {

    @Autowired
    private StockRepository repository;

    @Nested
    class SuiteCrud {

        @Test
        void create() {
            StockDb item = DomainBuilderDatabase.getStock();
            assertNull(item.getId());
            StockDb result = repository.save(item);

            //test
            assertNotNull(result);
            assertTrue(result.getId() != 0);
            assertNotNull(item.getCreatedAt());
        }

        @Test
        void createUniqueName() {
            String name = "notUnique_" + DomainBuilderDatabase.randomString();
            StockDb item1 = DomainBuilderDatabase.getStock(name);
            StockDb item2 = DomainBuilderDatabase.getStock(name);

            try {
                repository.save(item1);
                repository.save(item2);
                fail();
            } catch (DataIntegrityViolationException e) {
                assertTrue(true);
            }
            System.out.println();
        }

        @Test
        void update() {
            StockDb item = DomainBuilderDatabase.getStock();
            assertNull(item.getId());
            assertNull(item.getModifiedAt());
            StockDb record = repository.save(item);

            //now update
            String changedSymbol = DomainBuilderDatabase.getSymbolRandom();
            record.setSymbol(changedSymbol);
            record.setModifiedAt(LocalDateTime.now());
            StockDb resultUpdate = repository.save(record);

            //test
            assertNotNull(resultUpdate);
            assertEquals(resultUpdate.getSymbol(), changedSymbol);
            assertNotNull(resultUpdate.getModifiedAt());
        }

        @Test
        void delete() {
            StockDb item = DomainBuilderDatabase.getStock();
            assertNull(item.getId());
            assertNull(item.getDeletedAt());
            StockDb record = repository.save(item);

            //now update
            record.setDeletedAt(LocalDateTime.now());
            StockDb resultUpdate = repository.save(record);

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
            StockDb record = DomainBuilderDatabase.getStock(name);
            StockDb item = repository.save(record);
            StockDb result = repository.findById(item.getId()).get();

            //test
            assertNotNull(result);
            assertEquals(result.getName(), name);
        }

        @Test
        void findByExtid() {
            String extid = UUID.randomUUID().toString();
            StockDb record = DomainBuilderDatabase.getStock();
            record.setExtid(extid);

            repository.save(record);
            StockDb result = repository.findByExtid(extid);

            //test
            assertNotNull(result);
            assertEquals(result.getExtid(), extid);
        }

        @Test
        void findByName() {
            String name = "name" + DomainBuilderDatabase.randomString();
            StockDb record = DomainBuilderDatabase.getStock(name);

            repository.save(record);
            StockDb result = repository.findByName(name);

            //test
            assertNotNull(result);
            assertEquals(result.getName(), name);
        }

        @Test
        void findActive_toList() {
            String symbol1 = DomainBuilderDatabase.getSymbolRandom();
            String symbol2 = DomainBuilderDatabase.getSymbolRandom();
            StockDb record1 = DomainBuilderDatabase.getStock(symbol1);
            StockDb record2 = DomainBuilderDatabase.getStock(symbol2);
            repository.save(record1);
            repository.save(record2);

            List<StockDb> results = repository.findByActive(ActiveEnum.ACTIVE.value);

            //test
            assertNotNull(results);
            assertTrue(results.size() > 1);
        }

        @Test
        void findActive_checkNoInactive() {
            String symbol1 = DomainBuilderDatabase.getSymbolRandom();
            String symbol2 = DomainBuilderDatabase.getSymbolRandom();
            StockDb record1 = DomainBuilderDatabase.getStock(symbol1);
            StockDb record2 = DomainBuilderDatabase.getStock(symbol2);
            record2.setActive(ActiveEnum.INACTIVE.value);
            repository.save(record1);
            repository.save(record2);

            List<StockDb> results = repository.findByActive(ActiveEnum.ACTIVE.value);

            //test
            assertNotNull(results);
            assertTrue(results.size() > 0);
            assertFalse(results.contains(record2));
        }
    }
}
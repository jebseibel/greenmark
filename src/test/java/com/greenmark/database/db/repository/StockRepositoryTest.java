package com.greenmark.database.db.repository;

import com.greenmark.common.enums.ActiveEnum;
import com.greenmark.database.db.DomainBuilder;
import com.greenmark.database.db.entity.Stock;
import com.greenmark.database.db.entity.Stock;
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
class StockRepositoryTest {

    @Autowired
    private StockRepository repository;

    @Nested
    class SuiteCrud {

        @Test
        void create() {
            Stock item = DomainBuilder.getStock();
            assertNull(item.getId());
            Stock result = repository.save(item);

            //test
            assertNotNull(result);
            assertTrue(result.getId() != 0);
            assertNotNull(item.getCreatedAt());
        }

        @Test
        void createUniqueName() {
            String name = "notUnique_"+DomainBuilder.randomString();
            Stock item1 = DomainBuilder.getStock(name);
            Stock item2 = DomainBuilder.getStock(name);

            try {
                repository.save(item1);
                repository.save(item2);
                fail();
            }
            catch (DataIntegrityViolationException e) {
                assertTrue(true);
            }
            System.out.println();
        }

        @Test
        void update() {
            Stock item = DomainBuilder.getStock();
            assertNull(item.getId());
            assertNull(item.getModifiedAt());
            Stock record = repository.save(item);

            //now update
            String changedSymbol = DomainBuilder.getSymbolRandom();
            record.setSymbol(changedSymbol);
            record.setModifiedAt(LocalDateTime.now());
            Stock resultUpdate = repository.save(record);

            //test
            assertNotNull(resultUpdate);
            assertEquals(resultUpdate.getSymbol(), changedSymbol);
            assertNotNull(resultUpdate.getModifiedAt());
        }

        @Test
        void delete() {
            Stock item = DomainBuilder.getStock();
            assertNull(item.getId());
            assertNull(item.getDeletedAt());
            Stock record = repository.save(item);

            //now update
            record.setDeletedAt(LocalDateTime.now());
            Stock resultUpdate = repository.save(record);

            //test
            assertNotNull(resultUpdate);
            assertNotNull(resultUpdate.getDeletedAt());
        }
    }

    @Nested
    class SuiteFind {

        @Test
        void findById() {
            String name = "name"+DomainBuilder.randomString();
            Stock record = DomainBuilder.getStock(name);
            Stock item = repository.save(record);
            Stock result = repository.findById(item.getId()).get();

            //test
            assertNotNull(result);
            assertEquals(result.getName(), name);
        }

        @Test
        void findByExtid() {
            String extid = UUID.randomUUID().toString();
            Stock record = DomainBuilder.getStock();
            record.setExtid(extid);

            repository.save(record);
            Stock result = repository.findByExtid(extid);

            //test
            assertNotNull(result);
            assertEquals(result.getExtid(), extid);
        }

        @Test
        void findByName() {
            String name = "name"+DomainBuilder.randomString();
            Stock record = DomainBuilder.getStock(name);

            repository.save(record);
            Stock result = repository.findByName(name);

            //test
            assertNotNull(result);
            assertEquals(result.getName(), name);
        }

        @Test
        void findActive_toList() {
            String symbol1 = DomainBuilder.getSymbolRandom();
            String symbol2 = DomainBuilder.getSymbolRandom();
            Stock record1 = DomainBuilder.getStock(symbol1);
            Stock record2 = DomainBuilder.getStock(symbol2);
            repository.save(record1);
            repository.save(record2);

            List<Stock> results = repository.findByActive(ActiveEnum.ACTIVE.value);

            //test
            assertNotNull(results);
            assertTrue(results.size() > 1);
        }

        @Test
        void findActive_checkNoInactive() {
            String symbol1 = DomainBuilder.getSymbolRandom();
            String symbol2 = DomainBuilder.getSymbolRandom();
            Stock record1 = DomainBuilder.getStock(symbol1);
            Stock record2 = DomainBuilder.getStock(symbol2);
            record2.setActive(ActiveEnum.INACTIVE.value);
            repository.save(record1);
            repository.save(record2);

            List<Stock> results = repository.findByActive(ActiveEnum.ACTIVE.value);

            //test
            assertNotNull(results);
            assertTrue(results.size() > 0);
            assertFalse(results.contains(record2));
        }
    }
}
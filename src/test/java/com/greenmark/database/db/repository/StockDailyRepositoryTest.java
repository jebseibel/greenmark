package com.greenmark.database.db.repository;

import com.greenmark.common.enums.ActiveEnum;
import com.greenmark.database.db.DomainBuilder;
import com.greenmark.database.db.entity.StockDaily;
import com.greenmark.database.db.entity.StockDaily;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StockDailyRepositoryTest {

    @Autowired
    private StockDailyRepository repository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Nested
    class SuiteCrud {

        @Test
        void create() {
            StockDaily item = DomainBuilder.getStockDaily();
            assertNull(item.getId());
            StockDaily result = repository.save(item);

            //test
            assertNotNull(result);
            assertTrue(result.getId() != 0);
            assertNotNull(item.getCreatedAt());
        }

        @Test
        void createUniqueName() {
            String symbol = DomainBuilder.getSymbolRandom();
            StockDaily item1 = DomainBuilder.getStockDaily(symbol);
            StockDaily item2 = DomainBuilder.getStockDaily(symbol);

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
            StockDaily item = DomainBuilder.getStockDaily();
            assertNull(item.getId());
            assertNull(item.getModifiedAt());
            StockDaily record = repository.save(item);

            //now update
            String changedSymbol = DomainBuilder.getSymbolRandom();
            record.setSymbol(changedSymbol);
            record.setModifiedAt(LocalDateTime.now());
            StockDaily resultUpdate = repository.save(record);

            //test
            assertNotNull(resultUpdate);
            assertEquals(resultUpdate.getSymbol(), changedSymbol);
            assertNotNull(resultUpdate.getModifiedAt());
        }

        @Test
        void delete() {
            StockDaily item = DomainBuilder.getStockDaily();
            assertNull(item.getId());
            assertNull(item.getDeletedAt());
            StockDaily record = repository.save(item);

            //now update
            record.setDeletedAt(LocalDateTime.now());
            StockDaily resultUpdate = repository.save(record);

            //test
            assertNotNull(resultUpdate);
            assertNotNull(resultUpdate.getDeletedAt());
        }
    }

    @Nested
    class SuiteFind {

        @Test
        void findById() {
            String symbol = DomainBuilder.getSymbolRandom();
            StockDaily record = DomainBuilder.getStockDaily(symbol);
            StockDaily item = repository.save(record);
            StockDaily result = repository.findById(item.getId()).get();

            //test
            assertNotNull(result);
            assertEquals(result.getSymbol(), symbol);
        }


        @Test
        void findBySymbol() {
            String symbol = DomainBuilder.getSymbolRandom();
            StockDaily record = DomainBuilder.getStockDaily(symbol);

            repository.save(record);
            StockDaily result = repository.findBySymbol(symbol).get();

            //test
            assertNotNull(result);
            assertEquals(result.getSymbol(), symbol);
        }

        @Test
        void findActive_toList() {
            String symbol1 = DomainBuilder.getSymbolRandom();
            String symbol2 = DomainBuilder.getSymbolRandom();
            StockDaily record1 = DomainBuilder.getStockDaily(symbol1);
            StockDaily record2 = DomainBuilder.getStockDaily(symbol2);
            repository.save(record1);
            repository.save(record2);

            List<StockDaily> results = repository.findByActive(ActiveEnum.ACTIVE.value);

            //test
            assertNotNull(results);
            assertTrue(results.size() > 1);
        }

        @Test
        void findActive_checkNoInactive() {
            String symbol1 = DomainBuilder.getSymbolRandom();
            String symbol2 = DomainBuilder.getSymbolRandom();
            StockDaily record1 = DomainBuilder.getStockDaily(symbol1);
            StockDaily record2 = DomainBuilder.getStockDaily(symbol2);
            record2.setActive(ActiveEnum.INACTIVE.value);
            repository.save(record1);
            repository.save(record2);

            List<StockDaily> results = repository.findByActive(ActiveEnum.ACTIVE.value);

            //test
            assertNotNull(results);
            assertTrue(results.size() > 0);
            assertFalse(results.contains(record2));
        }        
    }
}
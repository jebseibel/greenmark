package com.greenmark.database.db.repository;

import com.greenmark.common.enums.ActiveEnum;
import com.greenmark.database.DomainBuilderDatabase;
import com.greenmark.database.db.entity.StockDailyDb;
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
class StockDbDailyRepositoryTest {

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
            StockDailyDb item = DomainBuilderDatabase.getStockDaily();
            assertNull(item.getId());
            StockDailyDb result = repository.save(item);

            //test
            assertNotNull(result);
            assertTrue(result.getId() != 0);
            assertNotNull(item.getCreatedAt());
        }

        @Test
        void createUniqueName() {
            String symbol = DomainBuilderDatabase.getSymbolRandom();
            StockDailyDb item1 = DomainBuilderDatabase.getStockDaily(symbol);
            StockDailyDb item2 = DomainBuilderDatabase.getStockDaily(symbol);

            repository.save(item1);
            assertThrows(DataIntegrityViolationException.class, () -> repository.save(item2));
        }

        @Test
        void update() {
            StockDailyDb item = DomainBuilderDatabase.getStockDaily();
            assertNull(item.getId());
            assertNull(item.getModifiedAt());
            StockDailyDb record = repository.save(item);

            //now update
            String changedSymbol = DomainBuilderDatabase.getSymbolRandom();
            record.setSymbol(changedSymbol);
            record.setModifiedAt(LocalDateTime.now());
            StockDailyDb resultUpdate = repository.save(record);

            //test
            assertNotNull(resultUpdate);
            assertEquals(resultUpdate.getSymbol(), changedSymbol);
            assertNotNull(resultUpdate.getModifiedAt());
        }

        @Test
        void delete() {
            StockDailyDb item = DomainBuilderDatabase.getStockDaily();
            assertNull(item.getId());
            assertNull(item.getDeletedAt());
            StockDailyDb record = repository.save(item);

            //now update
            record.setDeletedAt(LocalDateTime.now());
            StockDailyDb resultUpdate = repository.save(record);

            //test
            assertNotNull(resultUpdate);
            assertNotNull(resultUpdate.getDeletedAt());
        }
    }

    @Nested
    class SuiteFind {

        @Test
        void findById() {
            String symbol = DomainBuilderDatabase.getSymbolRandom();
            StockDailyDb record = DomainBuilderDatabase.getStockDaily(symbol);
            StockDailyDb item = repository.save(record);
            StockDailyDb result = repository.findById(item.getId()).get();

            //test
            assertNotNull(result);
            assertEquals(result.getSymbol(), symbol);
        }


        @Test
        void findBySymbol() {
            String symbol = DomainBuilderDatabase.getSymbolRandom();
            StockDailyDb record = DomainBuilderDatabase.getStockDaily(symbol);

            repository.save(record);
            StockDailyDb result = repository.findBySymbol(symbol).get();

            //test
            assertNotNull(result);
            assertEquals(result.getSymbol(), symbol);
        }

        @Test
        void findActive_toList() {
            String symbol1 = DomainBuilderDatabase.getSymbolRandom();
            String symbol2 = DomainBuilderDatabase.getSymbolRandom();
            StockDailyDb record1 = DomainBuilderDatabase.getStockDaily(symbol1);
            StockDailyDb record2 = DomainBuilderDatabase.getStockDaily(symbol2);
            repository.save(record1);
            repository.save(record2);

            List<StockDailyDb> results = repository.findByActive(ActiveEnum.ACTIVE.value);

            //test
            assertNotNull(results);
            assertTrue(results.size() > 1);
        }

        @Test
        void findActive_checkNoInactive() {
            String symbol1 = DomainBuilderDatabase.getSymbolRandom();
            String symbol2 = DomainBuilderDatabase.getSymbolRandom();
            StockDailyDb record1 = DomainBuilderDatabase.getStockDaily(symbol1);
            StockDailyDb record2 = DomainBuilderDatabase.getStockDaily(symbol2);
            record2.setActive(ActiveEnum.INACTIVE.value);
            repository.save(record1);
            repository.save(record2);

            List<StockDailyDb> results = repository.findByActive(ActiveEnum.ACTIVE.value);

            //test
            assertNotNull(results);
            assertTrue(results.size() > 0);
            assertFalse(results.contains(record2));
        }
    }
}
package com.greenmark.database.db.repository;

import com.greenmark.database.DomainBuilderDatabase;
import com.greenmark.database.db.entity.StockWatch;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StockWatchRepositoryTest {

    @Autowired
    private StockWatchRepository repository;

    @Nested
    class SuiteCrud {

        @Test
        void create() {
            StockWatch item = DomainBuilderDatabase.getStockWatchDaily();
            assertNull(item.getId());
            StockWatch result = repository.save(item);

            //test
            assertNotNull(result);
            assertTrue(result.getId() != 0);
            assertNotNull(item.getCreatedAt());
        }

        @Test
        void createSymbolNotUnique() {
            String symbol = DomainBuilderDatabase.getSymbolRandom();
            StockWatch item1 = DomainBuilderDatabase.getStockWatchDaily();
            StockWatch item2 = DomainBuilderDatabase.getStockWatchDaily();

            item1.setSymbol(symbol);
            item2.setSymbol(symbol);
            repository.save(item1);
            assertThrows(DataIntegrityViolationException.class, () -> repository.save(item2));
        }

        @Test
        void update() {
            StockWatch item = DomainBuilderDatabase.getStockWatchDaily();
            assertNull(item.getId());
            assertNull(item.getModifiedAt());
            StockWatch record = repository.save(item);

            //now update
            record.setModifiedAt(LocalDateTime.now());
            StockWatch resultUpdate = repository.save(record);

            //test
            assertNotNull(resultUpdate);
            assertNotNull(resultUpdate.getModifiedAt());
        }

        @Test
        void delete() {
            StockWatch item = DomainBuilderDatabase.getStockWatchDaily();
            assertNull(item.getId());
            assertNull(item.getDeletedAt());
            StockWatch record = repository.save(item);

            //now update
            record.setDeletedAt(LocalDateTime.now());
            StockWatch resultUpdate = repository.save(record);

            //test
            assertNotNull(resultUpdate);
            assertNotNull(resultUpdate.getDeletedAt());
        }
    }

    @Nested
    class SuiteFind {

        @Test
        void findBySymbol() {
            String symbol = DomainBuilderDatabase.getSymbolRandom();
            StockWatch record = DomainBuilderDatabase.getStockWatchDaily();
            System.out.println(symbol);
            repository.save(record);

            Optional<StockWatch> result = repository.findBySymbol(symbol);

            //test
            assertNotNull(result);
            //assertEquals(result.getSymbol(), symbol);
        }
    }
}
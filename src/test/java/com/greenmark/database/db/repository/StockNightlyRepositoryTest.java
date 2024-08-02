package com.greenmark.database.db.repository;

import com.greenmark.database.db.DomainBuilder;
import com.greenmark.database.db.entity.StockNightly;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StockNightlyRepositoryTest {

    @Autowired
    private StockNightlyRepository repository;

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
            StockNightly item = DomainBuilder.getStockNightly();
            assertNull(item.getId());
            StockNightly result = repository.save(item);

            //test
            assertNotNull(result);
            assertTrue(result.getId() != 0);
            assertNotNull(item.getCreatedAt());
        }

        @Test
        void createUniqueName() {
            String symbol = DomainBuilder.getSymbolRandom();
            StockNightly item1 = DomainBuilder.getStockNightly(symbol);
            StockNightly item2 = DomainBuilder.getStockNightly(symbol);

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
            StockNightly item = DomainBuilder.getStockNightly();
            assertNull(item.getId());
            assertNull(item.getModifiedAt());
            StockNightly record = repository.save(item);

            //now update
            String changedSymbol = DomainBuilder.getSymbolRandom();
            record.setSymbol(changedSymbol);
            record.setModifiedAt(LocalDateTime.now());
            StockNightly resultUpdate = repository.save(record);

            //test
            assertNotNull(resultUpdate);
            assertEquals(resultUpdate.getSymbol(), changedSymbol);
            assertNotNull(resultUpdate.getModifiedAt());
        }

        @Test
        void delete() {
            StockNightly item = DomainBuilder.getStockNightly();
            assertNull(item.getId());
            assertNull(item.getDeletedAt());
            StockNightly record = repository.save(item);

            //now update
            record.setDeletedAt(LocalDateTime.now());
            StockNightly resultUpdate = repository.save(record);

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
            StockNightly record = DomainBuilder.getStockNightly(symbol);
            StockNightly item = repository.save(record);
            StockNightly result = repository.findById(item.getId()).get();

            //test
            assertNotNull(result);
            assertEquals(result.getSymbol(), symbol);
        }


        @Test
        void findBySymbol() {
            String symbol = DomainBuilder.getSymbolRandom();
            StockNightly record = DomainBuilder.getStockNightly(symbol);

            repository.save(record);
            StockNightly result = repository.findBySymbol(symbol);

            //test
            assertNotNull(result);
            assertEquals(result.getSymbol(), symbol);
        }
    }
}
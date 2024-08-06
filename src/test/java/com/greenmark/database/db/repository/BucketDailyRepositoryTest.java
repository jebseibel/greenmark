package com.greenmark.database.db.repository;

import com.greenmark.common.database.domain.BucketDailyDb;
import com.greenmark.common.database.domain.StockData;
import com.greenmark.common.enums.ActiveEnum;
import com.greenmark.database.db.DomainBuilder;
import com.greenmark.database.db.entity.BucketDaily;
import com.greenmark.database.exceptions.DatabaseAccessException;
import com.greenmark.database.exceptions.DatabaseCreateFailureException;
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
class BucketDailyRepositoryTest {

    @Autowired
    private BucketDailyRepository repository;

    @Nested
    class SuiteCrud {

        @Test
        void create() {
            BucketDaily item = DomainBuilder.getBucketDaily();
            assertNull(item.getId());
            BucketDaily result = repository.save(item);

            //test
            assertNotNull(result);
            assertTrue(result.getId() != 0);
            assertNotNull(item.getCreatedAt());
        }

        @Test
        void createSymbolNotUnique() {
            String symbol = DomainBuilder.getSymbolRandom();
            BucketDaily item1 = DomainBuilder.getBucketDaily(symbol);
            BucketDaily item2 = DomainBuilder.getBucketDaily(symbol);

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
            BucketDaily item = DomainBuilder.getBucketDaily();
            assertNull(item.getId());
            assertNull(item.getModifiedAt());
            BucketDaily record = repository.save(item);

            //now update
            record.setModifiedAt(LocalDateTime.now());
            BucketDaily resultUpdate = repository.save(record);

            //test
            assertNotNull(resultUpdate);
            assertNotNull(resultUpdate.getModifiedAt());
        }

        @Test
        void delete() {
            BucketDaily item = DomainBuilder.getBucketDaily();
            assertNull(item.getId());
            assertNull(item.getDeletedAt());
            BucketDaily record = repository.save(item);

            //now update
            record.setDeletedAt(LocalDateTime.now());
            BucketDaily resultUpdate = repository.save(record);

            //test
            assertNotNull(resultUpdate);
            assertNotNull(resultUpdate.getDeletedAt());
        }
    }

    @Nested
    class SuiteFind {

        @Test
        void findBySymbol() {
            String symbol = DomainBuilder.getSymbolRandom();
            BucketDaily record = DomainBuilder.getBucketDaily(symbol);

            repository.save(record);
            BucketDaily result = repository.findBySymbol(symbol).get();

            //test
            assertNotNull(result);
            assertEquals(result.getSymbol(), symbol);
        }

        @Test
        void findActive_toList() {
            String symbol1 = DomainBuilder.getSymbolRandom();
            String symbol2 = DomainBuilder.getSymbolRandom();
            BucketDaily record1 = DomainBuilder.getBucketDaily(symbol1);
            BucketDaily record2 = DomainBuilder.getBucketDaily(symbol2);
            repository.save(record1);
            repository.save(record2);

            List<BucketDaily> results = repository.findByActive(ActiveEnum.ACTIVE.value);

            //test
            assertNotNull(results);
            assertTrue(results.size() > 1);
        }

        @Test
        void findActive_checkNoInactive() {
            String symbol1 = DomainBuilder.getSymbolRandom();
            String symbol2 = DomainBuilder.getSymbolRandom();
            BucketDaily record1 = DomainBuilder.getBucketDaily(symbol1);
            BucketDaily record2 = DomainBuilder.getBucketDaily(symbol2);
            record2.setActive(ActiveEnum.INACTIVE.value);
            repository.save(record1);
            repository.save(record2);

            List<BucketDaily> results = repository.findByActive(ActiveEnum.ACTIVE.value);

            //test
            assertNotNull(results);
            assertTrue(results.size() > 0);
            assertFalse(results.contains(record2));
        }
    }
}
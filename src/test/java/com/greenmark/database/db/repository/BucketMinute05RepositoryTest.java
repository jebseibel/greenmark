package com.greenmark.database.db.repository;

import com.greenmark.common.enums.ActiveEnum;
import com.greenmark.database.db.DomainBuilder;
import com.greenmark.database.db.entity.BucketMinute05;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BucketMinute05RepositoryTest {

    @Autowired
    private BucketMinute05Repository repository;

    @Nested
    class SuiteCrud {

        @Test
        void create() {
            BucketMinute05 item = DomainBuilder.getBucketMinute05();
            System.out.println(item);
            assertNull(item.getId());
            BucketMinute05 result = repository.save(item);

            //test
            assertNotNull(result);
            assertTrue(result.getId() != 0);
            assertNotNull(item.getCreatedAt());
        }

        @Test
        void createSymbolNotUnique() {
            String name = DomainBuilder.getSymbolRandom();
            BucketMinute05 item1 = DomainBuilder.getBucketMinute05(name);
            BucketMinute05 item2 = DomainBuilder.getBucketMinute05(name);

            repository.save(item1);
            assertThrows(DataIntegrityViolationException.class, () -> repository.save(item2));
        }

        @Test
        void update() {
            BucketMinute05 item = DomainBuilder.getBucketMinute05();
            assertNull(item.getId());
            assertNull(item.getModifiedAt());
            BucketMinute05 record = repository.save(item);

            //now update
            record.setModifiedAt(LocalDateTime.now());
            BucketMinute05 resultUpdate = repository.save(record);

            //test
            assertNotNull(resultUpdate);
            assertNotNull(resultUpdate.getModifiedAt());
        }

        @Test
        void delete() {
            BucketMinute05 item = DomainBuilder.getBucketMinute05();
            assertNull(item.getId());
            assertNull(item.getDeletedAt());
            BucketMinute05 record = repository.save(item);

            //now update
            record.setDeletedAt(LocalDateTime.now());
            BucketMinute05 resultUpdate = repository.save(record);

            //test
            assertNotNull(resultUpdate);
            assertNotNull(resultUpdate.getDeletedAt());
        }
    }

    @Nested
    class SuiteFind {

        @Test
        void findBySymbol() {
            String name = DomainBuilder.getSymbolRandom();
            BucketMinute05 record = DomainBuilder.getBucketMinute05(name);

            repository.save(record);
            BucketMinute05 result = repository.findBySymbol(name).get();

            //test
            assertNotNull(result);
            assertEquals(result.getSymbol(), name);
        }

        @Test
        void findActive_toList() {
            String symbol1 = DomainBuilder.getSymbolRandom();
            String symbol2 = DomainBuilder.getSymbolRandom();
            BucketMinute05 record1 = DomainBuilder.getBucketMinute05(symbol1);
            BucketMinute05 record2 = DomainBuilder.getBucketMinute05(symbol2);
            repository.save(record1);
            repository.save(record2);

            List<BucketMinute05> results = repository.findByActive(ActiveEnum.ACTIVE.value);

            //test
            assertNotNull(results);
            assertTrue(results.size() > 1);
        }

        @Test
        void findActive_checkNoInactive() {
            String symbol1 = DomainBuilder.getSymbolRandom();
            String symbol2 = DomainBuilder.getSymbolRandom();
            BucketMinute05 record1 = DomainBuilder.getBucketMinute05(symbol1);
            BucketMinute05 record2 = DomainBuilder.getBucketMinute05(symbol2);
            record2.setActive(ActiveEnum.INACTIVE.value);
            repository.save(record1);
            repository.save(record2);

            List<BucketMinute05> results = repository.findByActive(ActiveEnum.ACTIVE.value);

            //test
            assertNotNull(results);
            assertTrue(results.size() > 0);
            assertFalse(results.contains(record2));
        }
    }
}
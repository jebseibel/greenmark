package com.greenmark.database.db.repository;

import com.greenmark.common.enums.ActiveEnum;
import com.greenmark.database.DomainBuilder;
import com.greenmark.database.db.entity.BucketMinute15;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BucketMinute15RepositoryTest {

    @Autowired
    private BucketMinute15Repository repository;

    @Nested
    class SuiteCrud {

        @Test
        void create() {
            BucketMinute15 item = DomainBuilder.getBucketMinute15();
            System.out.println(item);
            assertNull(item.getId());
            BucketMinute15 result = repository.save(item);

            //test
            assertNotNull(result);
            assertTrue(result.getId() != 0);
            assertNotNull(item.getCreatedAt());
        }

        @Test
        void createSymbolNotUnique() {
            String name = DomainBuilder.getSymbolRandom();
            BucketMinute15 item1 = DomainBuilder.getBucketMinute15(name);
            BucketMinute15 item2 = DomainBuilder.getBucketMinute15(name);

            repository.save(item1);
            assertThrows(DataIntegrityViolationException.class, () -> repository.save(item2));
        }

        @Test
        void update() {
            BucketMinute15 item = DomainBuilder.getBucketMinute15();
            assertNull(item.getId());
            assertNull(item.getModifiedAt());
            BucketMinute15 record = repository.save(item);

            //now update
            record.setModifiedAt(LocalDateTime.now());
            BucketMinute15 resultUpdate = repository.save(record);

            //test
            assertNotNull(resultUpdate);
            assertNotNull(resultUpdate.getModifiedAt());
        }

        @Test
        void delete() {
            BucketMinute15 item = DomainBuilder.getBucketMinute15();
            assertNull(item.getId());
            assertNull(item.getDeletedAt());
            BucketMinute15 record = repository.save(item);

            //now update
            record.setDeletedAt(LocalDateTime.now());
            BucketMinute15 resultUpdate = repository.save(record);

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
            BucketMinute15 record = DomainBuilder.getBucketMinute15(name);

            repository.save(record);
            BucketMinute15 result = repository.findBySymbol(name).get();

            //test
            assertNotNull(result);
            assertEquals(result.getSymbol(), name);
        }

        @Test
        void findActive_toList() {
            String symbol1 = DomainBuilder.getSymbolRandom();
            String symbol2 = DomainBuilder.getSymbolRandom();
            BucketMinute15 record1 = DomainBuilder.getBucketMinute15(symbol1);
            BucketMinute15 record2 = DomainBuilder.getBucketMinute15(symbol2);
            repository.save(record1);
            repository.save(record2);

            List<BucketMinute15> results = repository.findByActive(ActiveEnum.ACTIVE.value);

            //test
            assertNotNull(results);
            assertTrue(results.size() > 1);
        }

        @Test
        void findActive_checkNoInactive() {
            String symbol1 = DomainBuilder.getSymbolRandom();
            String symbol2 = DomainBuilder.getSymbolRandom();
            BucketMinute15 record1 = DomainBuilder.getBucketMinute15(symbol1);
            BucketMinute15 record2 = DomainBuilder.getBucketMinute15(symbol2);
            record2.setActive(ActiveEnum.INACTIVE.value);
            repository.save(record1);
            repository.save(record2);

            List<BucketMinute15> results = repository.findByActive(ActiveEnum.ACTIVE.value);

            //test
            assertNotNull(results);
            assertTrue(results.size() > 0);
            assertFalse(results.contains(record2));
        }
    }
}
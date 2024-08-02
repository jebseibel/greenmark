package com.greenmark.database.db.repository;

import com.greenmark.database.db.DomainBuilder;
import com.greenmark.database.db.entity.BucketMinute60;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BucketMinute60RepositoryTest {

    @Autowired
    private BucketMinute60Repository repository;

    @Nested
    class SuiteCrud {

        @Test
        void create() {
            BucketMinute60 item = DomainBuilder.getBucketMinute60();
            System.out.println(item);
            assertNull(item.getId());
            BucketMinute60 result = repository.save(item);

            //test
            assertNotNull(result);
            assertTrue(result.getId() != 0);
            assertNotNull(item.getCreatedAt());
        }

        @Test
        void createSymbolNotUnique() {
            String name = DomainBuilder.getSymbolRandom();
            BucketMinute60 item1 = DomainBuilder.getBucketMinute60(name);
            BucketMinute60 item2 = DomainBuilder.getBucketMinute60(name);

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
            BucketMinute60 item = DomainBuilder.getBucketMinute60();
            assertNull(item.getId());
            assertNull(item.getModifiedAt());
            BucketMinute60 record = repository.save(item);

            //now update
            record.setModifiedAt(LocalDateTime.now());
            BucketMinute60 resultUpdate = repository.save(record);

            //test
            assertNotNull(resultUpdate);
            assertNotNull(resultUpdate.getModifiedAt());
        }

        @Test
        void delete() {
            BucketMinute60 item = DomainBuilder.getBucketMinute60();
            assertNull(item.getId());
            assertNull(item.getDeletedAt());
            BucketMinute60 record = repository.save(item);

            //now update
            record.setDeletedAt(LocalDateTime.now());
            BucketMinute60 resultUpdate = repository.save(record);

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
            BucketMinute60 record = DomainBuilder.getBucketMinute60(name);

            repository.save(record);
            BucketMinute60 result = repository.findBySymbol(name);

            //test
            assertNotNull(result);
            assertEquals(result.getSymbol(), name);
        }
    }
}
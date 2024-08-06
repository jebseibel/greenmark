package com.greenmark.database.db.repository;

import com.greenmark.database.db.DomainBuilder;
import com.greenmark.database.db.entity.BucketMinute15;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDateTime;

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
    }
}
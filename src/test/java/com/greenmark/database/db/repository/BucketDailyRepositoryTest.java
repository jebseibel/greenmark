package com.greenmark.database.db.repository;

import com.greenmark.database.db.DomainBuilder;
import com.greenmark.database.db.entity.BucketDaily;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDateTime;

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
            String name = DomainBuilder.getSymbolRandom();
            BucketDaily item1 = DomainBuilder.getBucketDaily(name);
            BucketDaily item2 = DomainBuilder.getBucketDaily(name);

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
            String name = DomainBuilder.getSymbolRandom();
            BucketDaily record = DomainBuilder.getBucketDaily(name);

            repository.save(record);
            BucketDaily result = repository.findBySymbol(name).get();

            //test
            assertNotNull(result);
            assertEquals(result.getSymbol(), name);
        }
    }
}
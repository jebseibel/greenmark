package com.greenmark.database.db.repository;

import com.greenmark.common.enums.ActiveEnum;
import com.greenmark.database.db.DomainBuilder;
import com.greenmark.database.db.entity.BucketMinute01;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BucketMinute01RepositoryTest {

    @Autowired
    private BucketMinute01Repository repository;

    @Nested
    class SuiteCrud {

        @Test
        void create() {
            BucketMinute01 item = DomainBuilder.getBucketMinute01();
            System.out.println(item);
            assertNull(item.getId());
            BucketMinute01 result = repository.save(item);

            //test
            assertNotNull(result);
            assertTrue(result.getId() != 0);
            assertNotNull(item.getCreatedAt());
        }

        @Test
        void createSymbolNotUnique() {
            String name = DomainBuilder.getSymbolRandom();
            BucketMinute01 item1 = DomainBuilder.getBucketMinute01(name);
            BucketMinute01 item2 = DomainBuilder.getBucketMinute01(name);

            repository.save(item1);
            assertThrows(DataIntegrityViolationException.class, () -> repository.save(item2));
        }

        @Test
        void update() {
            BucketMinute01 item = DomainBuilder.getBucketMinute01();
            assertNull(item.getId());
            assertNull(item.getModifiedAt());
            BucketMinute01 record = repository.save(item);

            //now update
            record.setModifiedAt(LocalDateTime.now());
            BucketMinute01 resultUpdate = repository.save(record);

            //test
            assertNotNull(resultUpdate);
            assertNotNull(resultUpdate.getModifiedAt());
        }

        @Test
        void delete() {
            BucketMinute01 item = DomainBuilder.getBucketMinute01();
            assertNull(item.getId());
            assertNull(item.getDeletedAt());
            BucketMinute01 record = repository.save(item);

            //now update
            record.setDeletedAt(LocalDateTime.now());
            BucketMinute01 resultUpdate = repository.save(record);

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
            BucketMinute01 record = DomainBuilder.getBucketMinute01(name);

            repository.save(record);
            BucketMinute01 result = repository.findBySymbol(name).get();

            //test
            assertNotNull(result);
            assertEquals(result.getSymbol(), name);
        }
    }
}
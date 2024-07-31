package com.greenmark.database.db.repository;

import com.greenmark.database.db.DomainBuilder;
import com.greenmark.database.db.entity.BucketEntity;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BucketRepositoryTest {

    @Autowired
    private BucketRepository repository;

    @Nested
    class SuiteCrud {

        @Test
        void create() {
            BucketEntity item = DomainBuilder.getBucket();
            assertNull(item.getId());
            BucketEntity result = repository.save(item);

            //test
            assertNotNull(result);
            assertTrue(result.getId() != 0);
            assertNotNull(item.getCreatedAt());
        }

        @Test
        void createUniqueName() {
            String name = "notUnique_"+DomainBuilder.randomPositiveString();
            BucketEntity item1 = DomainBuilder.getBucket(name);
            BucketEntity item2 = DomainBuilder.getBucket(name);

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
            BucketEntity item = DomainBuilder.getBucket();
            assertNull(item.getId());
            assertNull(item.getModifiedAt());
            BucketEntity record = repository.save(item);

            //now update
            String changedDescription = "description_update";
            record.setDescription(changedDescription);
            record.setModifiedAt(LocalDateTime.now());
            BucketEntity resultUpdate = repository.save(record);

            //test
            assertNotNull(resultUpdate);
            assertEquals(resultUpdate.getDescription(), changedDescription);
            assertNotNull(resultUpdate.getModifiedAt());
        }

        @Test
        void delete() {
            BucketEntity item = DomainBuilder.getBucket();
            assertNull(item.getId());
            assertNull(item.getDeletedAt());
            BucketEntity record = repository.save(item);

            //now update
            record.setDeletedAt(LocalDateTime.now());
            BucketEntity resultUpdate = repository.save(record);

            //test
            assertNotNull(resultUpdate);
            assertNotNull(resultUpdate.getDeletedAt());
        }
    }

    @Nested
    class SuiteFind {

        @Test
        void findById() {
            String name = "name"+DomainBuilder.randomPositiveString();
            BucketEntity record = DomainBuilder.getBucket(name);
            BucketEntity item = repository.save(record);
            BucketEntity result = repository.findById(item.getId()).get();

            //test
            assertNotNull(result);
            assertEquals(result.getName(), name);
        }

        @Test
        void findByExtid() {
            String extid = UUID.randomUUID().toString();
            BucketEntity record = DomainBuilder.getBucket();
            record.setExtid(extid);

            repository.save(record);
            BucketEntity result = repository.findByExtid(extid);

            //test
            assertNotNull(result);
            assertEquals(result.getExtid(), extid);
        }

        @Test
        void findByName() {
            String name = "name"+DomainBuilder.randomPositiveString();
            BucketEntity record = DomainBuilder.getBucket(name);

            repository.save(record);
            BucketEntity result = repository.findByName(name);

            //test
            assertNotNull(result);
            assertEquals(result.getName(), name);
        }
    }
}
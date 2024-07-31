package com.greenmark.database.db.repository;

import com.greenmark.database.db.DomainBuilder;
import com.greenmark.database.db.entity.FollowEntity;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FollowRepositoryTest {

    @Autowired
    private FollowRepository repository;

    @Nested
    class SuiteCrud {

        @Test
        void create() {
            FollowEntity item = DomainBuilder.getFollow();
            assertNull(item.getId());
            FollowEntity result = repository.save(item);

            //test
            assertNotNull(result);
            assertTrue(result.getId() != 0);
            assertNotNull(item.getCreatedAt());
        }

        @Test
        void createUniqueName() {
            String name = "notUnique_"+DomainBuilder.randomPositiveString();
            FollowEntity item1 = DomainBuilder.getFollow(name);
            FollowEntity item2 = DomainBuilder.getFollow(name);

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
            FollowEntity item = DomainBuilder.getFollow();
            assertNull(item.getId());
            assertNull(item.getModifiedAt());
            FollowEntity record = repository.save(item);

            //now update
            String changedDescription = "description_update";
            record.setDescription(changedDescription);
            record.setModifiedAt(LocalDateTime.now());
            FollowEntity resultUpdate = repository.save(record);

            //test
            assertNotNull(resultUpdate);
            assertEquals(resultUpdate.getDescription(), changedDescription);
            assertNotNull(resultUpdate.getModifiedAt());
        }

        @Test
        void delete() {
            FollowEntity item = DomainBuilder.getFollow();
            assertNull(item.getId());
            assertNull(item.getDeletedAt());
            FollowEntity record = repository.save(item);

            //now update
            record.setDeletedAt(LocalDateTime.now());
            FollowEntity resultUpdate = repository.save(record);

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
            FollowEntity record = DomainBuilder.getFollow(name);
            FollowEntity item = repository.save(record);
            FollowEntity result = repository.findById(item.getId()).get();

            //test
            assertNotNull(result);
            assertEquals(result.getName(), name);
        }

        @Test
        void findByExtid() {
            String extid = UUID.randomUUID().toString();
            FollowEntity record = DomainBuilder.getFollow();
            record.setExtid(extid);

            repository.save(record);
            FollowEntity result = repository.findByExtid(extid);

            //test
            assertNotNull(result);
            assertEquals(result.getExtid(), extid);
        }

        @Test
        void findByName() {
            String name = "name"+DomainBuilder.randomPositiveString();
            FollowEntity record = DomainBuilder.getFollow(name);

            repository.save(record);
            FollowEntity result = repository.findByName(name);

            //test
            assertNotNull(result);
            assertEquals(result.getName(), name);
        }
    }
}
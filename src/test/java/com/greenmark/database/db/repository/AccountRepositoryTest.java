package com.greenmark.database.db.repository;

import com.greenmark.database.db.entity.AccountEntity;
import com.greenmark.database.db.DomainBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountRepositoryTest {

    @Autowired
    private AccountRepository repository;

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
            AccountEntity item = DomainBuilder.getAccount();
            assertNull(item.getId());
            AccountEntity result = repository.save(item);

            //test
            assertNotNull(result);
            assertTrue(result.getId() != 0);
            assertNotNull(item.getCreatedAt());
        }

        @Test
        void createUniqueName() {
            String name = "notUnique_"+DomainBuilder.randomPositiveString();
            AccountEntity item1 = DomainBuilder.getAccount(name);
            AccountEntity item2 = DomainBuilder.getAccount(name);
            
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
            AccountEntity item = DomainBuilder.getAccount();
            assertNull(item.getId());
            assertNull(item.getModifiedAt());
            AccountEntity record = repository.save(item);

            //now update
            String changedDescription = "description_update";
            record.setDescription(changedDescription);
            record.setModifiedAt(LocalDateTime.now());
            AccountEntity resultUpdate = repository.save(record);

            //test
            assertNotNull(resultUpdate);
            assertEquals(resultUpdate.getDescription(), changedDescription);
            assertNotNull(resultUpdate.getModifiedAt());
        }

        @Test
        void delete() {
            AccountEntity item = DomainBuilder.getAccount();
            assertNull(item.getId());
            assertNull(item.getDeletedAt());
            AccountEntity record = repository.save(item);

            //now update
            record.setDeletedAt(LocalDateTime.now());
            AccountEntity resultUpdate = repository.save(record);

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
            AccountEntity record = DomainBuilder.getAccount(name);
            AccountEntity item = repository.save(record);
            AccountEntity result = repository.findById(item.getId()).get();

            //test
            assertNotNull(result);
            assertEquals(result.getName(), name);
        }

        @Test
        void findByExtid() {
            String extid = UUID.randomUUID().toString();
            AccountEntity record = DomainBuilder.getAccount();
            record.setExtid(extid);

            repository.save(record);
            AccountEntity result = repository.findByExtid(extid);

            //test
            assertNotNull(result);
            assertEquals(result.getExtid(), extid);
        }

        @Test
        void findByName() {
            String name = "name"+DomainBuilder.randomPositiveString();
            AccountEntity record = DomainBuilder.getAccount(name);

            repository.save(record);
            AccountEntity result = repository.findByName(name);

            //test
            assertNotNull(result);
            assertEquals(result.getName(), name);
        }
    }
}
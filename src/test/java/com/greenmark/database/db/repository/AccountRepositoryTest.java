package com.greenmark.database.db.repository;

import com.greenmark.database.db.entity.Account;
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
            Account item = DomainBuilder.getAccount();
            assertNull(item.getId());
            Account result = repository.save(item);

            //test
            assertNotNull(result);
            assertTrue(result.getId() != 0);
            assertNotNull(item.getCreatedAt());
        }

        @Test
        void createUniqueName() {
            String name = "notUnique_"+DomainBuilder.randomPositiveString();
            Account item1 = DomainBuilder.getAccount(name);
            Account item2 = DomainBuilder.getAccount(name);
            
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
            Account item = DomainBuilder.getAccount();
            assertNull(item.getId());
            assertNull(item.getModifiedAt());
            Account record = repository.save(item);

            //now update
            String changedDescription = "description_update";
            record.setDescription(changedDescription);
            record.setModifiedAt(LocalDateTime.now());
            Account resultUpdate = repository.save(record);

            //test
            assertNotNull(resultUpdate);
            assertEquals(resultUpdate.getDescription(), changedDescription);
            assertNotNull(resultUpdate.getModifiedAt());
        }

        @Test
        void delete() {
            Account item = DomainBuilder.getAccount();
            assertNull(item.getId());
            assertNull(item.getDeletedAt());
            Account record = repository.save(item);

            //now update
            record.setDeletedAt(LocalDateTime.now());
            Account resultUpdate = repository.save(record);

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
            Account record = DomainBuilder.getAccount(name);
            Account item = repository.save(record);
            Account result = repository.findById(item.getId()).get();

            //test
            assertNotNull(result);
            assertEquals(result.getName(), name);
        }

        @Test
        void findByExtid() {
            String extid = UUID.randomUUID().toString();
            Account record = DomainBuilder.getAccount();
            record.setExtid(extid);

            repository.save(record);
            Account result = repository.findByExtid(extid);

            //test
            assertNotNull(result);
            assertEquals(result.getExtid(), extid);
        }

        @Test
        void findByName() {
            String name = "name"+DomainBuilder.randomPositiveString();
            Account record = DomainBuilder.getAccount(name);

            repository.save(record);
            Account result = repository.findByName(name);

            //test
            assertNotNull(result);
            assertEquals(result.getName(), name);
        }
    }
}
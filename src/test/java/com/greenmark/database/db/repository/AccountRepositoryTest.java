package com.greenmark.database.db.repository;

import com.greenmark.database.DomainBuilderDatabase;
import com.greenmark.database.db.entity.Account;
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

    @Nested
    class SuiteCrud {

        @Test
        void create() {
            Account item = DomainBuilderDatabase.getAccount();
            assertNull(item.getId());
            Account result = repository.save(item);

            //test
            assertAll("Creation tests",
                    () -> assertNotNull(result, "result is not null"),
                    () -> assertTrue(result.getId() != 0, "err"),
                    () -> assertNotNull(item.getCreatedAt())
            );
        }

        @Test
        void createUniqueName() {
            String name = "notUnique_" + DomainBuilderDatabase.randomString();
            Account item1 = DomainBuilderDatabase.getAccount(name);
            Account item2 = DomainBuilderDatabase.getAccount(name);

            repository.save(item1);
            assertThrows(DataIntegrityViolationException.class, () -> repository.save(item2));
        }

        @Test
        void update() {
            Account item = DomainBuilderDatabase.getAccount();
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
            Account item = DomainBuilderDatabase.getAccount();
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
            String name = "name" + DomainBuilderDatabase.randomString();
            Account record = DomainBuilderDatabase.getAccount(name);
            Account item = repository.save(record);
            Account result = repository.findById(item.getId()).get();

            //test
            assertNotNull(result);
            assertEquals(result.getName(), name);
        }

        @Test
        void findByExtid() {
            String extid = UUID.randomUUID().toString();
            Account record = DomainBuilderDatabase.getAccount();
            record.setExtid(extid);

            repository.save(record);
            Account result = repository.findByExtid(extid).get();

            //test
            assertNotNull(result);
            assertEquals(result.getExtid(), extid);
        }

        @Test
        void findByName() {
            String name = "name" + DomainBuilderDatabase.randomString();
            Account record = DomainBuilderDatabase.getAccount(name);

            repository.save(record);
            Account result = repository.findByName(name).get();

            //test
            assertNotNull(result);
            assertEquals(result.getName(), name);
        }
    }
}
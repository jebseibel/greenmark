package com.greenmark.database.db.repository;

import com.greenmark.database.DomainBuilderDatabase;
import com.greenmark.database.db.entity.AccountDb;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountDbRepositoryTest {

    @Autowired
    private AccountRepository repository;

    @Nested
    class SuiteCrud {

        @Test
        void create() {
            AccountDb item = DomainBuilderDatabase.getAccount();
            assertNull(item.getId());
            AccountDb result = repository.save(item);

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
            AccountDb item1 = DomainBuilderDatabase.getAccount(name);
            AccountDb item2 = DomainBuilderDatabase.getAccount(name);

            repository.save(item1);
            assertThrows(DataIntegrityViolationException.class, () -> repository.save(item2));
        }

        @Test
        void update() {
            AccountDb item = DomainBuilderDatabase.getAccount();
            assertNull(item.getId());
            assertNull(item.getModifiedAt());
            AccountDb record = repository.save(item);

            //now update
            String changedDescription = "description_update";
            record.setDescription(changedDescription);
            record.setModifiedAt(LocalDateTime.now());
            AccountDb resultUpdate = repository.save(record);

            //test
            assertNotNull(resultUpdate);
            assertEquals(resultUpdate.getDescription(), changedDescription);
            assertNotNull(resultUpdate.getModifiedAt());
        }

        @Test
        void delete() {
            AccountDb item = DomainBuilderDatabase.getAccount();
            assertNull(item.getId());
            assertNull(item.getDeletedAt());
            AccountDb record = repository.save(item);

            //now update
            record.setDeletedAt(LocalDateTime.now());
            AccountDb resultUpdate = repository.save(record);

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
            AccountDb record = DomainBuilderDatabase.getAccount(name);
            AccountDb item = repository.save(record);
            AccountDb result = repository.findById(item.getId()).get();

            //test
            assertNotNull(result);
            assertEquals(result.getName(), name);
        }

        @Test
        void findByExtid() {
            String extid = UUID.randomUUID().toString();
            AccountDb record = DomainBuilderDatabase.getAccount();
            record.setExtid(extid);

            repository.save(record);
            AccountDb result = repository.findByExtid(extid).get();

            //test
            assertNotNull(result);
            assertEquals(result.getExtid(), extid);
        }

        @Test
        void findByName() {
            String name = "name" + DomainBuilderDatabase.randomString();
            AccountDb record = DomainBuilderDatabase.getAccount(name);

            repository.save(record);
            AccountDb result = repository.findByName(name).get();

            //test
            assertNotNull(result);
            assertEquals(result.getName(), name);
        }
    }
}
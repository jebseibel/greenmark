package com.greenmark.database.service;

import com.greenmark.database.db.DomainBuilder;
import com.greenmark.common.database.domain.AccountDb;
import com.greenmark.database.exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountDbServiceTest {
    @Autowired
    private AccountDbService service;

    @Nested
    class CreateTests {

        String random = DomainBuilder.randomString();
        String name = DomainBuilder.getNameRandom(random);
        String description = DomainBuilder.getNameRandom(random);

        @Test
        void created() throws Exception {
            // test
            String extid = DomainBuilder.getUUID();
            AccountDb result = service.create(extid, name, description);

            // validate
            assertNotNull(result);
            assertEquals(result.getName(), name);
            assertEquals(result.getDescription(), description);
        }

        @Test
        void createdTooLong() {
            // test
            String extid = DomainBuilder.getStringTestUUID();
            assertThrows(DatabaseCreateFailureException.class, () -> service.create(extid, name, description));
        }
    }

    @Nested
    class UpdateTests {

        AccountDb record;
        String extid;
        String random = DomainBuilder.randomString();
        String name = DomainBuilder.getNameRandom(random);
        String description = DomainBuilder.getNameRandom(random);

        @BeforeEach
        void beforeEach() throws DatabaseCreateFailureException, DatabaseAccessException {
            extid = DomainBuilder.getUUID();
            record = service.create(extid, name, description);
        }

        @Test
        void updated() throws DatabaseUpdateFailureException, DatabaseRetrievalFailureException {
            String newName = DomainBuilder.getNameRandom();
            String newDescription = DomainBuilder.getNameRandom();

            //execute
            AccountDb result = service.update(extid, newName, newDescription);

            // validate
            assertNotNull(result);
            assertEquals(result.getExtid(), extid);
            assertEquals(result.getName(), newName);
            assertEquals(result.getDescription(), newDescription);
        }

        @Test
        void updatedBadExtid() {
            // test
            try {
                String badExtid = UUID.randomUUID().toString();
                service.update(badExtid, name, description);
                fail();
            }
            catch (DatabaseRetrievalFailureException e) {
                assertTrue(true);
            }
            catch (Exception e) {
                fail();
            }
        }
    }

    @Nested
    class FindTests {

        AccountDb record;
        String extid;
        String random = DomainBuilder.randomString();
        String name = DomainBuilder.getNameRandom(random);
        String description = DomainBuilder.getNameRandom(random);

        @BeforeEach
        void beforeEach() throws DatabaseCreateFailureException, DatabaseAccessException {
            extid = DomainBuilder.getUUID();
            record = service.create(extid, name, description);
        }

        @Test
        void findByExtid() throws DatabaseRetrievalFailureException {
            // test
            AccountDb result = service.findByExtid(extid);

            // validate
            assertNotNull(result);
            assertEquals(result.getExtid(), extid);
        }

        @Test
        void findByExtid_NotFound() {
            String badExtid = UUID.randomUUID().toString();
            assertThrows(DatabaseRetrievalFailureException.class, () -> service.findByExtid(badExtid));
        }
    }

    @Nested
    class DeleteTests {

        AccountDb record;
        String extid;
        String random = DomainBuilder.randomString();
        String name = DomainBuilder.getNameRandom(random);
        String description = DomainBuilder.getNameRandom(random);

        @BeforeEach
        void beforeEach() throws DatabaseCreateFailureException, DatabaseAccessException {
            extid = DomainBuilder.getUUID();
            record = service.create(extid, name, description);
        }

        @Test
        void deleted() throws DatabaseRetrievalFailureException, DatabaseDeleteFailureException {
            //execute
            boolean result = service.delete(extid);

            // validate
            assertTrue(result);
        }

        @Test
        void deleteBadExtid() {
            String badExtid = UUID.randomUUID().toString();
            assertThrows(DatabaseRetrievalFailureException.class, () -> service.delete(badExtid));
        }
    }
}
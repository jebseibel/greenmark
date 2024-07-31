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
class AccountEntityDbServiceTest {
    @Autowired
    private AccountDbService service;

    @Nested
    class CreateTests {

        String random = DomainBuilder.randomPositiveString();
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
            try {
                String extid = DomainBuilder.getStringTestUUID();
                service.create(extid, name, description);
                fail();
            }
            catch (AccountCreateFailureException e) {
                assertTrue(true);
            }
            catch (Exception e) {
                fail();
            }
        }
    }

    @Nested
    class UpdateTests {

        AccountDb record;
        String extid;
        String random = DomainBuilder.randomPositiveString();
        String name = DomainBuilder.getNameRandom(random);
        String description = DomainBuilder.getNameRandom(random);

        @BeforeEach
        void beforeEach() throws AccountCreateFailureException, DatabaseAccessException {
            extid = DomainBuilder.getUUID();
            record = service.create(extid, name, description);
        }

        @Test
        void updated() throws AccountUpdateFailureException, AccountRetrievalFailureException {
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
            catch (AccountRetrievalFailureException e) {
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
        String random = DomainBuilder.randomPositiveString();
        String name = DomainBuilder.getNameRandom(random);
        String description = DomainBuilder.getNameRandom(random);

        @BeforeEach
        void beforeEach() throws AccountCreateFailureException, DatabaseAccessException {
            extid = DomainBuilder.getUUID();
            record = service.create(extid, name, description);
        }

        @Test
        void findByExtid() throws AccountRetrievalFailureException {
            // test
            AccountDb result = service.findByExtid(extid);

            // validate
            assertNotNull(result);
            assertEquals(result.getExtid(), extid);
        }

        @Test
        void findByExtid_NotFound() {
            // test
            try {
                String badExtid = UUID.randomUUID().toString();
                service.findByExtid(badExtid);
                fail();
            } catch (AccountRetrievalFailureException e) {
                assertTrue(true);
            }
            catch (Exception e) {
                fail();
            }
        }
    }

    @Nested
    class DeleteTests {

        AccountDb record;
        String extid;
        String random = DomainBuilder.randomPositiveString();
        String name = DomainBuilder.getNameRandom(random);
        String description = DomainBuilder.getNameRandom(random);

        @BeforeEach
        void beforeEach() throws AccountCreateFailureException, DatabaseAccessException {
            extid = DomainBuilder.getUUID();
            record = service.create(extid, name, description);
        }

        @Test
        void deleted() throws AccountRetrievalFailureException, AccountDeleteFailureException {
            //execute
            boolean result = service.delete(extid);

            // validate
            assertTrue(result);
        }

        @Test
        void updatedBadExtid() throws AccountDeleteFailureException {
            // test
            try {
                String badExtid = UUID.randomUUID().toString();
                service.delete(badExtid);
                fail();
            }
            catch (AccountRetrievalFailureException e) {
                assertTrue(true);
            }
        }
    }
}
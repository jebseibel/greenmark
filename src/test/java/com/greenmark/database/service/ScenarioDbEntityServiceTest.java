package com.greenmark.database.service;

import com.greenmark.common.database.domain.Scenario;
import com.greenmark.database.DomainBuilderDatabase;
import com.greenmark.database.exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ScenarioDbEntityServiceTest {

    @Autowired
    private ScenarioDbService service;

    @Nested
    class CreateTests {

        String random = DomainBuilderDatabase.randomString();
        String name = DomainBuilderDatabase.getNameRandom(random);
        String description = DomainBuilderDatabase.getNameRandom(random);

        @Test
        void created() throws Exception {
            // test
            String extid = DomainBuilderDatabase.getUUID();
            Scenario result = service.create(extid, name, description);

            // validate
            assertNotNull(result);
            assertEquals(result.getName(), name);
            assertEquals(result.getDescription(), description);
        }

        @Test
        void createdTooLong() {
            // test
            String extid = DomainBuilderDatabase.getStringTestUUIDTooLong();
            assertThrows(DatabaseCreateFailureException.class, () -> service.create(extid, name, description));
        }
    }

    @Nested
    class UpdateTests {

        Scenario record;
        String extid;
        String random = DomainBuilderDatabase.randomString();
        String name = DomainBuilderDatabase.getNameRandom(random);
        String description = DomainBuilderDatabase.getNameRandom(random);

        @BeforeEach
        void beforeEach() throws DatabaseCreateFailureException, DatabaseAccessException {
            extid = DomainBuilderDatabase.getUUID();
            record = service.create(extid, name, description);
        }

        @Test
        void updated() throws DatabaseUpdateFailureException, DatabaseRetrievalFailureException, DatabaseAccessException {
            String newName = DomainBuilderDatabase.getNameRandom();
            String newDescription = DomainBuilderDatabase.getNameRandom();

            //execute
            Scenario result = service.update(extid, newName, newDescription);

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
            } catch (DatabaseRetrievalFailureException e) {
                assertTrue(true);
            } catch (Exception e) {
                fail();
            }
        }
    }

    @Nested
    class FindTests {

        Scenario record;
        String extid;
        String random = DomainBuilderDatabase.randomString();
        String name = DomainBuilderDatabase.getNameRandom(random);
        String description = DomainBuilderDatabase.getNameRandom(random);

        @BeforeEach
        void beforeEach() throws DatabaseCreateFailureException, DatabaseAccessException {
            extid = DomainBuilderDatabase.getUUID();
            record = service.create(extid, name, description);
        }

        @Test
        void findByExtid() throws DatabaseRetrievalFailureException {
            // test
            Scenario result = service.findByExtid(extid);

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

        Scenario record;
        String extid;
        String random = DomainBuilderDatabase.randomString();
        String name = DomainBuilderDatabase.getNameRandom(random);
        String description = DomainBuilderDatabase.getNameRandom(random);

        @BeforeEach
        void beforeEach() throws DatabaseCreateFailureException, DatabaseAccessException {
            extid = DomainBuilderDatabase.getUUID();
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
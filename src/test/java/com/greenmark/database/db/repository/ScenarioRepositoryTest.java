package com.greenmark.database.db.repository;

import com.greenmark.database.DomainBuilderDatabase;
import com.greenmark.database.db.entity.Scenario;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ScenarioRepositoryTest {

    @Autowired
    private ScenarioRepository repository;

    @Nested
    class SuiteCrud {

        @Test
        void create() {
            Scenario item = DomainBuilderDatabase.getScenario();
            assertNull(item.getId());
            Scenario result = repository.save(item);

            //test
            assertNotNull(result);
            assertTrue(result.getId() != 0);
            assertNotNull(item.getCreatedAt());
        }

        @Test
        void createUniqueName() {
            String name = "notUnique_" + DomainBuilderDatabase.randomString();
            Scenario item1 = DomainBuilderDatabase.getScenario(name);
            Scenario item2 = DomainBuilderDatabase.getScenario(name);

            repository.save(item1);
            assertThrows(DataIntegrityViolationException.class, () -> repository.save(item2));
        }

        @Test
        void update() {
            Scenario item = DomainBuilderDatabase.getScenario();
            assertNull(item.getId());
            assertNull(item.getModifiedAt());
            Scenario record = repository.save(item);

            //now update
            String changedDescription = "description_update";
            record.setDescription(changedDescription);
            record.setModifiedAt(LocalDateTime.now());
            Scenario resultUpdate = repository.save(record);

            //test
            assertNotNull(resultUpdate);
            assertEquals(resultUpdate.getDescription(), changedDescription);
            assertNotNull(resultUpdate.getModifiedAt());
        }

        @Test
        void delete() {
            Scenario item = DomainBuilderDatabase.getScenario();
            assertNull(item.getId());
            assertNull(item.getDeletedAt());
            Scenario record = repository.save(item);

            //now update
            record.setDeletedAt(LocalDateTime.now());
            Scenario resultUpdate = repository.save(record);

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
            Scenario record = DomainBuilderDatabase.getScenario(name);
            Scenario item = repository.save(record);
            Scenario result = repository.findById(item.getId()).get();

            //test
            assertNotNull(result);
            assertEquals(result.getName(), name);
        }

        @Test
        void findByExtid() {
            String extid = UUID.randomUUID().toString();
            Scenario record = DomainBuilderDatabase.getScenario();
            record.setExtid(extid);

            repository.save(record);
            Scenario result = repository.findByExtid(extid).get();

            //test
            assertNotNull(result);
            assertEquals(result.getExtid(), extid);
        }

        @Test
        void findByName() {
            String name = "name" + DomainBuilderDatabase.randomString();
            Scenario record = DomainBuilderDatabase.getScenario(name);

            repository.save(record);
            Scenario result = repository.findByName(name).get();

            //test
            assertNotNull(result);
            assertEquals(result.getName(), name);
        }
    }
}
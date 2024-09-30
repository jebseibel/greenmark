package com.greenmark.database.db.repository;

import com.greenmark.database.DomainBuilderDatabase;
import com.greenmark.database.db.entity.ScenarioDb;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ScenarioDbRepositoryTest {

    @Autowired
    private ScenarioRepository repository;

    @Nested
    class SuiteCrud {

        @Test
        void create() {
            ScenarioDb item = DomainBuilderDatabase.getScenario();
            assertNull(item.getId());
            ScenarioDb result = repository.save(item);

            //test
            assertNotNull(result);
            assertTrue(result.getId() != 0);
            assertNotNull(item.getCreatedAt());
        }

        @Test
        void createUniqueName() {
            String name = "notUnique_" + DomainBuilderDatabase.randomString();
            ScenarioDb item1 = DomainBuilderDatabase.getScenario(name);
            ScenarioDb item2 = DomainBuilderDatabase.getScenario(name);

            repository.save(item1);
            assertThrows(DataIntegrityViolationException.class, () -> repository.save(item2));
        }

        @Test
        void update() {
            ScenarioDb item = DomainBuilderDatabase.getScenario();
            assertNull(item.getId());
            assertNull(item.getModifiedAt());
            ScenarioDb record = repository.save(item);

            //now update
            String changedDescription = "description_update";
            record.setDescription(changedDescription);
            record.setModifiedAt(LocalDateTime.now());
            ScenarioDb resultUpdate = repository.save(record);

            //test
            assertNotNull(resultUpdate);
            assertEquals(resultUpdate.getDescription(), changedDescription);
            assertNotNull(resultUpdate.getModifiedAt());
        }

        @Test
        void delete() {
            ScenarioDb item = DomainBuilderDatabase.getScenario();
            assertNull(item.getId());
            assertNull(item.getDeletedAt());
            ScenarioDb record = repository.save(item);

            //now update
            record.setDeletedAt(LocalDateTime.now());
            ScenarioDb resultUpdate = repository.save(record);

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
            ScenarioDb record = DomainBuilderDatabase.getScenario(name);
            ScenarioDb item = repository.save(record);
            ScenarioDb result = repository.findById(item.getId()).get();

            //test
            assertNotNull(result);
            assertEquals(result.getName(), name);
        }

        @Test
        void findByExtid() {
            String extid = UUID.randomUUID().toString();
            ScenarioDb record = DomainBuilderDatabase.getScenario();
            record.setExtid(extid);

            repository.save(record);
            ScenarioDb result = repository.findByExtid(extid).get();

            //test
            assertNotNull(result);
            assertEquals(result.getExtid(), extid);
        }

        @Test
        void findByName() {
            String name = "name" + DomainBuilderDatabase.randomString();
            ScenarioDb record = DomainBuilderDatabase.getScenario(name);

            repository.save(record);
            ScenarioDb result = repository.findByName(name).get();

            //test
            assertNotNull(result);
            assertEquals(result.getName(), name);
        }
    }
}
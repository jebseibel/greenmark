package com.greenmark.database.db.repository;

import com.greenmark.database.db.DomainBuilder;
import com.greenmark.database.db.entity.ScenarioEntity;
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
            ScenarioEntity item = DomainBuilder.getScenario();
            assertNull(item.getId());
            ScenarioEntity result = repository.save(item);

            //test
            assertNotNull(result);
            assertTrue(result.getId() != 0);
            assertNotNull(item.getCreatedAt());
        }

        @Test
        void createUniqueName() {
            String name = "notUnique_"+DomainBuilder.randomPositiveString();
            ScenarioEntity item1 = DomainBuilder.getScenario(name);
            ScenarioEntity item2 = DomainBuilder.getScenario(name);

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
            ScenarioEntity item = DomainBuilder.getScenario();
            assertNull(item.getId());
            assertNull(item.getModifiedAt());
            ScenarioEntity record = repository.save(item);

            //now update
            String changedDescription = "description_update";
            record.setDescription(changedDescription);
            record.setModifiedAt(LocalDateTime.now());
            ScenarioEntity resultUpdate = repository.save(record);

            //test
            assertNotNull(resultUpdate);
            assertEquals(resultUpdate.getDescription(), changedDescription);
            assertNotNull(resultUpdate.getModifiedAt());
        }

        @Test
        void delete() {
            ScenarioEntity item = DomainBuilder.getScenario();
            assertNull(item.getId());
            assertNull(item.getDeletedAt());
            ScenarioEntity record = repository.save(item);

            //now update
            record.setDeletedAt(LocalDateTime.now());
            ScenarioEntity resultUpdate = repository.save(record);

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
            ScenarioEntity record = DomainBuilder.getScenario(name);
            ScenarioEntity item = repository.save(record);
            ScenarioEntity result = repository.findById(item.getId()).get();

            //test
            assertNotNull(result);
            assertEquals(result.getName(), name);
        }

        @Test
        void findByExtid() {
            String extid = UUID.randomUUID().toString();
            ScenarioEntity record = DomainBuilder.getScenario();
            record.setExtid(extid);

            repository.save(record);
            ScenarioEntity result = repository.findByExtid(extid);

            //test
            assertNotNull(result);
            assertEquals(result.getExtid(), extid);
        }

        @Test
        void findByName() {
            String name = "name"+DomainBuilder.randomPositiveString();
            ScenarioEntity record = DomainBuilder.getScenario(name);

            repository.save(record);
            ScenarioEntity result = repository.findByName(name);

            //test
            assertNotNull(result);
            assertEquals(result.getName(), name);
        }
    }
}
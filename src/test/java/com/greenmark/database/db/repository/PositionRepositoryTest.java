package com.greenmark.database.db.repository;

import com.greenmark.common.enums.ActiveEnum;
import com.greenmark.database.DomainBuilderDatabase;
import com.greenmark.database.db.entity.Position;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PositionRepositoryTest {

    @Autowired
    private PositionRepository repository;

    @Nested
    class SuiteCrud {

        @Test
        void create() {
            Position item = DomainBuilderDatabase.getPosition();
            assertNull(item.getId());
            Position result = repository.save(item);

            //test
            assertNotNull(result);
            assertTrue(result.getId() != 0);
            assertNotNull(item.getCreatedAt());
        }

        @Test
        void createUniqueName() {
            String name = "notUnique_" + DomainBuilderDatabase.randomString();
            Position item1 = DomainBuilderDatabase.getPosition(name);
            Position item2 = DomainBuilderDatabase.getPosition(name);

            repository.save(item1);
            assertThrows(DataIntegrityViolationException.class, () -> repository.save(item2));

        }

        @Test
        void update() {
            Position item = DomainBuilderDatabase.getPosition();
            assertNull(item.getId());
            assertNull(item.getModifiedAt());
            Position record = repository.save(item);

            //now update
            String changedSymbol = DomainBuilderDatabase.getSymbolRandom();
            record.setSymbol(changedSymbol);
            record.setModifiedAt(LocalDateTime.now());
            Position resultUpdate = repository.save(record);

            //test
            assertNotNull(resultUpdate);
            assertEquals(resultUpdate.getSymbol(), changedSymbol);
            assertNotNull(resultUpdate.getModifiedAt());
        }

        @Test
        void delete() {
            Position item = DomainBuilderDatabase.getPosition();
            assertNull(item.getId());
            assertNull(item.getDeletedAt());
            Position record = repository.save(item);

            //now update
            record.setDeletedAt(LocalDateTime.now());
            Position resultUpdate = repository.save(record);

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
            Position record = DomainBuilderDatabase.getPosition(name);
            Position item = repository.save(record);
            Position result = repository.findById(item.getId()).get();

            //test
            assertNotNull(result);
            assertEquals(result.getName(), name);
        }

        @Test
        void findByExtid() {
            String extid = UUID.randomUUID().toString();
            Position record = DomainBuilderDatabase.getPosition();
            record.setExtid(extid);

            repository.save(record);
            Position result = repository.findByExtid(extid).get();

            //test
            assertNotNull(result);
            assertEquals(result.getExtid(), extid);
        }

        @Test
        void findByName() {
            String name = "name" + DomainBuilderDatabase.randomString();
            Position record = DomainBuilderDatabase.getPosition(name);

            repository.save(record);
            Position result = repository.findByName(name).get();

            //test
            assertNotNull(result);
            assertEquals(result.getName(), name);
        }

        @Test
        void findActive_toList() {
            String symbol1 = DomainBuilderDatabase.getSymbolRandom();
            String symbol2 = DomainBuilderDatabase.getSymbolRandom();
            Position record1 = DomainBuilderDatabase.getPosition(symbol1);
            Position record2 = DomainBuilderDatabase.getPosition(symbol2);
            repository.save(record1);
            repository.save(record2);

            List<Position> results = repository.findByActive(ActiveEnum.ACTIVE.value);

            //test
            assertNotNull(results);
            assertTrue(results.size() > 1);
        }

        @Test
        void findActive_checkNoInactive() {
            String symbol1 = DomainBuilderDatabase.getSymbolRandom();
            String symbol2 = DomainBuilderDatabase.getSymbolRandom();
            Position record1 = DomainBuilderDatabase.getPosition(symbol1);
            Position record2 = DomainBuilderDatabase.getPosition(symbol2);
            record2.setActive(ActiveEnum.INACTIVE.value);
            repository.save(record1);
            repository.save(record2);

            List<Position> results = repository.findByActive(ActiveEnum.ACTIVE.value);

            //test
            assertNotNull(results);
            assertTrue(results.size() > 0);
            assertFalse(results.contains(record2));
        }
    }
}
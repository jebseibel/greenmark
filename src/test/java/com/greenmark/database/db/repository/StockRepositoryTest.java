package com.greenmark.database.db.repository;

import com.greenmark.database.db.DomainBuilder;
import com.greenmark.database.db.entity.StockEntity;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StockRepositoryTest {

    @Autowired
    private StockRepository repository;

    @Nested
    class SuiteCrud {

        @Test
        void create() {
            StockEntity item = DomainBuilder.getStock();
            assertNull(item.getId());
            StockEntity result = repository.save(item);

            //test
            assertNotNull(result);
            assertTrue(result.getId() != 0);
            assertNotNull(item.getCreatedAt());
        }

        @Test
        void createUniqueName() {
            String name = "notUnique_"+DomainBuilder.randomPositiveString();
            StockEntity item1 = DomainBuilder.getStock(name);
            StockEntity item2 = DomainBuilder.getStock(name);

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
            StockEntity item = DomainBuilder.getStock();
            assertNull(item.getId());
            assertNull(item.getModifiedAt());
            StockEntity record = repository.save(item);

            //now update
            String changedDescription = "description_update";
            record.setDescription(changedDescription);
            record.setModifiedAt(LocalDateTime.now());
            StockEntity resultUpdate = repository.save(record);

            //test
            assertNotNull(resultUpdate);
            assertEquals(resultUpdate.getDescription(), changedDescription);
            assertNotNull(resultUpdate.getModifiedAt());
        }

        @Test
        void delete() {
            StockEntity item = DomainBuilder.getStock();
            assertNull(item.getId());
            assertNull(item.getDeletedAt());
            StockEntity record = repository.save(item);

            //now update
            record.setDeletedAt(LocalDateTime.now());
            StockEntity resultUpdate = repository.save(record);

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
            StockEntity record = DomainBuilder.getStock(name);
            StockEntity item = repository.save(record);
            StockEntity result = repository.findById(item.getId()).get();

            //test
            assertNotNull(result);
            assertEquals(result.getName(), name);
        }

        @Test
        void findByExtid() {
            String extid = UUID.randomUUID().toString();
            StockEntity record = DomainBuilder.getStock();
            record.setExtid(extid);

            repository.save(record);
            StockEntity result = repository.findByExtid(extid);

            //test
            assertNotNull(result);
            assertEquals(result.getExtid(), extid);
        }

        @Test
        void findByName() {
            String name = "name"+DomainBuilder.randomPositiveString();
            StockEntity record = DomainBuilder.getStock(name);

            repository.save(record);
            StockEntity result = repository.findByName(name);

            //test
            assertNotNull(result);
            assertEquals(result.getName(), name);
        }
    }
}
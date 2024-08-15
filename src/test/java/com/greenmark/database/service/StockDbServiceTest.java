package com.greenmark.database.service;

import com.greenmark.common.database.domain.StockDb;
import com.greenmark.database.DomainBuilder;
import com.greenmark.database.exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StockDbServiceTest {

    @Autowired
    private StockDbService service;

    @Nested
    class CreateTests {

        String random = DomainBuilder.randomString();
        String symbol = DomainBuilder.getSymbolRandom();
        String name = DomainBuilder.getNameRandom(random);

        @Test
        void created() throws Exception {
            // test
            System.out.println(symbol);
            StockDb result = service.create(symbol, name);

            // validate
            assertNotNull(result);
            assertEquals(result.getName(), name);
            assertEquals(result.getSymbol(), symbol);
        }

        @Test
        void createdTooLong() {
            // test
            try {
                String symbol = DomainBuilder.getStringTestUUID();
                service.create(symbol, name);
                fail();
            } catch (DatabaseCreateFailureException e) {
                assertTrue(true);
            } catch (Exception e) {
                fail();
            }
        }
    }

    @Nested
    class UpdateTests {

        StockDb record;
        String symbol;
        String name;

        @BeforeEach
        void beforeEach() throws DatabaseCreateFailureException, DatabaseAccessException {
            symbol = DomainBuilder.getSymbolRandom();
            name = DomainBuilder.getNameRandom();
            record = service.create(symbol, name);
        }

        @Test
        void updated() throws DatabaseUpdateFailureException, DatabaseRetrievalFailureException {
            String newName = DomainBuilder.getNameRandom();

            //execute
            StockDb result = service.update(symbol, newName);

            // validate
            assertNotNull(result);
            assertEquals(result.getName(), newName);
        }

        @Test
        void updatedBadSymbol() {
            // test
            try {
                String badSymbol = UUID.randomUUID().toString();
                service.update(badSymbol, name);
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

        StockDb record;
        String symbol;
        String name;

        @BeforeEach
        void beforeEach() throws DatabaseCreateFailureException, DatabaseAccessException {
            symbol = DomainBuilder.getSymbolRandom();
            name = DomainBuilder.getNameRandom();
            record = service.create(symbol, name);
        }

        @Test
        void findByExtid() throws DatabaseRetrievalFailureException {
            // test
            StockDb result = service.findBySymbol(symbol);

            // validate
            assertNotNull(result);
            assertEquals(result.getSymbol(), symbol);
        }

        @Test
        void findByExtid_NotFound() {
            // test
            try {
                String badExtid = UUID.randomUUID().toString();
                service.findBySymbol(badExtid);
                fail();
            } catch (DatabaseRetrievalFailureException e) {
                assertTrue(true);
            } catch (Exception e) {
                fail();
            }
        }
    }

    @Nested
    class DeleteTests {

        StockDb record;
        String symbol;
        String name;

        @BeforeEach
        void beforeEach() throws DatabaseCreateFailureException, DatabaseAccessException {
            symbol = DomainBuilder.getSymbolRandom();
            name = DomainBuilder.getNameRandom();
            record = service.create(symbol, name);
        }

        @Test
        void deleted() throws DatabaseRetrievalFailureException, DatabaseDeleteFailureException {
            //execute
            boolean result = service.delete(symbol);

            // validate
            assertTrue(result);
        }

        @Test
        void deleteBadSymbol() throws DatabaseDeleteFailureException {
            // test
            try {
                String symbol = UUID.randomUUID().toString();
                service.delete(symbol);
                fail();
            } catch (DatabaseRetrievalFailureException e) {
                assertTrue(true);
            }
        }
    }
}
package com.greenmark.database.service;

import com.greenmark.database.db.DomainBuilder;
import com.greenmark.common.database.domain.StockDb;
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

        String random = DomainBuilder.randomPositiveString();
        String symbol = DomainBuilder.getSymbolRandom();
        String name = DomainBuilder.getNameRandom(random);


        @Test
        void created() throws Exception {
            // test
            String extid = DomainBuilder.getUUID();
            System.out.println(symbol);
            StockDb result = service.create(extid, symbol, name);

            // validate
            assertNotNull(result);
            assertEquals(result.getName(), name);
            assertEquals(result.getSymbol(), symbol);
        }

        @Test
        void createdTooLong() {
            // test
            try {
                String extid = DomainBuilder.getStringTestUUID();
                service.create(extid, symbol, name);
                fail();
            }
            catch (StockCreateFailureException e) {
                assertTrue(true);
            }
            catch (Exception e) {
                fail();
            }
        }
    }

    @Nested
    class UpdateTests {

        StockDb record;
        String extid;
        String random = DomainBuilder.randomPositiveString();
        String name = DomainBuilder.getNameRandom(random);
        String symbol = DomainBuilder.getSymbolRandom();

        @BeforeEach
        void beforeEach() throws StockCreateFailureException, DatabaseAccessException {
            extid = DomainBuilder.getUUID();
            record = service.create(extid, symbol, name);
        }

        @Test
        void updated() throws StockUpdateFailureException, StockRetrievalFailureException {
            String newName = DomainBuilder.getNameRandom();
            String newSymbol = DomainBuilder.getSymbolRandom();

            //execute
            StockDb result = service.update(extid, newSymbol, newName);

            // validate
            assertNotNull(result);
            assertEquals(result.getExtid(), extid);
            assertEquals(result.getName(), newName);
            assertEquals(result.getSymbol(), newSymbol);
        }

        @Test
        void updatedBadExtid() {
            // test
            try {
                String badExtid = UUID.randomUUID().toString();
                service.update(badExtid, symbol, name);
                fail();
            }
            catch (StockRetrievalFailureException e) {
                assertTrue(true);
            }
            catch (Exception e) {
                fail();
            }
        }
    }

    @Nested
    class FindTests {

        StockDb record;
        String extid;
        String random = DomainBuilder.randomPositiveString();
        String symbol = DomainBuilder.getSymbolRandom();
        String name = DomainBuilder.getNameRandom(random);


        @BeforeEach
        void beforeEach() throws StockCreateFailureException, DatabaseAccessException {
            extid = DomainBuilder.getUUID();
            record = service.create(extid, symbol, name);
        }

        @Test
        void findByExtid() throws StockRetrievalFailureException {
            // test
            StockDb result = service.findByExtid(extid);

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
            } catch (StockRetrievalFailureException e) {
                assertTrue(true);
            }
            catch (Exception e) {
                fail();
            }
        }
    }

    @Nested
    class DeleteTests {

        StockDb record;
        String extid;
        String random = DomainBuilder.randomPositiveString();
        String symbol = DomainBuilder.getSymbolRandom();
        String name = DomainBuilder.getNameRandom(random);

        @BeforeEach
        void beforeEach() throws StockCreateFailureException, DatabaseAccessException {
            extid = DomainBuilder.getUUID();
            record = service.create(extid, symbol, name);
        }

        @Test
        void deleted() throws StockRetrievalFailureException, StockDeleteFailureException {
            //execute
            boolean result = service.delete(extid);

            // validate
            assertTrue(result);
        }

        @Test
        void updatedBadExtid() throws StockDeleteFailureException {
            // test
            try {
                String badExtid = UUID.randomUUID().toString();
                service.delete(badExtid);
                fail();
            }
            catch (StockRetrievalFailureException e) {
                assertTrue(true);
            }
        }
    }
}
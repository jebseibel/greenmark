package com.greenmark.database.service;

import com.greenmark.common.database.domain.StockDailyDb;
import com.greenmark.common.database.domain.StockData;
import com.greenmark.database.db.DomainBuilder;
import com.greenmark.database.exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StockDailyDbServiceTest {

    @Autowired
    private StockDailyDbService service;

    @Nested
    class CreateTests {

        String symbol = DomainBuilder.getSymbolRandom();

        @Test
        void createdNew() throws Exception {
            // test
            StockData stockData = DomainBuilder.getStockData();
            StockDailyDb result = service.create(symbol, stockData);

            // validate
            assertNotNull(result);
            assertEquals(result.getSymbol(), symbol);
        }

        @Test
        void createdTooLong() {
            // test
            try {
                StockData stockData = DomainBuilder.getStockData();
                String symbol = DomainBuilder.getStringTestUUID();
                service.create(symbol, stockData);
                fail();
            }
            catch (DatabaseCreateFailureException e) {
                assertTrue(true);
            }
            catch (Exception e) {
                fail();
            }
        }

        @Test
        void createdAlready() throws Exception {
            // test
            StockData stockData = DomainBuilder.getStockData();
            StockDailyDb result = service.create(symbol, stockData);

            try {
                service.create(symbol, stockData);
                fail();
            } catch (DatabaseCreateFailureException e) {
                System.out.println(e.getMessage());
                assertTrue(true);
            }
            catch (Exception e) {
                fail();
            }

            // validate
            assertNotNull(result);
            assertEquals(result.getSymbol(), symbol);
        }
    }

    @Nested
    class UpdateTests {

        StockDailyDb record;
        StockData stockData;
        String symbol = DomainBuilder.getSymbolRandom();

        @BeforeEach
        void beforeEach() throws DatabaseCreateFailureException, DatabaseAccessException {
            stockData = DomainBuilder.getStockData();
            record = service.create(symbol, stockData);
        }

        @Test
        void updated() throws DatabaseUpdateFailureException, DatabaseRetrievalFailureException, DatabaseAccessException {
            BigDecimal newCurrent = DomainBuilder.randomBigDecimal();
            stockData.setCurrent(newCurrent);

            //execute
            StockDailyDb result = service.update(symbol, stockData);

            // validate
            assertNotNull(result);
            assertEquals(result.getSymbol(), symbol);
            assertEquals(result.getCurrent(), newCurrent);
        }

        @Test
        void updatedBadSymbol() {
            // test
            try {
                String badSymbol = UUID.randomUUID().toString();
                service.update(badSymbol, stockData);
                fail();
            }
            catch (DatabaseRetrievalFailureException e) {
                assertTrue(true);
            }
            catch (Exception e) {
                fail();
            }
        }

        @Test
        void updatedBadStockdata() {
            // test
            try {
                stockData.setCurrent(null);
                service.update(symbol, stockData);
                fail();
            }
            catch (DatabaseUpdateFailureException e) {
                assertTrue(true);
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
                fail();
            }
        }
    }

    @Nested
    class DeleteTests {

        StockDailyDb record;
        StockData stockData;
        String symbol = DomainBuilder.getSymbolRandom();

        @BeforeEach
        void beforeEach() throws DatabaseCreateFailureException, DatabaseAccessException {
            stockData = DomainBuilder.getStockData();
            record = service.create(symbol, stockData);
        }

        @Test
        void deleted() throws DatabaseRetrievalFailureException, DatabaseDeleteFailureException {
            //execute
            boolean result = service.delete(symbol);

            // validate
            assertTrue(result);
        }

        @Test
        void updatedBadSymbol() throws DatabaseDeleteFailureException {
            // test
            try {
                String symbol = UUID.randomUUID().toString();
                service.delete(symbol);
                fail();
            }
            catch (DatabaseRetrievalFailureException e) {
                assertTrue(true);
            }
        }
    }

}
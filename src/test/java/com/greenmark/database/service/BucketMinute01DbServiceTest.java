package com.greenmark.database.service;

import com.greenmark.common.database.domain.BucketMinute01Db;
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
class BucketMinute01DbServiceTest {

    @Autowired
    private BucketMinute01DbService service;

    @Nested
    class CreateTests {

        String symbol = DomainBuilder.getSymbolRandom();

        @Test
        void createdNew() throws Exception {
            // test
            StockData stockData = DomainBuilder.getStockData();
            BucketMinute01Db result = service.create(symbol, stockData);

            // validate
            assertNotNull(result);
            assertEquals(result.getSymbol(), symbol);
        }

        @Test
        void createdTooLong() {
            StockData stockData = DomainBuilder.getStockData();
            String symbol = DomainBuilder.getStringTestUUID();

            assertThrows(DatabaseCreateFailureException.class, () -> service.create(symbol, stockData));
        }

        @Test
        void createdAlready() throws Exception {
            // test
            StockData stockData = DomainBuilder.getStockData();
            service.create(symbol, stockData);

            //try to create again
            assertThrows(DatabaseCreateFailureException.class, () -> service.create(symbol, stockData));
        }
    }

    @Nested
    class UpdateTests {

        BucketMinute01Db record;
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
            BucketMinute01Db result = service.update(symbol, stockData);

            // validate
            assertNotNull(result);
            assertEquals(result.getSymbol(), symbol);
            assertEquals(result.getCurrent(), newCurrent);
        }

        @Test
        void updatedBadSymbol() {
            String badSymbol = UUID.randomUUID().toString();
            assertThrows(DatabaseRetrievalFailureException.class, () -> service.update(badSymbol, stockData));
        }

        @Test
        void updatedBadStockdata() {
            stockData.setCurrent(null);
            assertThrows(DatabaseUpdateFailureException.class, () -> service.update(symbol, stockData));
        }
    }

    @Nested
    class DeleteTests {

        BucketMinute01Db record;
        StockData stockData;
        String symbol = DomainBuilder.getSymbolRandom();

        @BeforeEach
        void beforeEach() throws DatabaseCreateFailureException, DatabaseAccessException {
            stockData = DomainBuilder.getStockData();
            record = service.create(symbol, stockData);
        }

        @Test
        void deleted() throws DatabaseRetrievalFailureException, DatabaseDeleteFailureException {
            boolean result = service.delete(symbol);
            assertTrue(result);
        }

        @Test
        void updatedBadSymbol() {
            String symbol = UUID.randomUUID().toString();
            assertThrows(DatabaseRetrievalFailureException.class, () -> service.delete(symbol));
        }
    }

    @Nested
    class FindTests {

        BucketMinute01Db record;
        StockData stockData;
        String symbol;

        @BeforeEach
        void beforeEach() throws DatabaseCreateFailureException, DatabaseAccessException {
            symbol = DomainBuilder.getSymbolRandom();
            stockData = DomainBuilder.getStockData();
            record = service.create(symbol, stockData);
        }

        @Test
        void find() throws DatabaseRetrievalFailureException {
            BucketMinute01Db result = service.findBySymbol(symbol);
            assertNotNull(result);
        }

        @Test
        void findError() throws DatabaseRetrievalFailureException {
            String badSymbol = DomainBuilder.getSymbolRandom();
            assertThrows(DatabaseRetrievalFailureException.class, () -> service.findBySymbol(badSymbol));
        }
    }

    @Nested
    class FindActiveTests {

        BucketMinute01Db record1;
        StockData stockData1;
        String symbol1;

        @BeforeEach
        void beforeEach() throws DatabaseCreateFailureException, DatabaseAccessException {
            symbol1 = DomainBuilder.getSymbolRandom();
            stockData1 = DomainBuilder.getStockData();
            record1 = service.create(symbol1, stockData1);
        }
    }
}
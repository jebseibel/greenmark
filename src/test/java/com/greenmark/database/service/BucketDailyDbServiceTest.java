package com.greenmark.database.service;

import com.greenmark.common.database.domain.BucketDailyDb;
import com.greenmark.common.database.domain.StockData;
import com.greenmark.database.DomainBuilder;
import com.greenmark.database.exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BucketDailyDbServiceTest {

    @Autowired
    private BucketDailyDbService service;

    @Nested
    class CreateTests {

        String symbol = DomainBuilder.getSymbolRandom();

        @Test
        void createdNew() throws Exception {
            // test
            StockData stockData = DomainBuilder.getStockData();
            BucketDailyDb result = service.create(symbol, stockData);

            // validate
            assertNotNull(result);
            assertEquals(result.getSymbol(), symbol);
        }

        @Test
        void createdTooLong() {
            StockData stockData = DomainBuilder.getStockData();
            String symbol = DomainBuilder.getStringTestUUIDTooLong();

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

        BucketDailyDb record;
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
            BucketDailyDb result = service.update(symbol, stockData);

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

        BucketDailyDb record;
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

        BucketDailyDb record;
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
            BucketDailyDb result = service.findBySymbol(symbol);
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

        BucketDailyDb record1;
        StockData stockData1;
        String symbol1;

        @BeforeEach
        void beforeEach() throws DatabaseCreateFailureException, DatabaseAccessException {
            symbol1 = DomainBuilder.getSymbolRandom();
            stockData1 = DomainBuilder.getStockData();
            record1 = service.create(symbol1, stockData1);
        }

        @Test
        void findActive_both() throws DatabaseRetrievalFailureException, DatabaseAccessException, DatabaseCreateFailureException {
            String symbol2 = DomainBuilder.getSymbolRandom();
            StockData stockData2 = DomainBuilder.getStockData();
            BucketDailyDb record2 = service.create(symbol2, stockData2);

            //execute
            List<BucketDailyDb> results = service.findActive();

            // validate - using Streams :)
            BucketDailyDb hasRecord1 = results.stream()
                    .filter(result -> record1.getSymbol().equals(result.getSymbol()))
                    .findAny()
                    .orElse(null);

            BucketDailyDb hasRecord2 = results.stream()
                    .filter(result -> record2.getSymbol().equals(result.getSymbol()))
                    .findAny()
                    .orElse(null);

            // validate
            assertNotNull(results);
            assertTrue(results.size() > 1);
            assertNotNull(hasRecord1);
            assertNotNull(hasRecord2);
        }

        @Test
        void findActive_one() throws DatabaseRetrievalFailureException, DatabaseAccessException, DatabaseDeleteFailureException, DatabaseCreateFailureException {
            String symbol2 = DomainBuilder.getSymbolRandom();
            StockData stockData2 = DomainBuilder.getStockData();
            BucketDailyDb record2 = service.create(symbol2, stockData2);
            //set to inactive
            service.delete(symbol2);

            //execute
            List<BucketDailyDb> results = service.findActive();

            // validate - using Streams :)
            BucketDailyDb hasRecord1 = results.stream()
                    .filter(result -> record1.getSymbol().equals(result.getSymbol()))
                    .findAny()
                    .orElse(null);

            BucketDailyDb hasRecord2 = results.stream()
                    .filter(result -> record2.getSymbol().equals(result.getSymbol()))
                    .findAny()
                    .orElse(null);

            assertNotNull(results);
            assertTrue(results.size() > 1);
            assertNotNull(hasRecord1);
            assertNull(hasRecord2);
        }
    }
}
package com.greenmark.database.service;

import com.greenmark.common.database.domain.AccountDb;
import com.greenmark.common.database.domain.BucketDailyDb;
import com.greenmark.common.database.domain.StockData;
import com.greenmark.common.enums.ActiveEnum;
import com.greenmark.database.db.DomainBuilder;
import com.greenmark.database.db.entity.Stock;
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
import static org.junit.jupiter.api.Assertions.assertTrue;

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
            BucketDailyDb result = service.create(symbol, stockData);

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
            //execute
            BucketDailyDb result = service.findBySymbol(symbol);

            // validate
            assertNotNull(result);
        }

        @Test
        void findError() throws DatabaseRetrievalFailureException {
            String badSymbol = DomainBuilder.getSymbolRandom();

            try {
                BucketDailyDb result = service.findBySymbol(badSymbol);
                assertTrue(false);
            }
            catch (DatabaseRetrievalFailureException e) {
                assertTrue(true);
            }
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
        void findActive_both() throws DatabaseRetrievalFailureException, DatabaseAccessException {
            String symbol2 = DomainBuilder.getSymbolRandom();
            StockData stockData2 = DomainBuilder.getStockData();
            BucketDailyDb record2 = service.create(symbol2, stockData2);

            //execute
            List<BucketDailyDb> results = service.findActive();

            // validate
            assertNotNull(results);
            assertTrue(results.size() > 1);
            assertTrue(results.contains(record1));
            assertTrue(results.contains(record2));
        }

        @Test
        void findActive_one() throws DatabaseRetrievalFailureException, DatabaseAccessException, DatabaseDeleteFailureException {
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
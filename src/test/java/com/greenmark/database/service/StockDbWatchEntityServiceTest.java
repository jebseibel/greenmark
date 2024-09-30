package com.greenmark.database.service;

import com.greenmark.common.database.domain.MarketData;
import com.greenmark.common.database.domain.StockWatch;
import com.greenmark.common.enums.TimeframeType;
import com.greenmark.database.DomainBuilderDatabase;
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
class StockDbWatchEntityServiceTest {

    @Autowired
    private StockWatchDbService service;

    @Nested
    class CreateTests {


        @Test
        void createdNew() throws Exception {
            // test
            String symbol = DomainBuilderDatabase.getSymbolRandom();
            TimeframeType timeframeType = TimeframeType.DAILY;
            MarketData marketData = DomainBuilderDatabase.getMarketData();
            StockWatch result = service.create(symbol, timeframeType, marketData);

            // validate
            assertNotNull(result);
            assertEquals(result.getSymbol(), symbol);
        }

        @Test
        void createdTooLong() {
            // test
            String symbol = DomainBuilderDatabase.getStringTestUUIDTooLong();
            TimeframeType timeframeType = TimeframeType.DAILY;
            MarketData marketData = DomainBuilderDatabase.getMarketData();

            assertThrows(DatabaseCreateFailureException.class, () -> service.create(symbol, timeframeType, marketData));
        }

        @Test
        void createdAlready() throws Exception {
            // test
            String symbol = DomainBuilderDatabase.getStringTestUUIDTooLong();
            TimeframeType timeframeType = TimeframeType.DAILY;
            MarketData marketData = DomainBuilderDatabase.getMarketData();
            //service.create(symbol, timeframeType, marketData);

            //try to create again
            assertThrows(DatabaseCreateFailureException.class, () -> service.create(symbol, timeframeType, marketData));
        }
    }

    @Nested
    class UpdateTests {

        StockWatch record;
        MarketData marketData;
        String symbol = DomainBuilderDatabase.getSymbolRandom();
        TimeframeType timeframeType = TimeframeType.DAILY;

        @BeforeEach
        void beforeEach() throws DatabaseCreateFailureException, DatabaseAccessException {
            marketData = DomainBuilderDatabase.getMarketData();
            record = service.create(symbol, timeframeType, marketData);
        }

        @Test
        void updated() throws DatabaseUpdateFailureException, DatabaseRetrievalFailureException, DatabaseAccessException {
            BigDecimal newCurrent = DomainBuilderDatabase.randomBigDecimal();
            marketData.setCurrent(newCurrent);

            //execute
            StockWatch result = service.update(symbol, marketData);

            // validate
            assertNotNull(result);
            assertEquals(result.getSymbol(), symbol);
            assertEquals(result.getCurrent(), newCurrent);
        }

        @Test
        void updatedBadSymbol() {
            String badSymbol = UUID.randomUUID().toString();
            assertThrows(DatabaseRetrievalFailureException.class, () -> service.update(badSymbol, marketData));
        }

        @Test
        void updatedBadStockdata() {
            marketData.setCurrent(null);
            assertThrows(DatabaseUpdateFailureException.class, () -> service.update(symbol, marketData));
        }
    }

    @Nested
    class DeleteTests {

        StockWatch record;
        MarketData marketData;
        String symbol = DomainBuilderDatabase.getSymbolRandom();
        TimeframeType timeframeType = TimeframeType.DAILY;

        @BeforeEach
        void beforeEach() throws DatabaseCreateFailureException, DatabaseAccessException {
            marketData = DomainBuilderDatabase.getMarketData();
            record = service.create(symbol, timeframeType, marketData);
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

        StockWatch record;
        MarketData marketData;
        String symbol;
        TimeframeType timeframeType;

        @BeforeEach
        void beforeEach() throws DatabaseCreateFailureException, DatabaseAccessException {
            symbol = DomainBuilderDatabase.getSymbolRandom();
            timeframeType = TimeframeType.DAILY;
            marketData = DomainBuilderDatabase.getMarketData();
            record = service.create(symbol, timeframeType, marketData);
        }

        @Test
        void find() throws DatabaseRetrievalFailureException {
            StockWatch result = service.findBySymbol(symbol);
            assertNotNull(result);
        }

        @Test
        void findError() throws DatabaseRetrievalFailureException {
            String badSymbol = DomainBuilderDatabase.getSymbolRandom();
            assertThrows(DatabaseRetrievalFailureException.class, () -> service.findBySymbol(badSymbol));
        }
    }

    @Nested
    class FindActiveTests {

        StockWatch record1;
        MarketData marketData1;
        String symbol1;
        TimeframeType timeframeType;

        @BeforeEach
        void beforeEach() throws DatabaseCreateFailureException, DatabaseAccessException {
            symbol1 = DomainBuilderDatabase.getSymbolRandom();
            timeframeType = TimeframeType.DAILY;
            marketData1 = DomainBuilderDatabase.getMarketData();
            record1 = service.create(symbol1,timeframeType, marketData1);
        }

        @Test
        void findActive_both() throws DatabaseRetrievalFailureException, DatabaseAccessException, DatabaseCreateFailureException {
            String symbol2 = DomainBuilderDatabase.getSymbolRandom();
            MarketData marketData2 = DomainBuilderDatabase.getMarketData();
            StockWatch record2 = service.create(symbol2, timeframeType, marketData2);

            //execute
            List<StockWatch> results = service.findAll();

            // validate - using Streams :)
            StockWatch hasRecord1 = results.stream()
                    .filter(result -> record1.getSymbol().equals(result.getSymbol()))
                    .findAny()
                    .orElse(null);

            StockWatch hasRecord2 = results.stream()
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
            String symbol2 = DomainBuilderDatabase.getSymbolRandom();
            TimeframeType timeframeType = TimeframeType.DAILY;
            MarketData marketData2 = DomainBuilderDatabase.getMarketData();
            StockWatch record2 = service.create(symbol2, timeframeType, marketData2);
            //set to inactive
            service.delete(symbol2);

            //execute
            List<StockWatch> results = service.findAll();

            // validate - using Streams :)
            StockWatch hasRecord1 = results.stream()
                    .filter(result -> record1.getSymbol().equals(result.getSymbol()))
                    .findAny()
                    .orElse(null);

            StockWatch hasRecord2 = results.stream()
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
package com.greenmark.common.indicators;

import com.greenmark.common.database.domain.StockWatchDb;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MacdTest {

    BigDecimal bigDecimal01 = new BigDecimal(1);
    BigDecimal bigDecimal10 = new BigDecimal(10);
    BigDecimal bigDecimal20 = new BigDecimal(20);

    Macd macd = new Macd();
    StockWatchDb stockWatchDb = new StockWatchDb();

    @BeforeEach
    void beforeEach() {
        stockWatchDb.setMacd(bigDecimal10);
    }

    @Test
    void isGreater() {
        boolean result = macd.isGreater(stockWatchDb, bigDecimal20);
        assertTrue(result);
    }

    @Test
    void isLess() {
        boolean result = macd.isLess(stockWatchDb, bigDecimal01);
        assertTrue(result);
    }

    @Test
    void isEqual() {
        boolean result = macd.isEqual(stockWatchDb, bigDecimal10);
        assertTrue(result);
    }
}
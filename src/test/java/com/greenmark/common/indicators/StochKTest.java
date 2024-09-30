package com.greenmark.common.indicators;

import com.greenmark.common.database.domain.StockWatch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;

class StochKTest {

    BigDecimal bigDecimal01 = new BigDecimal(1);
    BigDecimal bigDecimal10 = new BigDecimal(10);
    BigDecimal bigDecimal20 = new BigDecimal(20);

    StochK stochk = new StochK();
    StockWatch stockWatch = new StockWatch();

    @BeforeEach
    void beforeEach() {
        stockWatch.setStochk(bigDecimal10);
    }

    @Test
    void isGreater() {
        boolean result = stochk.isGreater(stockWatch, bigDecimal20);
        assertTrue(result);
    }

    @Test
    void isLess() {
        boolean result = stochk.isLess(stockWatch, bigDecimal01);
        assertTrue(result);
    }

    @Test
    void isEqual() {
        boolean result = stochk.isEqual(stockWatch, bigDecimal10);
        assertTrue(result);
    }
}
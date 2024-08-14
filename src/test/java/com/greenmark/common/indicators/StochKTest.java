package com.greenmark.common.indicators;

import com.greenmark.common.database.domain.BucketData;
import com.greenmark.common.database.domain.StockDailyDb;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;

class StochKTest {

    BigDecimal bigDecimal01 = new BigDecimal(1);
    BigDecimal bigDecimal10 = new BigDecimal(10);
    BigDecimal bigDecimal20 = new BigDecimal(20);

    StochK stochk = new StochK();
    BucketData bucketDataStochkIs10 = new StockDailyDb();

    @BeforeEach
    void beforeEach() {
        bucketDataStochkIs10.setStochk(bigDecimal10);
    }

    @Test
    void isGreater() {
        boolean result = stochk.isGreater(bucketDataStochkIs10, bigDecimal20);
        assertTrue(result);
    }

    @Test
    void isLess() {
        boolean result = stochk.isLess(bucketDataStochkIs10, bigDecimal01);
        assertTrue(result);
    }

    @Test
    void isEqual() {
        boolean result = stochk.isEqual(bucketDataStochkIs10, bigDecimal10);
        assertTrue(result);
    }
}
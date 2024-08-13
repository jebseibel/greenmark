package com.greenmark.app.macds;

import com.greenmark.common.indicators.Macd;
import com.greenmark.common.database.domain.BucketData;
import com.greenmark.common.database.domain.StockDailyDb;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class MacdTest {

    BigDecimal bigDecimal01 = new BigDecimal(1);
    BigDecimal bigDecimal10 = new BigDecimal(10);
    BigDecimal bigDecimal20 = new BigDecimal(20);

    Macd macd = new Macd();
    BucketData bucketDataMacdIs10 = new StockDailyDb();

    @BeforeEach
    void beforeEach()  {
        bucketDataMacdIs10.setMacd(bigDecimal10);
    }

    @Test
    void isGreater() {
        boolean result = macd.isGreater(bucketDataMacdIs10, bigDecimal20);
        assertTrue(result);
    }

    @Test
    void isLess() {
        boolean result = macd.isLess(bucketDataMacdIs10, bigDecimal01);
        assertTrue(result);
    }

    @Test
    void isEqual() {
        boolean result = macd.isEqual(bucketDataMacdIs10, bigDecimal10);
        assertTrue(result);
    }
}
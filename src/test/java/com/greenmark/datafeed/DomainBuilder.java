package com.greenmark.datafeed;

import com.greenmark.common.datafeed.TechnicalIndicatorRequest;
import com.greenmark.common.enums.ResolutionType;
import com.greenmark.common.enums.TaType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

public class DomainBuilder {
    public static String NAME = "Name_";
    public static String DESCRIPTION = "Desc_";

    // ///////////////////////////////////////////////////////////////////

    public static TechnicalIndicatorRequest getMacd(String symbol) {
        return  getTechnicalIndicatorRequest(symbol, TaType.MACD, ResolutionType.MINUTE01);
    }

    public static TechnicalIndicatorRequest getTechnicalIndicatorRequest(
            String symbol,
            TaType thisTa,
            ResolutionType thisResolution
    ) {
        TechnicalIndicatorRequest item = new TechnicalIndicatorRequest();
        item.setSymbol(symbol);
        item.setType(thisTa);
        item.setResolution(thisResolution);
        item.setLocalDateTime(LocalDateTime.now());
        return item;
    }


    // //////////////////////////////////////////////////////////////////
    public static BigDecimal getBigDecimal(String value) {
        return new BigDecimal(value);
    }

    public static String getRandom() {
        return randomString();
    }


    public static final String getUUID() {
        return UUID.randomUUID().toString();
    }

    public static final String getStringTestUUID() {
        return "123456789012345678901234567890123456789012345678901234567890";
    }

    public static String randomString() {
        return String.valueOf(new Random().nextInt());
    }

    public static BigDecimal randomBigDecimal() {
        float random = randomFloat();
        return new BigDecimal(random);
    }

    public static float randomFloat() {
        return new Random().nextFloat();
    }

    public static long randomLong() {
        return new Random().nextLong();
    }

    public static int randomPositiveInt() {
        return new Random().nextInt();
    }

    public static int randomPositiveInt(int maxValue) {
        return new Random().nextInt(maxValue);
    }

}

package com.greenmark.database;

import com.greenmark.common.database.domain.AccountDb;
import com.greenmark.common.database.domain.BucketMinute60Db;
import com.greenmark.common.database.domain.StockData;
import com.greenmark.common.enums.ActiveEnum;
import com.greenmark.database.db.entity.*;
import com.greenmark.database.db.mapper.AccountMapper;
import com.greenmark.database.db.mapper.BucketMinute60Mapper;
import com.greenmark.datafeed.finnhub.models.Quote;
import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

public class DomainBuilder {
    public static String NAME = "Name_";
    public static String DESCRIPTION = "Desc_";

    // ///////////////////////////////////////////////////////////////////
    public static AccountDb getAccountDb(Account item) {
        return new AccountMapper().toDb(item);
    }

    public static Account getAccount() {
        return getAccount(null, null, null);
    }

    public static Account getAccount(String name) {
        return getAccount(name, null, null);
    }

    public static Account getAccount(
            String thisName,
            String thisDescription,
            String thisExtId
    ) {
        String random = getRandom();
        Account item = new Account();
        item.setExtid(thisName != null ? thisName : UUID.randomUUID().toString());
        item.setName(thisName != null ? thisName : getNameRandom(random));
        item.setDescription(thisDescription != null ? thisDescription : getDescriptionRandom(random));
        item.setActive(ActiveEnum.ACTIVE.value);
        item.setCreatedAt(LocalDateTime.now());
        item.setModifiedAt(null);
        item.setDeletedAt(null);
        return item;
    }

//          Account item = Account.builder()
//                .extid(UUID.randomUUID().toString())
//                .name( thisName != null ? thisName : getNameRandom())
//                .description(thisDescription)
//                .createdAt(LocalDateTime.now())
//                .modifiedAt(null)
//                .deletedAt(null)
//                .active(ActiveEnum.ACTIVE.value)
//        .build();

    // //////////////////////////////////////////////////////////////////
    // Bucket 60
    // //////////////////////////////////////////////////////////////////
    public static BucketMinute60Db getBucketMinute60DbMacd(BigDecimal macd) {
        BucketMinute60 item = getBucketMinute60(getSymbolRandom());
        item.setMacd(macd);
        return new BucketMinute60Mapper().toDb(item);
    }

    public static BucketMinute60Db getBucketMinute60Db() {
        BucketMinute60 item = getBucketMinute60(getSymbolRandom());
        return new BucketMinute60Mapper().toDb(item);
    }

    public static BucketMinute60 getBucketMinute60() {
        String symbol = getSymbolRandom();
        return getBucketMinute60(symbol);
    }

    public static BucketMinute60 getBucketMinute60(
            String thisSymbol
    ) {
        BucketMinute60 item = new BucketMinute60();
        item.setSymbol(thisSymbol != null ? thisSymbol : getSymbolRandom());
        item.setCurrent(randomBigDecimal());
        item.setOpen(randomBigDecimal());
        item.setHigh(randomBigDecimal());
        item.setLow(randomBigDecimal());
        item.setPreviousClose(randomBigDecimal());
        item.setChanged(randomBigDecimal());
        item.setChangedPercent(randomBigDecimal());
        item.setMacd(randomBigDecimal());
        item.setStochk(randomBigDecimal());
        item.setActive(ActiveEnum.ACTIVE.value);
        item.setCreatedAt(LocalDateTime.now());
        item.setModifiedAt(null);
        item.setDeletedAt(null);
        return item;
    }

    // //////////////////////////////////////////////////////////////////
    // Bucket 15
    // //////////////////////////////////////////////////////////////////
    public static BucketMinute15 getBucketMinute15() {
        String symbol = getSymbolRandom();
        return getBucketMinute15(symbol);
    }

    public static BucketMinute15 getBucketMinute15(
            String thisSymbol
    ) {
        BucketMinute15 item = new BucketMinute15();
        item.setSymbol(thisSymbol != null ? thisSymbol : getSymbolRandom());
        item.setCurrent(randomBigDecimal());
        item.setOpen(randomBigDecimal());
        item.setHigh(randomBigDecimal());
        item.setLow(randomBigDecimal());
        item.setPreviousClose(randomBigDecimal());
        item.setChanged(randomBigDecimal());
        item.setChangedPercent(randomBigDecimal());
        item.setMacd(randomBigDecimal());
        item.setStochk(randomBigDecimal());
        item.setActive(ActiveEnum.ACTIVE.value);
        item.setCreatedAt(LocalDateTime.now());
        item.setModifiedAt(null);
        item.setDeletedAt(null);
        return item;
    }

    // //////////////////////////////////////////////////////////////////
    // Bucket 05
    // //////////////////////////////////////////////////////////////////
    public static BucketMinute05 getBucketMinute05() {
        String symbol = getSymbolRandom();
        return getBucketMinute05(symbol);
    }

    public static BucketMinute05 getBucketMinute05(
            String thisSymbol
    ) {
        BucketMinute05 item = new BucketMinute05();
        item.setSymbol(thisSymbol != null ? thisSymbol : getSymbolRandom());
        item.setCurrent(randomBigDecimal());
        item.setOpen(randomBigDecimal());
        item.setHigh(randomBigDecimal());
        item.setLow(randomBigDecimal());
        item.setPreviousClose(randomBigDecimal());
        item.setChanged(randomBigDecimal());
        item.setChangedPercent(randomBigDecimal());
        item.setMacd(randomBigDecimal());
        item.setStochk(randomBigDecimal());
        item.setActive(ActiveEnum.ACTIVE.value);
        item.setCreatedAt(LocalDateTime.now());
        item.setModifiedAt(null);
        item.setDeletedAt(null);
        return item;
    }

    // //////////////////////////////////////////////////////////////////
    // Bucket 01
    // //////////////////////////////////////////////////////////////////
    public static BucketMinute01 getBucketMinute01() {
        String symbol = getSymbolRandom();
        return getBucketMinute01(symbol);
    }

    public static BucketMinute01 getBucketMinute01(
            String thisSymbol
    ) {
        BucketMinute01 item = new BucketMinute01();
        item.setSymbol(thisSymbol != null ? thisSymbol : getSymbolRandom());
        item.setCurrent(randomBigDecimal());
        item.setOpen(randomBigDecimal());
        item.setHigh(randomBigDecimal());
        item.setLow(randomBigDecimal());
        item.setPreviousClose(randomBigDecimal());
        item.setChanged(randomBigDecimal());
        item.setChangedPercent(randomBigDecimal());
        item.setMacd(randomBigDecimal());
        item.setStochk(randomBigDecimal());
        item.setActive(ActiveEnum.ACTIVE.value);
        item.setCreatedAt(LocalDateTime.now());
        item.setModifiedAt(null);
        item.setDeletedAt(null);
        return item;
    }

    // //////////////////////////////////////////////////////////////////
    public static BucketDaily getBucketDaily() {
        String symbol = getSymbolRandom();
        return getBucketDaily(symbol);
    }

    public static BucketDaily getBucketDaily(
            String thisSymbol
    ) {
        BucketDaily item = new BucketDaily();
        item.setSymbol(thisSymbol != null ? thisSymbol : getSymbolRandom());
        item.setCurrent(randomBigDecimal());
        item.setOpen(randomBigDecimal());
        item.setHigh(randomBigDecimal());
        item.setLow(randomBigDecimal());
        item.setPreviousClose(randomBigDecimal());
        item.setChanged(randomBigDecimal());
        item.setChangedPercent(randomBigDecimal());
        item.setMacd(randomBigDecimal());
        item.setStochk(randomBigDecimal());
        item.setActive(ActiveEnum.ACTIVE.value);
        item.setCreatedAt(LocalDateTime.now());
        item.setModifiedAt(null);
        item.setDeletedAt(null);
        return item;
    }


    // //////////////////////////////////////////////////////////////////
    public static Scenario getScenario() {
        return getScenario(null, null);
    }

    public static Scenario getScenario(String name) {
        return getScenario(name, null);
    }

    public static Scenario getScenario(
            String thisName,
            String thisDescription
    ) {
        Scenario item = new Scenario();
        item.setExtid(UUID.randomUUID().toString());
        item.setName(thisName != null ? thisName : getNameRandom());
        item.setDescription(thisDescription != null ? thisDescription : getDescriptionRandom());
        item.setActive(ActiveEnum.ACTIVE.value);
        item.setCreatedAt(LocalDateTime.now());
        item.setModifiedAt(null);
        item.setDeletedAt(null);
        return item;
    }

    // //////////////////////////////////////////////////////////////////
    public static Stock getStock() {
        return getStock(null, null);
    }

    public static Stock getStock(String name) {
        return getStock(name, null);
    }

    public static Stock getStock(
            String thisName,
            String thisSymbol
    ) {
        Stock item = new Stock();
        item.setExtid(UUID.randomUUID().toString());
        item.setName(thisName != null ? thisName : getNameRandom());
        item.setSymbol(thisSymbol != null ? thisSymbol : getSymbolRandom());
        item.setReady(1);
        item.setActive(ActiveEnum.ACTIVE.value);
        item.setCreatedAt(LocalDateTime.now());
        item.setModifiedAt(null);
        item.setDeletedAt(null);
        return item;
    }

    // //////////////////////////////////////////////////////////////////
    public static StockDaily getStockDaily() {
        return getStockDaily(null);
    }

    public static StockDaily getStockDaily(
            String thisSymbol
    ) {
        StockDaily item = new StockDaily();
        item.setSymbol(thisSymbol != null ? thisSymbol : getSymbolRandom());
        item.setCurrent(randomBigDecimal());
        item.setOpen(randomBigDecimal());
        item.setHigh(randomBigDecimal());
        item.setLow(randomBigDecimal());
        item.setPreviousClose(randomBigDecimal());
        item.setChanged(randomBigDecimal());
        item.setChangedPercent(randomBigDecimal());
        item.setMacd(randomBigDecimal());
        item.setStochk(randomBigDecimal());
        item.setActive(ActiveEnum.ACTIVE.value);
        item.setCreatedAt(LocalDateTime.now());
        item.setModifiedAt(null);
        item.setDeletedAt(null);

        return item;
    }

    // //////////////////////////////////////////////////////////////////
    public static StockData getStockData() {
        StockData item = new StockData();
        item.setCurrent(randomBigDecimal());
        item.setHigh(randomBigDecimal());
        item.setLow(randomBigDecimal());
        item.setOpen(randomBigDecimal());
        item.setPreviousClose(randomBigDecimal());
        item.setChanged(randomBigDecimal());
        item.setChangedPercent(randomBigDecimal());
        item.setMacd(randomBigDecimal());
        item.setStochk(randomBigDecimal());

        return item;
    }

    public static Quote getQuote() {
        Quote item = new Quote();
        item.setC(randomFloat());  //current
        item.setO(randomFloat());  //open
        item.setL(randomFloat());  //low
        item.setH(randomFloat());  //high
        item.setPc(randomFloat()); //previousClose
        item.setD(randomFloat());  //change
        item.setDp(randomFloat()); //change percent

        return item;
    }

    // //////////////////////////////////////////////////////////////////
    public static BigDecimal getBigDecimal(String value) {
        return new BigDecimal(value);
    }

    public static String getDescriptionRandom() {
        return DESCRIPTION + randomString();
    }

    public static String getDescriptionRandom(String random) {
        return DESCRIPTION + random;
    }

    public static String getRandom() {
        return randomString();
    }

    public static String getNameRandom() {
        return NAME + randomString();
    }

    public static String getSymbolRandom() {
        return RandomStringUtils.random(7, true, false);
    }

    public static String getNameRandom(String random) {
        return NAME + random;
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

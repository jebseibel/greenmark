package com.greenmark.database;

import com.greenmark.common.database.domain.Account;
import com.greenmark.common.database.domain.MarketData;
import com.greenmark.common.database.domain.Position;
import com.greenmark.common.enums.ActiveEnum;
import com.greenmark.common.enums.TimeframeType;
import com.greenmark.database.db.entity.*;
import com.greenmark.database.db.mapper.AccountMapper;
import com.greenmark.database.db.mapper.PositionMapper;
import com.greenmark.datafeed.finnhub.models.Quote;
import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

public class DomainBuilderDatabase {
    public static String NAME = "Name_";
    public static String DESCRIPTION = "Desc_";

    // ///////////////////////////////////////////////////////////////////
    public static Account getAccountDb(AccountDb item) {
        return new AccountMapper().toEntity(item);
    }

    public static AccountDb getAccount() {
        return getAccount(null, null, null);
    }

    public static AccountDb getAccount(String name) {
        return getAccount(name, null, null);
    }

    public static AccountDb getAccount(
            String thisName,
            String thisDescription,
            String thisExtId
    ) {
        String random = getRandom();
        AccountDb item = new AccountDb();
        item.setExtid(thisExtId != null ? thisExtId : UUID.randomUUID().toString());
        item.setName(thisName != null ? thisName : getNameRandom(random));
        item.setDescription(thisDescription != null ? thisDescription : getDescriptionRandom(random));
        item.setActive(ActiveEnum.ACTIVE.value);
        item.setCreatedAt(LocalDateTime.now());
        item.setModifiedAt(null);
        item.setDeletedAt(null);
        return item;
    }

    // //////////////////////////////////////////////////////////////////
    public static Position getPositionDb() {
        PositionDb item = getPosition(null, null);
        return new PositionMapper().toEntity(item);
    }

    public static PositionDb getPosition() {
        return getPosition(null, null);
    }

    public static PositionDb getPosition(String name) {
        return getPosition(name, null);
    }

    public static PositionDb getPosition(
            String thisName,
            String thisSymbol
    ) {
        String random = getRandom();
        BigDecimal price = randomBigDecimal();
        int shares = 10;
        //create
        BigDecimal total = price.multiply(new BigDecimal(shares));
        PositionDb item = new PositionDb();
        item.setExtid(UUID.randomUUID().toString());
        item.setSymbol(thisSymbol != null ? thisSymbol : getSymbolRandom());
        item.setName(thisName != null ? thisName : getNameRandom(random));
        item.setShares(shares);
        item.setPrice(price);
        item.setTotal(total);
        item.setActive(ActiveEnum.ACTIVE.value);
        item.setCreatedAt(LocalDateTime.now());
        item.setModifiedAt(null);
        item.setDeletedAt(null);
        return item;
    }


    public static StockWatchDb getStockWatchMinute01() {
        String symbol = getSymbolRandom();
        return getStockWatch(symbol, TimeframeType.MINUTE01);
    }

    public static StockWatchDb getStockWatchMinute05() {
        String symbol = getSymbolRandom();
        return getStockWatch(symbol, TimeframeType.MINUTE05);
    }

    public static StockWatchDb getStockWatchMinute15() {
        String symbol = getSymbolRandom();
        return getStockWatch(symbol, TimeframeType.MINUTE15);
    }

    public static StockWatchDb getStockWatchMinute60() {
        String symbol = getSymbolRandom();
        return getStockWatch(symbol, TimeframeType.MINUTE60);
    }

    public static StockWatchDb getStockWatchDaily() {
        String symbol = getSymbolRandom();
        return getStockWatch(symbol, TimeframeType.DAILY);
    }

    // //////////////////////////////////////////////////////////////////
    // //////////////////////////////////////////////////////////////////
    public static StockWatchDb getStockWatch(
            String thisSymbol,
            TimeframeType timeframeType
    ) {
        StockWatchDb item = new StockWatchDb();
        item.setSymbol(thisSymbol != null ? thisSymbol : getSymbolRandom());
        item.setTimeframe(timeframeType.value);
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
    public static ScenarioDb getScenario() {
        return getScenario(null, null);
    }

    public static ScenarioDb getScenario(String name) {
        return getScenario(name, null);
    }

    public static ScenarioDb getScenario(
            String thisName,
            String thisDescription
    ) {
        ScenarioDb item = new ScenarioDb();
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
    public static StockDb getStock() {
        return getStock(null, null);
    }

    public static StockDb getStock(String name) {
        return getStock(name, null);
    }

    public static StockDb getStock(
            String thisName,
            String thisSymbol
    ) {
        StockDb item = new StockDb();
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
    public static StockDailyDb getStockDaily() {
        return getStockDaily(null);
    }

    public static StockDailyDb getStockDaily(
            String thisSymbol
    ) {
        StockDailyDb item = new StockDailyDb();
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

    public static StockWatchDb getStockWatch() {
        String symbol = getSymbolRandom();
        return getStockWatch(symbol);
    }

    public static StockWatchDb getStockWatch(
            String thisSymbol
    ) {
        StockWatchDb item = new StockWatchDb();
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
    public static MarketData getMarketData() {
        MarketData item = new MarketData();
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
        return RandomStringUtils.random(4, true, false).toUpperCase();
    }

    public static String getNameRandom(String random) {
        return NAME + random;
    }

    public static final String getUUID() {
        return UUID.randomUUID().toString();
    }

    public static final String getStringTestUUIDTooLong() {
        return "123456789012345678901234567890123456789012345678901234567890";
    }

    public static String randomString() {
        return String.valueOf(new Random().nextInt());
    }

    public static BigDecimal randomBigDecimal() {
        int random = randomPositiveInt(100);
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

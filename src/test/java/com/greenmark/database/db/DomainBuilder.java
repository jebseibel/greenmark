package com.greenmark.database.db;

import com.greenmark.common.database.domain.*;
import com.greenmark.common.enums.ActiveEnum;
import com.greenmark.database.db.entity.*;
import com.greenmark.database.db.mapper.*;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

public class DomainBuilder
{
    public static String NAME = "Name_";
    public static String DESCRIPTION = "Desc_";

    // ///////////////////////////////////////////////////////////////////
    public static AccountDb getAccountDb() {
        Account item = getAccount(null, null);
        return AccountMapper.toDb(item);
    }
    public static Account getAccount() {
        return getAccount(null, null);
    }
    public static Account getAccount(String name) {
        return getAccount(name, null);
    }
    public static Account getAccount(
            String thisName,
            String thisDescription
    ) {

        String random = getNameRandom();
        Account item = new Account();
        item.setExtid(UUID.randomUUID().toString());
        item.setName( thisName != null ? thisName : getNameRandom(random));
        item.setDescription( thisDescription != null ? thisDescription : getDescriptionRandom(random));
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
    public static BucketDb getBucketDb() {
        Bucket item = getBucket(null, null);
        return BucketMapper.toDb(item);
    }    
    public static Bucket getBucket() {
        return getBucket(null, null);
    }
    public static Bucket getBucket(String name) {
        return getBucket(name, null);
    }
    public static Bucket getBucket(
            String thisName,
            String thisDescription
    ) {
        Bucket item = new Bucket();
        item.setExtid(UUID.randomUUID().toString());
        item.setName( thisName != null ? thisName : getNameRandom());
        item.setDescription( thisDescription != null ? thisDescription : getDescriptionRandom());
        item.setActive(ActiveEnum.ACTIVE.value);
        item.setCreatedAt(LocalDateTime.now());
        item.setModifiedAt(null);
        item.setDeletedAt(null);
        return item;
    }

    // //////////////////////////////////////////////////////////////////
    public static FollowDb getFollowDb() {
        Follow item = getFollow(null, null);
        return FollowMapper.toDb(item);
    }
    public static Follow getFollow() { return getFollow(null, null); }
    public static Follow getFollow(String name) { return getFollow(name, null);  }
    public static Follow getFollow(
            String thisName,
            String thisDescription
    ) {
        Follow item = new Follow();
        item.setExtid(UUID.randomUUID().toString());
        item.setName( thisName != null ? thisName : getNameRandom());
        item.setDescription( thisDescription != null ? thisDescription : getDescriptionRandom());
        item.setActive(ActiveEnum.ACTIVE.value);
        item.setCreatedAt(LocalDateTime.now());
        item.setModifiedAt(null);
        item.setDeletedAt(null);
        return item;
    }

    // //////////////////////////////////////////////////////////////////
    public static ScenarioDb getScenarioDb() {
        Scenario item = getScenario(null, null);
        return ScenarioMapper.toDb(item);
    }
    public static Scenario getScenario() { return getScenario(null, null); }
    public static Scenario getScenario(String name) { return getScenario(name, null);  }
    public static Scenario getScenario(
            String thisName,
            String thisDescription
    ) {
        Scenario item = new Scenario();
        item.setExtid(UUID.randomUUID().toString());
        item.setName( thisName != null ? thisName : getNameRandom());
        item.setDescription( thisDescription != null ? thisDescription : getDescriptionRandom());
        item.setActive(ActiveEnum.ACTIVE.value);
        item.setCreatedAt(LocalDateTime.now());
        item.setModifiedAt(null);
        item.setDeletedAt(null);
        return item;
    }

    // //////////////////////////////////////////////////////////////////
    public static StockDb getStockDb() {
        Stock item = getStock(null, null);
        return StockMapper.toDb(item);
    }
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
        item.setName( thisName != null ? thisName : getNameRandom());
        item.setSymbol( thisSymbol != null ? thisSymbol : getSymbolRandom());
        item.setReady(1);
        item.setActive(ActiveEnum.ACTIVE.value);
        item.setCreatedAt(LocalDateTime.now());
        item.setModifiedAt(null);
        item.setDeletedAt(null);
        return item;
    }

    // //////////////////////////////////////////////////////////////////
    public static StockDailyDb getStockNightlyDb() {
        StockDaily item = getStockNightly(null);
        return StockDailyMapper.toDb(item);
    }

    public static StockDaily getStockNightly() {
        return getStockNightly(null);
    }

    public static StockDaily getStockNightly(
            String thisSymbol
    ) {
        StockDaily item = new StockDaily();
        item.setSymbol( thisSymbol != null ? thisSymbol : getSymbolRandom());
        item.setOpen(randomPositiveFloat());
        item.setClose(randomPositiveFloat());
        item.setHigh(randomPositiveFloat());
        item.setLow(randomPositiveFloat());
        item.setVolume(randomPositiveLong());
        item.setMacd(randomPositiveFloat());
        item.setStochk(randomPositiveFloat());
        item.setActive(ActiveEnum.ACTIVE.value);
        item.setCreatedAt(LocalDateTime.now());
        item.setModifiedAt(null);

        return item;
    }

    // //////////////////////////////////////////////////////////////////
    public static String getDescriptionRandom() {
        return DESCRIPTION + randomPositiveString();
    }

    public static String getDescriptionRandom(String random) {
        return DESCRIPTION + random;
    }

    public static String getNameRandom() {
        return NAME + randomPositiveString();
    }
    public static String getSymbolRandom() {
        return RandomStringUtils.random(7, true, false);
    }

    public static String getNameRandom(String random) {
        return NAME + random;
    }

    public static final String getUUID () {
        return UUID.randomUUID().toString();
    }

    public static final String getStringTestUUID() {
        return "123456789012345678901234567890123456789012345678901234567890";
    }

    public static String randomPositiveString()  {
        return String.valueOf(new Random().nextInt());
    }

    public static float randomPositiveFloat()  {
        return new Random().nextFloat();
    }

    public static long randomPositiveLong()  { return new Random().nextLong(); }

    public static int randomPositiveInt()  {
        return new Random().nextInt();
    }

    public static int randomPositiveInt(int maxValue)  {
        return new Random().nextInt(maxValue);
    }

}

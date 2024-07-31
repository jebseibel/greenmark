package com.greenmark.database.db;

import com.greenmark.common.database.domain.*;
import com.greenmark.common.enums.ActiveEnum;
import com.greenmark.database.db.entity.*;
import com.greenmark.database.db.mapper.*;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

public class DomainBuilder
{
    public static String NAME = "Name_";
    public static String DESCRIPTION = "Desc_";

    // ///////////////////////////////////////////////////////////////////
    public static AccountDb getAccountDb() {
        AccountEntity item = getAccount(null, null);
        return AccountMapper.toDb(item);
    }
    public static AccountEntity getAccount() {
        return getAccount(null, null);
    }
    public static AccountEntity getAccount(String name) {
        return getAccount(name, null);
    }
    public static AccountEntity getAccount(
            String thisName,
            String thisDescription
    ) {

        String random = getNameRandom();
        AccountEntity item = new AccountEntity();
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
        BucketEntity item = getBucket(null, null);
        return BucketMapper.toDb(item);
    }    
    public static BucketEntity getBucket() {
        return getBucket(null, null);
    }
    public static BucketEntity getBucket(String name) {
        return getBucket(name, null);
    }
    public static BucketEntity getBucket(
            String thisName,
            String thisDescription
    ) {
        BucketEntity item = new BucketEntity();
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
        FollowEntity item = getFollow(null, null);
        return FollowMapper.toDb(item);
    }
    public static FollowEntity getFollow() { return getFollow(null, null); }
    public static FollowEntity getFollow(String name) { return getFollow(name, null);  }
    public static FollowEntity getFollow(
            String thisName,
            String thisDescription
    ) {
        FollowEntity item = new FollowEntity();
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
        ScenarioEntity item = getScenario(null, null);
        return ScenarioMapper.toDb(item);
    }
    public static ScenarioEntity getScenario() { return getScenario(null, null); }
    public static ScenarioEntity getScenario(String name) { return getScenario(name, null);  }
    public static ScenarioEntity getScenario(
            String thisName,
            String thisDescription
    ) {
        ScenarioEntity item = new ScenarioEntity();
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
        StockEntity item = getStock(null, null);
        return StockMapper.toDb(item);
    }
    public static StockEntity getStock() {
        return getStock(null, null);
    }
    public static StockEntity getStock(String name) {
        return getStock(name, null);
    }
    public static StockEntity getStock(
            String thisName,
            String thisDescription
    ) {
        StockEntity item = new StockEntity();
        item.setExtid(UUID.randomUUID().toString());
        item.setName( thisName != null ? thisName : getNameRandom());
        item.setDescription( thisDescription != null ? thisDescription : getDescriptionRandom());
//        item.setSymbol( thisSymbol != null ? thisSymbol : "Symbol_" + randomPositiveString());
//        item.setIgnore( 1);
        item.setActive(ActiveEnum.ACTIVE.value);
        item.setCreatedAt(LocalDateTime.now());
        item.setModifiedAt(null);
        item.setDeletedAt(null);
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

    public static int randomPositiveInt()  {
        return new Random().nextInt();
    }

    public static int randomPositiveInt(int maxValue)  {
        return new Random().nextInt(maxValue);
    }

}

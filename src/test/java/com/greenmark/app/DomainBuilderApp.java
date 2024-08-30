package com.greenmark.app;

import com.greenmark.app.buckets.BucketData;
import com.greenmark.app.buckets.BucketDataContainer;
import com.greenmark.common.database.domain.StockWatchDb;
import com.greenmark.common.enums.TimeframeType;
import com.greenmark.database.DomainBuilderDatabase;
import com.greenmark.datafeed.finnhub.models.Quote;
import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class DomainBuilderApp {
    public static String NAME = "Name_";
    public static String DESCRIPTION = "Desc_";

    // ///////////////////////////////////////////////////////////////////
    public static StockWatchDb getStockWatchDb() {
        String symbol = getSymbolRandom();
        return new StockWatchDb(symbol);
    }

    public static BucketData getBucketData(String name, int order, TimeframeType timeframeType, int swNumber) {
        BucketData bucketData  = getBucketData(name, order, timeframeType);
        for(int i = 1; i <= swNumber; i++) {
            StockWatchDb stockWatch = getStockWatchDb();
            bucketData.getStockWatchDbList().add(stockWatch);
        }
        return bucketData;
    }

    public static BucketData getBucketData(String name, int order, TimeframeType timeframeType) {

        BucketData bucketData = new BucketData();
        bucketData.setName(name);
        bucketData.setOrder(order);
        bucketData.setTimeframe(timeframeType);
        bucketData.setDemote(DomainBuilderDatabase.randomBigDecimal());
        bucketData.setPromote(DomainBuilderDatabase.randomBigDecimal());
        return bucketData;
    }

    public static BucketDataContainer getBucketDataContainer() {
        BucketData bucket01 = DomainBuilderApp.getBucketData("minute01",1, TimeframeType.MINUTE01);
        BucketData bucket05 = DomainBuilderApp.getBucketData("minute05",2, TimeframeType.MINUTE05);
        BucketData bucket15 = DomainBuilderApp.getBucketData("minute15", 3, TimeframeType.MINUTE15);
        BucketData bucket60 = DomainBuilderApp.getBucketData("minute60",4, TimeframeType.MINUTE60);
        BucketData bucketDD = DomainBuilderApp.getBucketData("daily",5, TimeframeType.DAILY);
        List<BucketData> list = List.of(bucket01, bucket05, bucket15, bucket60, bucketDD);
        BucketDataContainer bucketDataContainer = new BucketDataContainer();
        bucketDataContainer.setBucketDataList(list);
        return bucketDataContainer;
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

    public static final String getStringTestUUIDTooLong() {
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

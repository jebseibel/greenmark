package com.greenmark.app;

import com.greenmark.app.buckets.*;
import com.greenmark.common.database.domain.*;
import com.greenmark.database.service.DatabaseBucketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class StartupService {

    private BucketAllData bucketAllData;
    private DatabaseBucketService databaseBucketService;
    
    public StartupService(
            BucketAllData bucketAllData,
            DatabaseBucketService databaseBucketService
    ) {
        this.bucketAllData = bucketAllData;
        this.databaseBucketService = databaseBucketService;
    }

    public void loadAll() {
        log.info("Loading all buckets");
        loadMinute01();
        loadMinute05();
        loadMinute15();
        loadMinute60();
        loadMinuteDD();
        log.info("Done loading buckets");
    }

    public int loadMinute01() {
        List<BucketMinute01Db> items = databaseBucketService.getAllMinute01();
        Bucket01Data data = bucketAllData.getBucket01Data();
        data.setData(items);
        return items.size();
    }

    public int loadMinute05() {
        List<BucketMinute05Db> items = databaseBucketService.getAllMinute05();
        Bucket05Data data = bucketAllData.getBucket05Data();
        data.setData(items);
        return items.size();

    }

    public int loadMinute15() {
        List<BucketMinute15Db> items = databaseBucketService.getAllMinute15();
        Bucket15Data data = bucketAllData.getBucket15Data();
        data.setData(items);
        return items.size();

    }

    public int loadMinute60() {
        List<BucketMinute60Db> items = databaseBucketService.getAllMinute60();
        Bucket60Data data = bucketAllData.getBucket60Data();
        data.setData(items);
        return items.size();
    }

    public int loadMinuteDD() {
        List<BucketDailyDb> items = databaseBucketService.getAllDaily();
        BucketDDData data = bucketAllData.getBucketDailyData();
        data.setData(items);
        return items.size();
    }

    public BucketAllData getBucketAllData() {
        return bucketAllData;
    }
}

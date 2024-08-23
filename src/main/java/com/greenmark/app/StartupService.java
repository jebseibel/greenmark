package com.greenmark.app;

import com.greenmark.app.buckets.BucketData;
import com.greenmark.common.database.domain.StockWatchDb;
import com.greenmark.database.service.DatabaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class StartupService {

    private BucketAllData bucketAllData;
    private DatabaseService databaseService;
    
    public StartupService(
            BucketAllData bucketAllData,
            DatabaseService databaseService
    ) {
        this.bucketAllData = bucketAllData;
        this.databaseService = databaseService;
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
        List<StockWatchDb> items = databaseService.getAllMinute01();
        BucketData data = bucketAllData.getBucket01Data();
        data.setData(items);
        return items.size();
    }

    public int loadMinute05() {
        List<StockWatchDb> items = databaseService.getAllMinute05();
        BucketData data = bucketAllData.getBucket05Data();
        data.setData(items);
        return items.size();

    }

    public int loadMinute15() {
        List<StockWatchDb> items = databaseService.getAllMinute15();
        BucketData data = bucketAllData.getBucket15Data();
        data.setData(items);
        return items.size();

    }

    public int loadMinute60() {
        List<StockWatchDb> items = databaseService.getAllMinute60();
        BucketData data = bucketAllData.getBucket60Data();
        data.setData(items);
        return items.size();
    }

    public int loadMinuteDD() {
        List<StockWatchDb> items = databaseService.getAllDaily();
        BucketData data = bucketAllData.getBucketDailyData();
        data.setData(items);
        return items.size();
    }

    public BucketAllData getBucketAllData() {
        return bucketAllData;
    }
}

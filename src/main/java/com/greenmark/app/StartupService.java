package com.greenmark.app;

import com.greenmark.app.buckets.BucketDataContainer;
import com.greenmark.database.service.DatabaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StartupService {

    private BucketDataContainer bucketDataContainer;
    private DatabaseService databaseService;
    
    public StartupService(
            BucketDataContainer bucketDataContainer,
            DatabaseService databaseService
    ) {
        this.bucketDataContainer = bucketDataContainer;
        this.databaseService = databaseService;
    }
//
//    public void loadAll() {
//        log.info("Loading all buckets");
//        loadMinute01();
//        loadMinute05();
//        loadMinute15();
//        loadMinute60();
//        loadMinuteDD();
//        log.info("Done loading buckets");
//    }
//
//    public int loadMinute01() {
//        List<StockWatch> items = databaseService.getAllMinute01();
//        BucketData data = bucketDataContainer.getBucket01Data();
//        data.setData(items);
//        return items.size();
//    }
//
//    public int loadMinute05() {
//        List<StockWatch> items = databaseService.getAllMinute05();
//        BucketData data = bucketDataContainer.getBucket05Data();
//        data.setData(items);
//        return items.size();
//
//    }
//
//    public int loadMinute15() {
//        List<StockWatch> items = databaseService.getAllMinute15();
//        BucketData data = bucketDataContainer.getBucket15Data();
//        data.setData(items);
//        return items.size();
//
//    }
//
//    public int loadMinute60() {
//        List<StockWatch> items = databaseService.getAllMinute60();
//        BucketData data = bucketDataContainer.getBucket60Data();
//        data.setData(items);
//        return items.size();
//    }
//
//    public int loadMinuteDD() {
//        List<StockWatch> items = databaseService.getAllDaily();
//        BucketData data = bucketDataContainer.getBucketDailyData();
//        data.setData(items);
//        return items.size();
//    }

    public BucketDataContainer getBucketAllData() {
        return bucketDataContainer;
    }
}

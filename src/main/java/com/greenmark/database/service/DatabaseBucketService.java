package com.greenmark.database.service;

import com.greenmark.common.database.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DatabaseBucketService {

    private BucketDailyDbService bucketDailyDbService;
    private BucketMinute60DbService bucketMinute60DbService;
    private BucketMinute15DbService bucketMinute15DbService;
    private BucketMinute05DbService bucketMinute05DbService;
    private BucketMinute01DbService bucketMinute01DbService;

    public DatabaseBucketService(
            BucketDailyDbService bucketDailyDbService,
            BucketMinute60DbService bucketMinute60DbService,
            BucketMinute15DbService bucketMinute15DbService,
            BucketMinute05DbService bucketMinute05DbService,
            BucketMinute01DbService bucketMinute01DbService
    ) {
        this.bucketDailyDbService = bucketDailyDbService;
        this.bucketMinute60DbService = bucketMinute60DbService;
        this.bucketMinute15DbService = bucketMinute15DbService;
        this.bucketMinute05DbService = bucketMinute05DbService;
        this.bucketMinute01DbService = bucketMinute01DbService;
    }

    public List<BucketMinute01Db> getAllMinute01 () {
        return bucketMinute01DbService.findAll();
    }

    public List<BucketMinute05Db> getAllMinute05 () {
        return bucketMinute05DbService.findAll();
    }

    public List<BucketMinute15Db> getAllMinute15 () {
        return bucketMinute15DbService.findAll();
    }

    public List<BucketMinute60Db> getAllMinute60 () {
        return bucketMinute60DbService.findAll();
    }

    public List<BucketDailyDb> getAllDaily () {
        return bucketDailyDbService.findAll();
    }
}

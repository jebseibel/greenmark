package com.greenmark.app;

import com.greenmark.app.buckets.*;
import org.springframework.stereotype.Service;

@Service
public class BucketMainService {

    private final Bucket01Service bucket01Service;
    private final Bucket05Service bucket05Service;
    private final Bucket15Service bucket15Service;
    private final Bucket60Service bucket60Service;
    private final BucketDDService bucketDailyService;
    private final AllBucketService allBucketService;

    public BucketMainService(
            Bucket01Service bucket01Service,
            Bucket05Service bucket05Service,
            Bucket15Service bucket15Service,
            Bucket60Service bucket60Service,
            BucketDDService bucketDailyService,
            AllBucketService allBucketService
    ) {
        this.bucket01Service = bucket01Service;
        this.bucket05Service = bucket05Service;
        this.bucket15Service = bucket15Service;
        this.bucket60Service = bucket60Service;
        this.bucketDailyService = bucketDailyService;
        this.allBucketService = allBucketService;
    }

    public boolean isReady() {
        return allBucketService.size() == 0;
    }
}

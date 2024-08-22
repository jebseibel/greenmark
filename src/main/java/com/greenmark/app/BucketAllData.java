package com.greenmark.app;

import com.greenmark.app.buckets.*;
import org.springframework.stereotype.Component;

@Component
public class BucketAllData {

    private Bucket01Data bucket01Data;
    private Bucket05Data bucket05Data;
    private Bucket15Data bucket15Data;
    private Bucket60Data bucket60Data;
    private BucketDDData bucketDailyData;
    private BucketMarketData bucketMarketData;

    public BucketAllData(
            Bucket01Data bucket01Data,
            Bucket05Data bucket05Data,
            Bucket15Data bucket15Data,
            Bucket60Data bucket60Data,
            BucketDDData bucketDailyData,
            BucketMarketData bucketMarketData
    ) {
        this.bucket01Data = bucket01Data;
        this.bucket05Data = bucket05Data;
        this.bucket15Data = bucket15Data;
        this.bucket60Data = bucket60Data;
        this.bucketDailyData = bucketDailyData;
        this.bucketMarketData = bucketMarketData;
    }

    public Bucket01Data getBucket01Data() {
        return bucket01Data;
    }

    public Bucket05Data getBucket05Data() {
        return bucket05Data;
    }

    public Bucket15Data getBucket15Data() {
        return bucket15Data;
    }

    public Bucket60Data getBucket60Data() {
        return bucket60Data;
    }

    public BucketDDData getBucketDailyData() {
        return bucketDailyData;
    }

    public BucketMarketData getAllBucketData() {
        return bucketMarketData;
    }
}

package com.greenmark.app;

import com.greenmark.app.buckets.BucketData;
import com.greenmark.app.buckets.BucketMarketData;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class BucketAllData {

    private BucketData bucket01Data;
    private BucketData bucket05Data;
    private BucketData bucket15Data;
    private BucketData bucket60Data;
    private BucketData bucketDailyData;
    private BucketMarketData bucketMarketData;

    public BucketAllData(
            BucketData bucket01Data,
            BucketData bucket05Data,
            BucketData bucket15Data,
            BucketData bucket60Data,
            BucketData bucketDailyData,
            BucketMarketData bucketMarketData
    ) {
        this.bucket01Data = bucket01Data;
        this.bucket05Data = bucket05Data;
        this.bucket15Data = bucket15Data;
        this.bucket60Data = bucket60Data;
        this.bucketDailyData = bucketDailyData;
        this.bucketMarketData = bucketMarketData;
    }
}

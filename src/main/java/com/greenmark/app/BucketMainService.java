package com.greenmark.app;

import com.greenmark.app.buckets.*;
import org.springframework.stereotype.Service;

@Service
public class BucketMainService {

    public BucketMainService(
            Bucket01Service bucket01Service,
            Bucket05Service bucket05Service,
            Bucket15Service bucket15Service,
            Bucket60Service bucket60Service,
            BucketDDService bucketDailyService
    ) {
    }

    public boolean initialize() {
        return true;
    }
}

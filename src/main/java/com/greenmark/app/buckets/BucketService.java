package com.greenmark.app.buckets;

import com.greenmark.common.database.domain.BucketData;

import java.util.List;

public interface BucketService {
    List<BucketData> getAllPromote();
    List<BucketData> getAllDemote();
}

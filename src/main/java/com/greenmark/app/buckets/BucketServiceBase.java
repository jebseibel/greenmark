package com.greenmark.app.buckets;

import com.greenmark.common.database.domain.BucketData;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Data
public class BucketServiceBase {

    List<BucketData> list = new ArrayList<>();
    BigDecimal promote;
    BigDecimal demote;

    public BucketServiceBase() {

    }

    /////////////////////////////////////////////////////////////
    // Methods
    /////////////////////////////////////////////////////////////
    public int size() {
        return list.size();
    }

    public void addItem(BucketData bucketMinute60) {
        if (!list.contains(bucketMinute60))
            list.add(bucketMinute60);
    }

    public void removeItem(BucketData bucketMinute60) {
        list.remove(bucketMinute60);
    }

    public List<BucketData> getBucketData() {
        return list;
    }
}

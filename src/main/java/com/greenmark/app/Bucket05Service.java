package com.greenmark.app;

import com.greenmark.common.database.domain.BucketMinute15Db;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Data
public class Bucket05Service {

    List<BucketMinute15Db> list;
    BigDecimal promote;
    BigDecimal demote;

    public Bucket05Service(BigDecimal promote, BigDecimal demote) {
        this.list = new ArrayList<>();
        this.promote = promote;
        this.demote = demote;
    }

    /////////////////////////////////////////////////////////////
    // Methods
    /////////////////////////////////////////////////////////////
    public int size() {
        return list.size();
    }

    public void addItem(BucketMinute15Db bucketMinute60) {
        if(! list.contains(bucketMinute60))
            list.add(bucketMinute60);
    }

    public void removeItem(BucketMinute15Db bucketMinute60) {
        list.remove(bucketMinute60);
    }

    public List<BucketMinute15Db> getBucketMinute15Db() {
        return list;
    }

    /////////////////////////////////////////////////////////////////////////////////
    public List<BucketMinute15Db> getAllPromote() {
        return list.stream()
                .filter(item -> item.getMacd().compareTo(promote) > 0)
                .collect(Collectors.toList());
    }

    /////////////////////////////////////////////////////////////////////////////////
    public List<BucketMinute15Db> getAllDemote() {
        return list.stream()
                .filter(item -> item.getMacd().compareTo(demote) < 0)
                .collect(Collectors.toList());
    }
}

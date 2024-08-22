package com.greenmark.app.buckets;

import com.greenmark.common.database.domain.BucketMinute15Db;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class Bucket15Data {

    List<BucketMinute15Db> list = new ArrayList<>();
    BigDecimal promote;
    BigDecimal demote;

    public Bucket15Data() {
    }

    public List<BucketMinute15Db> getAllPromote() {
        return list.stream()
                .filter(item -> item.getMacd().compareTo(promote) > 0)
                .collect(Collectors.toList());
    }

    public List<BucketMinute15Db> getAllDemote() {
        return list.stream()
                .filter(item -> item.getMacd().compareTo(demote) < 0)
                .collect(Collectors.toList());
    }

    public void setData(List<BucketMinute15Db> records) {
        list = records;
    }

    public List<BucketMinute15Db> getData()  {
        return list;
    }

    public void addItem(BucketMinute15Db item) {
        if (!list.contains(item))
            list.add(item);
    }

    public void removeItem(BucketMinute15Db item) {
        list.remove(item);
    }

    public int size() {
        return list.size();
    }
}

package com.greenmark.app.buckets;

import com.greenmark.common.database.domain.BucketDailyDb;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class BucketDDData {

    List<BucketDailyDb> list = new ArrayList<>();
    BigDecimal promote;
    BigDecimal demote;

    public BucketDDData() {
    }

    public List<BucketDailyDb> getAllPromote() {
        return list.stream()
                .filter(item -> item.getMacd().compareTo(promote) > 0)
                .collect(Collectors.toList());
    }

    public List<BucketDailyDb> getAllDemote() {
        return list.stream()
                .filter(item -> item.getMacd().compareTo(demote) < 0)
                .collect(Collectors.toList());
    }

    public void setData(List<BucketDailyDb> records) {
        list = records;
    }

    public List<BucketDailyDb> getData()  {
        return list;
    }

    public void addItem(BucketDailyDb item) {
        if (!list.contains(item))
            list.add(item);
    }

    public void removeItem(BucketDailyDb item) {
        list.remove(item);
    }

    public int size() {
        return list.size();
    }
}

package com.greenmark.app.buckets;

import com.greenmark.common.database.domain.BucketMinute01Db;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class Bucket01Data {

    List<BucketMinute01Db> list = new ArrayList<>();
    BigDecimal promote;
    BigDecimal demote;

    public Bucket01Data() {
    }

    public List<BucketMinute01Db> getAllPromote() {
        return list.stream()
                .filter(item -> item.getMacd().compareTo(promote) > 0)
                .collect(Collectors.toList());
    }

    public List<BucketMinute01Db> getAllDemote() {
        return list.stream()
                .filter(item -> item.getMacd().compareTo(demote) < 0)
                .collect(Collectors.toList());
    }

    public void setData(List<BucketMinute01Db> records) {
        list = records;
    }

    public List<BucketMinute01Db> getData()  {
        return list;
    }

    public void addItem(BucketMinute01Db item) {
        if (!list.contains(item))
            list.add(item);
    }

    public void removeItem(BucketMinute01Db item) {
        list.remove(item);
    }

    public int size() {
        return list.size();
    }
}

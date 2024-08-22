package com.greenmark.app.buckets;

import com.greenmark.common.database.domain.BucketMinute60Db;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@Data
public class Bucket60Data {

    List<BucketMinute60Db> list = new ArrayList<>();
    BigDecimal promote;
    BigDecimal demote;

    public Bucket60Data() {

    }

    public List<BucketMinute60Db> getAllPromote() {
        return list.stream()
                .filter(item -> item.getMacd().compareTo(promote) > 0)
                .collect(Collectors.toList());
    }

    public List<BucketMinute60Db> getAllDemote() {
        return list.stream()
                .filter(item -> item.getMacd().compareTo(demote) < 0)
                .collect(Collectors.toList());
    }

    public void setData(List<BucketMinute60Db> records) {
        list = records;
    }

    public List<BucketMinute60Db> getData()  {
        return list;
    }

    public void addItem(BucketMinute60Db item) {
        if (!list.contains(item))
            list.add(item);
    }

    public void removeItem(BucketMinute60Db item) {
        list.remove(item);
    }

    public int size() {
        return list.size();
    }
}

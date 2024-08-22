package com.greenmark.app.buckets;

import com.greenmark.common.database.domain.StockWatchDb;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class BucketData {

    List<StockWatchDb> list = new ArrayList<>();
    BigDecimal promote;
    BigDecimal demote;

    public BucketData() {
    }

    public List<StockWatchDb> getAllPromote() {
        return list.stream()
                .filter(item -> item.getMacd().compareTo(promote) > 0)
                .collect(Collectors.toList());
    }

    public List<StockWatchDb> getAllDemote() {
        return list.stream()
                .filter(item -> item.getMacd().compareTo(demote) < 0)
                .collect(Collectors.toList());
    }

    public void setData(List<StockWatchDb> records) {
        list = records;
    }

    public List<StockWatchDb> getData()  {
        return list;
    }

    public void addItem(StockWatchDb item) {
        if (!list.contains(item))
            list.add(item);
    }

    public void removeItem(StockWatchDb item) {
        list.remove(item);
    }

    public int size() {
        return list.size();
    }
}

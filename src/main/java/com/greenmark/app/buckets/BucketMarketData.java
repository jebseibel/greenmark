package com.greenmark.app.buckets;

import com.greenmark.common.database.domain.StockDailyDb;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class BucketMarketData {

    List<StockDailyDb> list = new ArrayList<>();
    BigDecimal promote;
    BigDecimal demote;

    public BucketMarketData() {
    }

    public List<StockDailyDb> getAllPromote() {
        return list.stream()
                .filter(item -> item.getMacd().compareTo(promote) > 0)
                .collect(Collectors.toList());
    }

    public List<StockDailyDb> getAllDemote() {
        return list.stream()
                .filter(item -> item.getMacd().compareTo(demote) < 0)
                .collect(Collectors.toList());
    }

    public void setData(List<StockDailyDb> records) {
        list = records;
    }

    public List<StockDailyDb> getData()  {
        return list;
    }

    public void addItem(StockDailyDb item) {
        if (!list.contains(item))
            list.add(item);
    }

    public void removeItem(StockDailyDb item) {
        list.remove(item);
    }

    public int size() {
        return list.size();
    }
}

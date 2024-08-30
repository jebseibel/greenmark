package com.greenmark.app.buckets;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.greenmark.common.database.domain.StockWatchDb;
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
public class BucketData {

    @JsonProperty("name")
    String name;

    @JsonProperty("promote")
    BigDecimal promote;

    @JsonProperty("demote")
    BigDecimal demote;

    List<StockWatchDb> list = new ArrayList<>();

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


    public String toStringLong() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName()).append("\n");
        sb.append("---------------\n");
        sb.append("Name    : ").append(name).append("\n");
        sb.append("promote : ").append(promote).append("\n");
        sb.append("demote  : ").append(demote).append("\n");

        sb.append("\nStocks:\n");
        //list
        sb.append("----------------\n");
        list.forEach(s -> { sb.append(s.toString()).append("\n"); });

        return sb.toString();
    }
}

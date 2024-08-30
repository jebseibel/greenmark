package com.greenmark.app.buckets;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.greenmark.common.database.domain.StockWatchDb;
import com.greenmark.common.enums.TimeframeType;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@Data
public class BucketTools {

    @JsonProperty("name")
    String name;

    @JsonProperty("promote")
    BigDecimal promote;

    @JsonProperty("demote")
    BigDecimal demote;

    @JsonProperty("order")
    int order;

    @JsonProperty("timeframe")
    TimeframeType timeframe;

    List<StockWatchDb> list = new ArrayList<>();

    public BucketTools() {
    }

//    public List<StockWatchDb> getAllPromote() {
//        return list.stream()
//                .filter(item -> item.getMacd().compareTo(promote) > 0)
//                .collect(Collectors.toList());
//    }
//
//    public List<StockWatchDb> getAllDemote() {
//        return list.stream()
//                .filter(item -> item.getMacd().compareTo(demote) < 0)
//                .collect(Collectors.toList());
//    }

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

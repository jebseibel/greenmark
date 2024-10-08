package com.greenmark.app.buckets;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.greenmark.common.database.domain.StockWatch;
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
@JsonPropertyOrder({ "name", "order", "timeframe", "demote", "promote", "stockWatchList" })
public class BucketData {

    @JsonProperty("name")
    String name;

    @JsonProperty("order")
    int order;

    @JsonProperty("timeframe")
    TimeframeType timeframe;

    @JsonProperty("promote")
    BigDecimal promote;

    @JsonProperty("demote")
    BigDecimal demote;

    @JsonProperty("stocklist")
    List<StockWatch> stockWatchList = new ArrayList<>();

    public BucketData() {
    }

    /**
     * Convenience method
     *
     * @param item
     */
    public void addItem(StockWatch item) {
        if (!stockWatchList.contains(item))
            stockWatchList.add(item);
    }

    public void removeItem(StockWatch item) {
        stockWatchList.remove(item);
    }

    public String toStringLong() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName()).append("\n");
        sb.append("---------------\n");
        sb.append("Name    : ").append(name).append("\n");
        sb.append("Order   : ").append(order).append("\n");
        sb.append("Tf      : ").append(timeframe).append("\n");
        sb.append("promote : ").append(promote).append("\n");
        sb.append("demote  : ").append(demote).append("\n");

        sb.append("\nStocks:\n");
        //list
        sb.append("----------------\n");
        stockWatchList.forEach(s -> { sb.append(s.toString()).append("\n"); });

        return sb.toString();
    }
}

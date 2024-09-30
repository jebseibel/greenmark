package com.greenmark.common.database.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class StockWatch {
    private String symbol;
    private BigDecimal current;
    private BigDecimal open;
    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal previousClose;
    private BigDecimal changed;
    private BigDecimal changedPercent;
    private BigDecimal macd;
    private BigDecimal stochk;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private LocalDateTime deletedAt;
    private Integer active;

    public StockWatch(String symbol) {
        this.symbol = symbol;
    }

    public String toStringTA() {
        return this.getClass().getSimpleName() + " symbol [" + symbol + "] macd [" + macd + "] stochk [" + stochk + "]";
    }

    public String toStringShort() {
        return this.getClass().getSimpleName() +" [" + symbol + "]";
    }

    @Override
    public String toString() {
        return toStringShort();
    }
}

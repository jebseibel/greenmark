package com.greenmark.common.database.domain;

import lombok.Data;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
@Data
public class MarketData {

    @NonNull
    private BigDecimal current;
    @NonNull
    private BigDecimal open;
    @NonNull
    private BigDecimal high;
    @NonNull
    private BigDecimal low;
    @NonNull
    private BigDecimal previousClose;
    private BigDecimal changed;
    private BigDecimal changedPercent;
    private BigDecimal macd;
    private BigDecimal stochk;

    public MarketData() {

    }
}

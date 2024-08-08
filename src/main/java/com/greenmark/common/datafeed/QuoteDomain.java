package com.greenmark.common.datafeed;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class QuoteDomain {
    private BigDecimal current;
    private BigDecimal open;
    private BigDecimal high;
    private BigDecimal low;

    private BigDecimal previousClose;
    private BigDecimal change;
    private BigDecimal changePercent;
}

package com.greenmark.core;

import com.greenmark.core.enums.StockStatus;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class StockStub {
    protected String symbol;
    protected String name;
    protected String market;
    protected StockStatus status;

    public StockStub() {

    }
}

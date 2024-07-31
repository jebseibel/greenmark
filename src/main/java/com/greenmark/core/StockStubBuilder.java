package com.greenmark.core;

public class StockStubBuilder {

    public StockStub fromStock(Stock stock) {
        StockStub stub = StockStub.builder()
                .symbol(stock.symbol)
                .name(stock.name)
                .market(stock.market)
                .status(stock.status)
                .build();
        return stub;
    }

}

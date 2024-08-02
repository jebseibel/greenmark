package com.greenmark.database.db.mapper;

import com.greenmark.common.database.domain.StockDb;
import com.greenmark.database.db.entity.Stock;
import org.modelmapper.ModelMapper;

public class StockMapper {
    public static StockDb toDb(Stock stock) {
        return new ModelMapper().map(stock, StockDb.class);
    }

    public static Stock toEntity(StockDb itemDb) {
        return new ModelMapper().map(itemDb, Stock.class);
    }
}

package com.greenmark.database.db.mapper;

import com.greenmark.common.database.domain.StockDailyDb;
import com.greenmark.common.database.domain.StockDb;
import com.greenmark.database.db.entity.Stock;
import com.greenmark.database.db.entity.StockDaily;
import org.modelmapper.ModelMapper;

import java.util.List;

public class StockMapper {
    public static StockDb toDb(Stock stock) {
        return new ModelMapper().map(stock, StockDb.class);
    }

    public static Stock toEntity(StockDb itemDb) {
        return new ModelMapper().map(itemDb, Stock.class);
    }

    public static List<StockDb> toList(List<Stock> items) {
        return items.stream().map( item -> toDb(item)).toList();
    }
}

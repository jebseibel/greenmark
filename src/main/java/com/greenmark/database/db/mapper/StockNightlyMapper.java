package com.greenmark.database.db.mapper;

import com.greenmark.common.database.domain.StockDb;
import com.greenmark.common.database.domain.StockNightlyDb;
import com.greenmark.database.db.entity.Stock;
import com.greenmark.database.db.entity.StockNightly;
import org.modelmapper.ModelMapper;

public class StockNightlyMapper {
    public static StockNightlyDb toDb(StockNightly item) {
        return new ModelMapper().map(item, StockNightlyDb.class);
    }

    public static StockNightly toEntity(StockNightlyDb itemDb) {
        return new ModelMapper().map(itemDb, StockNightly.class);
    }
}

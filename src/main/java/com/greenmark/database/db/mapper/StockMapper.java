package com.greenmark.database.db.mapper;

import com.greenmark.common.database.domain.StockDb;
import com.greenmark.database.db.entity.StockEntity;
import org.modelmapper.ModelMapper;

public class StockMapper
{
    public static StockDb toDb(StockEntity stock) {
        return new ModelMapper().map(stock, StockDb.class);
    }

    public static StockEntity toEntity(StockDb accountDb) {
        return new ModelMapper().map(accountDb, StockEntity.class);
    }
}

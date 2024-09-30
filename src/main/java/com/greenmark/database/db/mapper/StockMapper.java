package com.greenmark.database.db.mapper;

import com.greenmark.common.database.domain.Stock;
import com.greenmark.database.db.entity.StockDb;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
public class StockMapper {
    public Stock toEntity(StockDb stockDb) {
        return new ModelMapper().map(stockDb, Stock.class);
    }

//    public StockDb toEntity(Stock itemDb) {
//        return new ModelMapper().map(itemDb, StockDb.class);
//    }

    public List<Stock> toList(List<StockDb> items) {
        return items.stream().map(this::toEntity).toList();
    }
}

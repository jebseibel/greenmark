package com.greenmark.database.db.mapper;

import com.greenmark.common.database.domain.StockDb;
import com.greenmark.database.db.entity.Stock;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
public class StockMapper {
    public StockDb toDb(Stock stock) {
        return new ModelMapper().map(stock, StockDb.class);
    }

    public Stock toEntity(StockDb itemDb) {
        return new ModelMapper().map(itemDb, Stock.class);
    }

    public List<StockDb> toList(List<Stock> items) {
        return items.stream().map(this::toDb).toList();
    }
}

package com.greenmark.database.db.mapper;

import com.greenmark.common.database.domain.StockDailyDb;
import com.greenmark.database.db.entity.StockDaily;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
public class StockDailyMapper {

    public StockDailyDb toDb(StockDaily item) {
        return new ModelMapper().map(item, StockDailyDb.class);
    }

    public StockDaily toEntity(StockDailyDb itemDb) {
        return new ModelMapper().map(itemDb, StockDaily.class);
    }

    public List<StockDailyDb> toList(List<StockDaily> items) {
        return items.stream().map(this::toDb).toList();
    }
}

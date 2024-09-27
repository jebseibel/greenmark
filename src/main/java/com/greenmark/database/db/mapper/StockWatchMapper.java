package com.greenmark.database.db.mapper;

import com.greenmark.common.database.domain.StockWatchDb;
import com.greenmark.database.db.entity.StockWatch;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
public class StockWatchMapper {

    public StockWatchDb toDb(StockWatch item) {
        return new ModelMapper().map(item, StockWatchDb.class);
    }

    public StockWatch toEntity(StockWatchDb itemDb) {
        return new ModelMapper().map(itemDb, StockWatch.class);
    }

    public List<StockWatchDb> toList(List<StockWatch> items) {
        return items.stream().map(this::toDb).toList();
    }
}

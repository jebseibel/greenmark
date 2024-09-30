package com.greenmark.database.db.mapper;

import com.greenmark.common.database.domain.StockWatch;
import com.greenmark.database.db.entity.StockWatchDb;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
public class StockWatchMapper {

    public StockWatch toEntity(StockWatchDb item) {
        return new ModelMapper().map(item, StockWatch.class);
    }

//    public StockWatchDb toEntity(StockWatch itemDb) {
//        return new ModelMapper().map(itemDb, StockWatchDb.class);
//    }

    public List<StockWatch> toList(List<StockWatchDb> items) {
        return items.stream().map(this::toEntity).toList();
    }
}

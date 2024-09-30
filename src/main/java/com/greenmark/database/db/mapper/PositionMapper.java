package com.greenmark.database.db.mapper;

import com.greenmark.common.database.domain.Position;
import com.greenmark.database.db.entity.PositionDb;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
public class PositionMapper {

    public Position toEntity(PositionDb item) {
        return Position.builder()
                .extid(item.getExtid())
                .symbol(item.getSymbol())
                .name(item.getName())
                .price(item.getPrice())
                .shares(item.getShares())
                .total(item.getTotal())
                .createdAt(item.getCreatedAt())
                .modifiedAt(item.getModifiedAt())
                .deletedAt(item.getDeletedAt())
                .active(item.getActive())
                .build();
    }
//
//    public PositionDb toEntity(Position itemDb) {
//        return new ModelMapper().map(itemDb, PositionDb.class);
//    }

    public List<Position> toList(List<PositionDb> items) {
        return items.stream().map(this::toEntity).toList();
    }
}

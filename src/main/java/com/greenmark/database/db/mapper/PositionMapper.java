package com.greenmark.database.db.mapper;

import com.greenmark.common.database.domain.PositionDb;
import com.greenmark.database.db.entity.Position;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
public class PositionMapper {

    public PositionDb toDb(Position item) {
        return PositionDb.builder()
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

    public Position toEntity(PositionDb itemDb) {
        return new ModelMapper().map(itemDb, Position.class);
    }

    public List<PositionDb> toList(List<Position> items) {
        return items.stream().map(this::toDb).toList();
    }
}

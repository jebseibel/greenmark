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
        return new ModelMapper().map(item, PositionDb.class);
    }

    public Position toEntity(PositionDb itemDb) {
        return new ModelMapper().map(itemDb, Position.class);
    }

    public List<PositionDb> toList(List<Position> items) {
        return items.stream().map(this::toDb).toList();
    }
}

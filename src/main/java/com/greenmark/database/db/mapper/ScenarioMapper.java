package com.greenmark.database.db.mapper;

import com.greenmark.common.database.domain.Scenario;
import com.greenmark.database.db.entity.ScenarioDb;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
public class ScenarioMapper {
    public Scenario toEntity(ScenarioDb item) {
        return new ModelMapper().map(item, Scenario.class);
    }

//    public ScenarioDb toEntity(Scenario itemDb) {
//        return new ModelMapper().map(itemDb, ScenarioDb.class);
//    }

    public List<Scenario> toList(List<ScenarioDb> items) {
        if (items == null) {
            return null;
        }
        return items.stream().map(this::toEntity).toList();
    }
}

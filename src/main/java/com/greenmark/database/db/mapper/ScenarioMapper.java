package com.greenmark.database.db.mapper;

import com.greenmark.common.database.domain.ScenarioDb;
import com.greenmark.database.db.entity.Scenario;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class ScenarioMapper {
    public ScenarioDb toDb(Scenario item) {
        return new ModelMapper().map(item, ScenarioDb.class);
    }

    public Scenario toEntity(ScenarioDb itemDb) {
        return new ModelMapper().map(itemDb, Scenario.class);
    }
}

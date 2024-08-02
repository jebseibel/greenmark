package com.greenmark.database.db.mapper;

import com.greenmark.common.database.domain.ScenarioDb;
import com.greenmark.database.db.entity.Scenario;
import org.modelmapper.ModelMapper;

public class ScenarioMapper {
    public static ScenarioDb toDb(Scenario item) {
        return new ModelMapper().map(item, ScenarioDb.class);
    }

    public static Scenario toEntity(ScenarioDb itemDb) {
        return new ModelMapper().map(itemDb, Scenario.class);
    }
}

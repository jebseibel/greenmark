package com.greenmark.database.db.mapper;

import com.greenmark.common.database.domain.ScenarioDb;
import com.greenmark.database.db.entity.ScenarioEntity;
import org.modelmapper.ModelMapper;

public class ScenarioMapper
{
    public static ScenarioDb toDb(ScenarioEntity Scenario) {
        return new ModelMapper().map(Scenario, ScenarioDb.class);
    }

    public static ScenarioEntity toEntity(ScenarioDb accountDb) {
        return new ModelMapper().map(accountDb, ScenarioEntity.class);
    }
}

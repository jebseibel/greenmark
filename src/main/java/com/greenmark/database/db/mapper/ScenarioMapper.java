package com.greenmark.database.db.mapper;

import com.greenmark.common.database.domain.ScenarioDb;
import com.greenmark.database.db.entity.Scenario;
import org.modelmapper.ModelMapper;

public class ScenarioMapper
{
    public static ScenarioDb toDb(Scenario Scenario) {
        return new ModelMapper().map(Scenario, ScenarioDb.class);
    }

    public static Scenario toEntity(ScenarioDb accountDb) {
        return new ModelMapper().map(accountDb, Scenario.class);
    }
}

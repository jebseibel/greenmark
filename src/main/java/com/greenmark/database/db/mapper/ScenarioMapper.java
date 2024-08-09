package com.greenmark.database.db.mapper;

import com.greenmark.common.database.domain.AccountDb;
import com.greenmark.common.database.domain.ScenarioDb;
import com.greenmark.database.db.entity.Account;
import com.greenmark.database.db.entity.Scenario;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
public class ScenarioMapper {
    public ScenarioDb toDb(Scenario item) {
        return new ModelMapper().map(item, ScenarioDb.class);
    }

    public Scenario toEntity(ScenarioDb itemDb) {
        return new ModelMapper().map(itemDb, Scenario.class);
    }

    public List<ScenarioDb> toList(List<Scenario> items) {
        if (items == null) { return null; }
        return items.stream().map( item -> toDb(item)).toList();
    }
}

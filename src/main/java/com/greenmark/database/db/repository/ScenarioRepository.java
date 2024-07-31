package com.greenmark.database.db.repository;

import com.greenmark.database.db.entity.ScenarioEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScenarioRepository extends CrudRepository<ScenarioEntity, Integer> {
    ScenarioEntity findByName(String name);

    ScenarioEntity findByExtid(String extid);
}
package com.greenmark.database.db.repository;

import com.greenmark.database.db.entity.Scenario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScenarioRepository extends CrudRepository<Scenario, Integer> {
    Scenario findByName(String name);
    Scenario findByExtid(String extid);
}
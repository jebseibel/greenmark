package com.greenmark.database.db.repository;

import com.greenmark.database.db.entity.ScenarioDb;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScenarioRepository extends ListCrudRepository<ScenarioDb, Integer> {
    Optional<ScenarioDb> findByName(String name);
    Optional<ScenarioDb> findByExtid(String extid);
}
package com.greenmark.database.db.repository;

import com.greenmark.database.db.entity.Scenario;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScenarioRepository extends ListCrudRepository<Scenario, Integer> {
    Optional<Scenario> findByName(String name);
    Optional<Scenario> findByExtid(String extid);
}
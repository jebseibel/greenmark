package com.greenmark.database.db.repository;

import com.greenmark.database.db.entity.PositionDb;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PositionRepository extends ListCrudRepository<PositionDb, Integer> {
    Optional<PositionDb> findByExtid(String extid);
    Optional<PositionDb> findBySymbol(String name);
    Optional<PositionDb> findByName(String name);
    List<PositionDb> findByActive(Integer active);
}
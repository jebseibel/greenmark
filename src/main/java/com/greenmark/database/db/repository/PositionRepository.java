package com.greenmark.database.db.repository;

import com.greenmark.database.db.entity.Position;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PositionRepository extends ListCrudRepository<Position, Integer> {
    Optional<Position> findByExtid(String extid);
    Optional<Position> findBySymbol(String name);
    Optional<Position> findByName(String name);
    List<Position> findByActive(Integer active);
}
package com.greenmark.database.db.repository;

import com.greenmark.database.db.entity.StockDb;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository extends ListCrudRepository<StockDb, Integer> {
    StockDb findByName(String name);
    StockDb findByExtid(String extid);
    Optional<StockDb> findBySymbol(String name);
    List<StockDb> findByActive(Integer active);
}
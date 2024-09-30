package com.greenmark.database.db.repository;

import com.greenmark.database.db.entity.StockDailyDb;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockDailyRepository extends ListCrudRepository<StockDailyDb, Integer> {
    Optional<StockDailyDb> findBySymbol(String name);
    List<StockDailyDb> findByActive(Integer active);
}
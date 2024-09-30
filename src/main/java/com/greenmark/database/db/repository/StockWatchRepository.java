package com.greenmark.database.db.repository;

import com.greenmark.database.db.entity.StockWatchDb;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockWatchRepository extends ListCrudRepository<StockWatchDb, Integer> {
    Optional<StockWatchDb> findBySymbol(String name);
    List<StockWatchDb> findByActive(Integer active);
    List<StockWatchDb> findByTimeframeAndActive(String timeframe, Integer active);
}
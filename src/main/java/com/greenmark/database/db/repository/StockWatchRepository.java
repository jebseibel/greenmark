package com.greenmark.database.db.repository;

import com.greenmark.database.db.entity.StockWatch;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockWatchRepository extends ListCrudRepository<StockWatch, Integer> {
    Optional<StockWatch> findBySymbol(String name);

    List<StockWatch> findByActive(Integer active);

    List<StockWatch> findByTimeframeAndActive(String timeframe, Integer active);
}
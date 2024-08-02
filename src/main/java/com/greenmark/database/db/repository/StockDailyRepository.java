package com.greenmark.database.db.repository;

import com.greenmark.database.db.entity.StockDaily;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockDailyRepository extends CrudRepository<StockDaily, Integer> {
    StockDaily findBySymbol(String symbol);
}
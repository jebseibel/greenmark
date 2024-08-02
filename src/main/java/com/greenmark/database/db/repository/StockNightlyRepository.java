package com.greenmark.database.db.repository;

import com.greenmark.database.db.entity.Stock;
import com.greenmark.database.db.entity.StockNightly;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockNightlyRepository extends CrudRepository<StockNightly, Integer> {
    StockNightly findBySymbol(String symbol);
}
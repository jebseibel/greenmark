package com.greenmark.database.db.repository;

import com.greenmark.database.db.entity.StockDaily;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockDailyRepository extends ListCrudRepository<StockDaily, Integer> {
    Optional<StockDaily> findBySymbol(String name);
    List<StockDaily> findByActive(Integer active);
}
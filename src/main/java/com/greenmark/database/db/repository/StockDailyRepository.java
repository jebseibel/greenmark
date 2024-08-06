package com.greenmark.database.db.repository;

import com.greenmark.database.db.entity.BucketDaily;
import com.greenmark.database.db.entity.StockDaily;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockDailyRepository extends CrudRepository<StockDaily, Integer> {
    Optional<StockDaily> findBySymbol(String name);
}
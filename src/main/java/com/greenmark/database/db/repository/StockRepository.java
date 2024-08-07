package com.greenmark.database.db.repository;

import com.greenmark.database.db.entity.BucketDaily;
import com.greenmark.database.db.entity.Stock;
import com.greenmark.database.db.entity.StockDaily;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository extends CrudRepository<Stock, Integer> {
    Stock findByName(String name);
    Stock findByExtid(String extid);
    Optional<Stock> findBySymbol(String name);
    List<Stock> findByActive(Integer active);
}
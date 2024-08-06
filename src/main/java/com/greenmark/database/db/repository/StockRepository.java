package com.greenmark.database.db.repository;

import com.greenmark.database.db.entity.Stock;
import com.greenmark.database.db.entity.StockDaily;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends CrudRepository<Stock, Integer> {
    Stock findByName(String name);
    Stock findByExtid(String extid);
    List<Stock> findByActive(Integer active);
}
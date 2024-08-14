package com.greenmark.database.db.repository;

import com.greenmark.database.db.entity.Stock;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository extends ListCrudRepository<Stock, Integer> {
    Stock findByName(String name);

    Stock findByExtid(String extid);

    Optional<Stock> findBySymbol(String name);

    List<Stock> findByActive(Integer active);
}
package com.greenmark.database.db.repository;

import com.greenmark.database.db.entity.StockEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends CrudRepository<StockEntity, Integer> {
    StockEntity findByName(String name);

    StockEntity findByExtid(String extid);
}
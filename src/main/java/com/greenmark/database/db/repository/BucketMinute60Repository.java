package com.greenmark.database.db.repository;

import com.greenmark.database.db.entity.BucketMinute60;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BucketMinute60Repository extends CrudRepository<BucketMinute60, Integer> {
    BucketMinute60 findBySymbol(String name);
}
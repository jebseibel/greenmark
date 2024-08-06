package com.greenmark.database.db.repository;

import com.greenmark.database.db.entity.BucketMinute01;
import com.greenmark.database.db.entity.BucketMinute05;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BucketMinute05Repository extends CrudRepository<BucketMinute05, Integer> {
    Optional<BucketMinute05> findBySymbol(String name);
}
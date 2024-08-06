package com.greenmark.database.db.repository;

import com.greenmark.database.db.entity.BucketDaily;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BucketDailyRepository extends CrudRepository<BucketDaily, Integer> {
    Optional<BucketDaily> findBySymbol(String name);
}
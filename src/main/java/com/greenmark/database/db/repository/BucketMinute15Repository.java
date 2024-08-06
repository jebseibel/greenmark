package com.greenmark.database.db.repository;

import com.greenmark.database.db.entity.BucketMinute01;
import com.greenmark.database.db.entity.BucketMinute15;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BucketMinute15Repository extends CrudRepository<BucketMinute15, Integer> {
    Optional<BucketMinute15> findBySymbol(String name);
    List<BucketMinute15> findByActive(Integer active);
}
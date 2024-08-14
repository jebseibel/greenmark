package com.greenmark.database.db.repository;

import com.greenmark.database.db.entity.BucketMinute01;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BucketMinute01Repository extends ListCrudRepository<BucketMinute01, Integer> {
    Optional<BucketMinute01> findBySymbol(String name);

    List<BucketMinute01> findByActive(Integer active);
}
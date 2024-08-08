package com.greenmark.database.db.repository;

import com.greenmark.database.db.entity.BucketMinute01;
import com.greenmark.database.db.entity.BucketMinute05;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BucketMinute05Repository extends ListCrudRepository<BucketMinute05, Integer> {
    Optional<BucketMinute05> findBySymbol(String name);
    List<BucketMinute05> findByActive(Integer active);
}
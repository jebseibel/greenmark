package com.greenmark.database.db.repository;

import com.greenmark.database.db.entity.Bucket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BucketRepository extends CrudRepository<Bucket, Integer> {
    Bucket findByName(String name);

    Bucket findByExtid(String extid);
}
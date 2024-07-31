package com.greenmark.database.db.repository;

import com.greenmark.database.db.entity.BucketEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BucketRepository extends CrudRepository<BucketEntity, Integer> {
    BucketEntity findByName(String name);

    BucketEntity findByExtid(String extid);
}
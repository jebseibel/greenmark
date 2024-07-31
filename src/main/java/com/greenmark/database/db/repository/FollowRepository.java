package com.greenmark.database.db.repository;

import com.greenmark.database.db.entity.FollowEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends CrudRepository<FollowEntity, Integer> {
    FollowEntity findByName(String name);

    FollowEntity findByExtid(String extid);
}
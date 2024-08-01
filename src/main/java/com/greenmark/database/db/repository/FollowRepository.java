package com.greenmark.database.db.repository;

import com.greenmark.database.db.entity.Follow;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends CrudRepository<Follow, Integer> {
    Follow findByName(String name);

    Follow findByExtid(String extid);
}
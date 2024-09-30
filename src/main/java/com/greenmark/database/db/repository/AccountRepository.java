package com.greenmark.database.db.repository;

import com.greenmark.database.db.entity.AccountDb;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends ListCrudRepository<AccountDb, Integer> {
    Optional<AccountDb> findByName(String name);
    Optional<AccountDb> findByExtid(String extid);
}
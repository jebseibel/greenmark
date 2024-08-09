package com.greenmark.database.db.repository;

import com.greenmark.database.db.entity.Account;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends ListCrudRepository<Account, Integer> {
    Optional<Account> findByName(String name);
    Optional<Account> findByExtid(String extid);
}
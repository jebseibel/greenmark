package com.greenmark.database.db.repository;

import com.greenmark.database.db.entity.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {
    Account findByName(String name);
    Account findByExtid(String extid);
}
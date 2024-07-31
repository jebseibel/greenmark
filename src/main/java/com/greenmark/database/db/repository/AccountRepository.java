package com.greenmark.database.db.repository;

import com.greenmark.database.db.entity.AccountEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<AccountEntity, Integer> {
    AccountEntity findByName(String name);
    AccountEntity findByExtid(String extid);
}
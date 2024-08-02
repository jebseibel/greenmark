package com.greenmark.database.db.mapper;

import com.greenmark.common.database.domain.AccountDb;
import com.greenmark.database.db.entity.Account;
import org.modelmapper.ModelMapper;

public class AccountMapper {
    public static AccountDb toDb(Account item) {
        return new ModelMapper().map(item, AccountDb.class);
    }

    public static Account toEntity(AccountDb itemDb) {
        return new ModelMapper().map(itemDb, Account.class);
    }
}

package com.greenmark.database.db.mapper;

import com.greenmark.common.database.domain.AccountDb;
import com.greenmark.database.db.entity.Account;
import org.modelmapper.ModelMapper;

public class AccountMapper
{
    public static AccountDb toDb(Account account) {
        return new ModelMapper().map(account, AccountDb.class);
    }

    public static Account toEntity(AccountDb accountDb) {
        return new ModelMapper().map(accountDb, Account.class);
    }
}

package com.greenmark.database.db.mapper;

import com.greenmark.common.database.domain.AccountDb;
import com.greenmark.database.db.entity.AccountEntity;
import org.modelmapper.ModelMapper;

public class AccountMapper {
    public static AccountDb toDb(AccountEntity account) {
        return new ModelMapper().map(account, AccountDb.class);
    }

    public static AccountEntity toEntity(AccountDb accountDb) {
        return new ModelMapper().map(accountDb, AccountEntity.class);
    }
}

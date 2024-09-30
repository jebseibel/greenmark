package com.greenmark.database.db.mapper;

import com.greenmark.common.database.domain.Account;
import com.greenmark.database.db.entity.AccountDb;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
public class AccountMapper {

    public Account toEntity(AccountDb item) {
        return new ModelMapper().map(item, Account.class);
    }

//    public AccountDb toDb(Account itemDb) {
//        return new ModelMapper().map(itemDb, AccountDb.class);
//    }

    public List<Account> toList(List<AccountDb> items) {
        if (items == null) {
            return null;
        }
        return items.stream().map(this::toEntity).toList();
    }
}

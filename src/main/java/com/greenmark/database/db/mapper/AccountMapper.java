package com.greenmark.database.db.mapper;

import com.greenmark.common.database.domain.AccountDb;
import com.greenmark.database.db.entity.Account;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
public class AccountMapper {

    public AccountDb toDb(Account item) {
        return new ModelMapper().map(item, AccountDb.class);
    }

    public Account toEntity(AccountDb itemDb) {
        return new ModelMapper().map(itemDb, Account.class);
    }

    public List<AccountDb> toList(List<Account> items) {
        if (items == null) {
            return null;
        }
        return items.stream().map(this::toDb).toList();
    }
}

package com.greenmark.database.db.mapper;

import com.greenmark.common.database.domain.AccountDb;
import com.greenmark.common.database.domain.AccountDb;
import com.greenmark.database.db.entity.Account;
import com.greenmark.database.db.entity.Account;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class AccountMapper {

    String name = "thisname";

    public AccountMapper() {
    }

    public AccountDb toDb(Account item) {
        return new ModelMapper().map(item, AccountDb.class);
    }

    public Account toEntity(AccountDb itemDb) {
        return new ModelMapper().map(itemDb, Account.class);
    }

    public List<AccountDb> toList(List<Account> items) {
        if (items == null) { return null; }
        return items.stream().map( item -> toDb(item)).toList();
    }
}

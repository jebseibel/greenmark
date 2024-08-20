package com.greenmark.common.database.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AccountDb extends BaseDb {
    private String extid;
    private String name;
    private String description;
}

package com.greenmark.common.database.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AccountDb extends BaseDb {
    private String extid;
    private String name;
    private String description;
}

package com.greenmark.common.database.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Account extends BaseEntity {
    private String extid;
    private String name;
    private String description;
}

package com.greenmark.common.database.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StockDb {
    private String extid;
    private String symbol;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private LocalDateTime deletedAt;
    private Integer active;
}

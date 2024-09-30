package com.greenmark.common.database.domain;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Slf4j
@Data
@Builder
public class Position {
    private String extid;
    private String symbol;
    private String name;
    private Integer shares;
    private BigDecimal price;
    private BigDecimal total;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private LocalDateTime deletedAt;
    private Integer active;
}

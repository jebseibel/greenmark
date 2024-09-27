package com.greenmark.web.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class ResponsePosition {
    private String extid;
    private String symbol;
    private String name;
    private Integer shares;
    private BigDecimal price;
    private BigDecimal total;
    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime deleted;
}

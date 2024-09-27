package com.greenmark.web.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class RequestPositionUpdate {

    @NotEmpty(message = "The extid is required.")

    private String name;
    private Integer shares;
    private BigDecimal price;
    private BigDecimal total;
}

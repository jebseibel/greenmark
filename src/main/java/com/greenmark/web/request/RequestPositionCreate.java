package com.greenmark.web.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class RequestPositionCreate {

    @NotEmpty(message = "The symbol is required.")
    @Size(max = 8, message = "...")
    private String symbol;

    @NotEmpty(message = "The name is required.")
    @Size(max = 64, message = "{validation.street.size.too_long}")
    private String name;

    @NotEmpty(message = "The shares are required.")
    private Integer shares;

    @NotEmpty(message = "The price is required.")
    private BigDecimal price;

    @NotEmpty(message = "The total is required.")
    private BigDecimal total;
}

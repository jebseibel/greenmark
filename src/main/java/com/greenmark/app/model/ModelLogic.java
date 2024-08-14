package com.greenmark.app.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ModelLogic {
    private BigDecimal demote;
    private BigDecimal promote;

    public ModelLogic(BigDecimal demote, BigDecimal promote) {
        this.demote = demote;
        this.promote = promote;
    }
}

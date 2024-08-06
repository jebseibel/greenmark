package com.greenmark.app;

import com.greenmark.common.database.domain.StockDailyDb;

import java.math.BigDecimal;

public class ModelLogic {
    private BigDecimal stockMinPrice;
    private BigDecimal stockMaxPrice;

    private BigDecimal passStochkDaily;
    private BigDecimal passStochkMinute60;
    private BigDecimal passStochkMinute15;
    private BigDecimal passStochkMinute05;
    private BigDecimal passStochkMinute01;

    public ModelLogic() {
    }

    public boolean passStockDaily(StockDailyDb stock) {
        if (stock.getStochk().compareTo(passStochkDaily) > 0) {
            return true;
        }
        return false;
    }

}

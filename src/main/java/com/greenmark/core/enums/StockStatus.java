package com.greenmark.core.enums;

public enum StockStatus {
    NEW(1),
    ORDER1_PLACED(2),
    ORDER2_QUALIFY(3),
    ORDER2_PLACED(4),
    COMPLETE(5);
    
    public final int value;

    StockStatus(int value) {
        this.value = value;
    }

    public int getValue() { return value; }
}
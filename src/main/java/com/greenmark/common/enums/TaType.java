package com.greenmark.common.enums;

public enum TaType {
    MACD("macd"),
    STOCHK("stochk");

    public final String value;

    TaType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

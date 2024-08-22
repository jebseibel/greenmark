package com.greenmark.common.enums;

public enum TimeframeType {
    MINUTE01("1"),
    MINUTE05("5"),
    MINUTE15("15"),
    MINUTE60("60"),
    DAILY("D");

    public final String value;

    TimeframeType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

package com.greenmark.common.enums;

public enum ResolutionType {
    MINUTE01("1"),
    MINUTE05("5"),
    MINUTE15("15"),
    MINUTE60("60"),
    DAILY("D"),
    WEEKLY("W"),
    MONTHLY("M");

    public final String value;

    ResolutionType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

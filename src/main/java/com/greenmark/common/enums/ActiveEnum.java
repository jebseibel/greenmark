package com.greenmark.common.enums;

public enum ActiveEnum {
    ACTIVE(1),
    INACTIVE(0);

    public final int value;

    ActiveEnum(int value) {
        this.value = value;
    }

    public int getValue() { return value; }

}
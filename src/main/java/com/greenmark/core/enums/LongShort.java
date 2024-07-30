package com.greenmark.core.enums;

public enum LongShort {
    LONG(1),
    SHORT(2);

    public final int value;

    LongShort(int value) {
        this.value = value;
    }

    public int getValue() { return value; }
}
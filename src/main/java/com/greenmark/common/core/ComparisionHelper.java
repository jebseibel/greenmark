package com.greenmark.common.core;

public class ComparisionHelper {


    public static final String getComparision(int incoming) {
        if (incoming == -1) {
            return "LESSER";
        }
        if (incoming == 1) {
            return "GREATER";
        }
        if (incoming == 0) {
            return "EQUAL";
        }
        return "NONE";
    }
}

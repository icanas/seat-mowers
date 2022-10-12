package com.seat.code.domain.enums;

public enum Operation {
    L,
    R,
    M;

    public static Operation valueOf(char c){
        return Operation.valueOf(String.valueOf(c));
    }
}

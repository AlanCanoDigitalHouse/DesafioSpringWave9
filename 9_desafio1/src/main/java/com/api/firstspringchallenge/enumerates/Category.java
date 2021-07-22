package com.api.firstspringchallenge.enumerates;

public enum Category {
    SILLAS(0), TECLADOS(1);

    private int value;

    public void setValue(int value) {
        this.value = value;
    }

    private Category(int value){
        this.value=value;
    }

    public int getValue() {
        return value;
    }
}

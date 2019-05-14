package com.galaxy.numeral;

import java.util.List;

public abstract class Number<S extends Symbol> {
    protected String representation;
    protected int value;
    protected List<S> symbolList;

    public List<S> getSymbolList() {
        return symbolList;
    }

    public String getRepresentation() {
        return representation;
    }

    public int getValue() {
        return value;
    }

    void setValue(int value) {
        this.value = value;
    }

}
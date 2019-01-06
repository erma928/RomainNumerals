package com.galaxy.numeral;

/**
 * Created by fengjimin on 1/5/19.
 */
public enum RomanSymbol {
    I('I', 1),
    V('V', 5),
    X('X', 10),
    L('L', 50),
    C('C', 100),
    D('D', 500),
    M('M', 1000),
    ;

    private char code;
    private int value;

    private RomanSymbol(char code, int value) {
        this.code = code;
        this.value = value;
    }

    public char getCode() {
        return code;
    }

    public int getValue() {
        return value;
    }

    public boolean isRepeatable() {
        switch (code) {
            case 'I':
            case 'X':
            case 'C':
            case 'M':
                return true;
            default:
                return false;
        }
    }

    public boolean isDeductibleFrom(RomanSymbol largerSymbol) {
        switch (code) {
            case 'I':
                if (largerSymbol.getCode()=='V' || largerSymbol.getCode()=='X') {
                    return true;
                } else {
                    return false;
                }
            case 'X':
                if (largerSymbol.getCode()=='L' || largerSymbol.getCode()=='C') {
                    return true;
                } else {
                    return false;
                }
            case 'C':
                if (largerSymbol.getCode()=='D' || largerSymbol.getCode()=='M') {
                    return true;
                } else {
                    return false;
                }
            default:
                return false;
        }
    }

    public static RomanSymbol getByCode(char code) {
        for (RomanSymbol symbol: RomanSymbol.values()) {
            if (symbol.getCode()==code) {
                return symbol;
            }
        }
        return null;
    }

    public static RomanSymbol getByValue(int value) {
        for (RomanSymbol symbol: RomanSymbol.values()) {
            if (symbol.getValue()==value) {
                return symbol;
            }
        }
        return null;
    }

}

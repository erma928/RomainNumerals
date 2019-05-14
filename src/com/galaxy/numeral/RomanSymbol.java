package com.galaxy.numeral;

/**
 * symbol for roman numeral.
 * this class also encodes parser rules for the symbol:
 * whether it is repeatable, or deductible, etc
 */
public enum RomanSymbol implements Symbol {
    I("I", 1),
    V("V", 5),
    X("X", 10),
    L("L", 50),
    C("C", 100),
    D("D", 500),
    M("M", 1000),
    ;

    public static RomanSymbol getByCode(String code) {
        for (RomanSymbol symbol: RomanSymbol.values()) {
            if (symbol.getCode().equals(code)) {
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

    private String code;
    private int value;

    private RomanSymbol(String code, int value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public int getValue() {
        return value;
    }

    public boolean isRepeatable() {
        switch (code) {
            case "I":
            case "X":
            case "C":
            case "M":
                return true;
            default:
                return false;
        }
    }

    public boolean isDeductibleFrom(Symbol largerSymbol) {
        switch (code) {
            case "I":
                if (largerSymbol.getCode().equals("V") || largerSymbol.getCode().equals("X")) {
                    return true;
                } else {
                    return false;
                }
            case "X":
                if (largerSymbol.getCode().equals("L") || largerSymbol.getCode().equals("C")) {
                    return true;
                } else {
                    return false;
                }
            case "C":
                if (largerSymbol.getCode().equals("D") || largerSymbol.getCode().equals("M")) {
                    return true;
                } else {
                    return false;
                }
            default:
                return false;
        }
    }

}

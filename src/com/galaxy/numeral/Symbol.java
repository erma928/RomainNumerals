package com.galaxy.numeral;

/**
 * symbols for numerals
 */
public interface Symbol {
    String getCode();
    int getValue();
    boolean isRepeatable();
    boolean isDeductibleFrom(Symbol largerSymbol);
}

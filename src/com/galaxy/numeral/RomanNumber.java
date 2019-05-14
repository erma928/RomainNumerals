package com.galaxy.numeral;

import com.galaxy.exceptions.NumberFormatException;

import java.util.ArrayList;

public class RomanNumber extends Number<RomanSymbol> {

    RomanNumber(String representation) throws NumberFormatException {
        this.symbolList = new ArrayList<>();
        for (char code: representation.toCharArray()) {
            RomanSymbol symbol = RomanSymbol.getByCode(String.valueOf(code));
            if (symbol==null) {
                throw new NumberFormatException("Roman symbol code not found: "+code);
            }
            symbolList.add(symbol);
        }
        this.representation = representation;
    }
}

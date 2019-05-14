package com.galaxy.numeral;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

public class GalaxySymbol implements Symbol {
    private static Map<String, Pair<GalaxySymbol, RomanSymbol>> galaxyRomanSymbolMap = new HashMap<>();

    public static void addSymbol(String code, String romanCode) {
        if (galaxyRomanSymbolMap.containsKey(code)) {
            return;
        }
        RomanSymbol symbol = RomanSymbol.getByCode(romanCode);
        if (symbol!=null) {
            GalaxySymbol galaxySymbol = new GalaxySymbol(code, symbol.getValue());
            galaxyRomanSymbolMap.put(code, new Pair<>(galaxySymbol, symbol));
        }
    }

    public static GalaxySymbol getByCode(String code) {
        if (galaxyRomanSymbolMap.containsKey(code)) {
            return galaxyRomanSymbolMap.get(code).getKey();
        }
        return null;
    }

    public static GalaxySymbol getByValue(int value) {
        for (Pair<GalaxySymbol, RomanSymbol> pair: galaxyRomanSymbolMap.values()) {
            if (pair.getKey().getValue()==value) {
                return pair.getKey();
            }
        }
        return null;
    }

    private String code;
    private int value;

    GalaxySymbol(String code, int value) {
        this.code = code;
        this.value = value;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public boolean isRepeatable() {
        if (galaxyRomanSymbolMap.containsKey(code)) {
            RomanSymbol symbol = galaxyRomanSymbolMap.get(code).getValue();
            return symbol.isRepeatable();
        }

        return false;
    }

    @Override
    public boolean isDeductibleFrom(Symbol largerSymbol) {
        if (galaxyRomanSymbolMap.containsKey(code) && galaxyRomanSymbolMap.containsKey(largerSymbol.getCode())) {
            RomanSymbol symbol = galaxyRomanSymbolMap.get(code).getValue();
            RomanSymbol largerRsymbol = galaxyRomanSymbolMap.get(largerSymbol.getCode()).getValue();

            return symbol.isDeductibleFrom(largerRsymbol);
        }

        return false;
    }

}

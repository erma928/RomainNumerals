package com.galaxy.numeral;

import com.galaxy.exceptions.GalaxyMetalNotFoundException;
import com.galaxy.exceptions.GalaxyNumeralFormatException;
import com.galaxy.exceptions.RomanNumeralFormatException;
import com.galaxy.numeral.GalaxyRomanNumeral;
import com.galaxy.numeral.RomanSymbol;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 支持星系符号与罗马数的转化逻辑
 * Created by fengjimin on 1/5/19.
 */
public class GalaxyTranslator {
    private Map<String, RomanSymbol> galaxySymbols;
    private Map<String, BigDecimal> metalPrices;
    private GalaxyRomanNumeral galaxyRomanNumeral;

    public GalaxyTranslator() {
        galaxySymbols = new HashMap<>();
        metalPrices = new HashMap<>();
        galaxyRomanNumeral = new GalaxyRomanNumeral();
    }

    public void addSymbol(String galSymName, char romanCode) {
        RomanSymbol symbol = RomanSymbol.getByCode(romanCode);
        if (symbol!=null) {
            galaxySymbols.put(galSymName, symbol);
        }
    }

    public void addPrice(String galaxyNumeralsMetal, BigDecimal amount) throws GalaxyNumeralFormatException, RomanNumeralFormatException {
        String[] numeralMetals = galaxyNumeralsMetal.split("\\s");
        String metalName = numeralMetals[numeralMetals.length-1];
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<numeralMetals.length-1; i++) {
            String gSymbol = numeralMetals[i];
            RomanSymbol rSymbol = galaxySymbols.get(gSymbol);
            if (rSymbol==null) {
                throw new GalaxyNumeralFormatException("Galaxy symbol not found: "+gSymbol);
            }
            sb.append(rSymbol.getCode());
        }
        int value = 1;
        if (sb.length()>0) {
            value = galaxyRomanNumeral.parseFromNumeral(sb.toString());
        }

        metalPrices.put(metalName, amount.divide(BigDecimal.valueOf(value)));
    }

    public int getValue(String galaxyNumerals) throws GalaxyNumeralFormatException, RomanNumeralFormatException {
        StringBuilder sb = new StringBuilder();
        String[] gSymbols = galaxyNumerals.split("\\s");
        for (String gSymbol: gSymbols) {
            RomanSymbol rSymbol = galaxySymbols.get(gSymbol);
            if (rSymbol==null) {
                throw new GalaxyNumeralFormatException("Galaxy symbol not found: "+gSymbol);
            }
            sb.append(rSymbol.getCode());
        }

        return galaxyRomanNumeral.parseFromNumeral(sb.toString());
    }

    public BigDecimal getAmount(String galaxyNumeralsMetal) throws GalaxyNumeralFormatException, RomanNumeralFormatException, GalaxyMetalNotFoundException {
        String[] numeralMetals = galaxyNumeralsMetal.split("\\s");
        String metal = numeralMetals[numeralMetals.length-1];
        BigDecimal price = metalPrices.get(metal);
        if (price==null) {
            throw new GalaxyMetalNotFoundException("Metal not found: "+metal);
        }

        StringBuilder sb = new StringBuilder();
        for (int i=0; i<numeralMetals.length-1; i++) {
            String gSymbol = numeralMetals[i];
            RomanSymbol rSymbol = galaxySymbols.get(gSymbol);
            if (rSymbol==null) {
                throw new GalaxyNumeralFormatException("Galaxy symbol not found: "+gSymbol);
            }
            sb.append(rSymbol.getCode());
        }
        int value = galaxyRomanNumeral.parseFromNumeral(sb.toString());

        return price.multiply(BigDecimal.valueOf(value));
    }
}

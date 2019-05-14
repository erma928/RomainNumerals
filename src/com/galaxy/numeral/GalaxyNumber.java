package com.galaxy.numeral;

import java.util.ArrayList;
import com.galaxy.exceptions.NumberFormatException;

/**
 * galaxy number class
 */
public class GalaxyNumber extends Number<GalaxySymbol> {

    GalaxyNumber(String representation) throws NumberFormatException {
        this.symbolList = new ArrayList<GalaxySymbol>();
        String[] codes = representation.split("\\s");
        for (String code: codes) {
            GalaxySymbol symbol = GalaxySymbol.getByCode(code);
            if (symbol==null) {
                throw new NumberFormatException("Galaxy symbol code not found: "+code);
            }
            symbolList.add(symbol);
        }
        this.representation = representation;
    }

}

package com.galaxy.numeral;

import com.galaxy.exceptions.NumberFormatException;
import com.galaxy.numeral.parser.NumberParser;

public class GalaxyNumberBuilder extends NumberBuilder<GalaxyNumber> {

    GalaxyNumberBuilder(NumberParser numberParser) {
        super(numberParser);
    }

    @Override
    public GalaxyNumber buildFromRepresentation(String representation) throws NumberFormatException {
        GalaxyNumber number = new GalaxyNumber(representation);
        int value = this.numberParser.parseNumber(number);
        number.setValue(value);

        return number;
    }

}

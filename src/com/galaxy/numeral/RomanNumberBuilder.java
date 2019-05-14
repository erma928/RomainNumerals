package com.galaxy.numeral;

import com.galaxy.exceptions.NumberFormatException;
import com.galaxy.numeral.parser.NumberParser;

public class RomanNumberBuilder extends NumberBuilder<RomanNumber> {

    RomanNumberBuilder(NumberParser numberParser) {
        super(numberParser);
    }

    @Override
    public RomanNumber buildFromRepresentation(String representation) throws NumberFormatException {
        RomanNumber number = new RomanNumber(representation);
        int value = this.numberParser.parseNumber(number);
        number.setValue(value);

        return number;
    }

}

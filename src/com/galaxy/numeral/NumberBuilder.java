package com.galaxy.numeral;

import com.galaxy.exceptions.NumberFormatException;
import com.galaxy.numeral.parser.NumberParser;

/**
 * builder for numerals; all creations of numerals are through here
 * @param <T>
 */
public abstract class NumberBuilder<T extends Number> {

    protected NumberParser numberParser;

    NumberBuilder(NumberParser numberParser) {
        this.numberParser = numberParser;
    }

    public abstract T buildFromRepresentation(String representation) throws NumberFormatException;

}

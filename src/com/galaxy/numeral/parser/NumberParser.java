package com.galaxy.numeral.parser;

import com.galaxy.exceptions.NumberFormatException;
import com.galaxy.numeral.Number;
import com.galaxy.numeral.parser.rule.ParseRule;

public interface NumberParser<T extends Number> {

    void addParseRule(ParseRule rule);

    /**
     * parse number to arabic (integer)
     * @param number
     * @return
     * @throws NumberFormatException
     */
    int parseNumber(T number) throws NumberFormatException;

}

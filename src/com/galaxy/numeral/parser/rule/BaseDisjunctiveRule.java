package com.galaxy.numeral.parser.rule;

import com.galaxy.exceptions.NumberFormatException;
import com.galaxy.numeral.Symbol;

import java.util.Stack;

public abstract class BaseDisjunctiveRule<T extends Symbol> implements ParseRule<T> {

    @Override
    public boolean isDisjunctive() {
        return true;
    }

    @Override
    public boolean check(Stack<Integer> parsedValues, T symbol) throws NumberFormatException {
        if (parsedValues.empty()) { // first element
            parsedValues.push(symbol.getValue());
            return true;
        } else {
            return checkParsed(parsedValues, symbol);
        }
    }

    abstract boolean checkParsed(Stack<Integer> parsedValues, T symbol) throws NumberFormatException;
}

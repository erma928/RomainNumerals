package com.galaxy.numeral.parser.rule;

import com.galaxy.exceptions.NumberFormatException;
import com.galaxy.numeral.Symbol;

import java.util.Stack;

/**
 * value in descending order
 * @param <T>
 */
public class DescendingRule<T extends Symbol> extends BaseDisjunctiveRule<T> {

    @Override
    public boolean checkParsed(Stack<Integer> parsedValues, T symbol) throws NumberFormatException {
        int last = parsedValues.peek();
        if (last > symbol.getValue()) { // previous is larger than current
            parsedValues.push(symbol.getValue());
            return true;
        }
        return false;
    }

}

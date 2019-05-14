package com.galaxy.numeral.parser.rule;

import com.galaxy.exceptions.NumberFormatException;
import com.galaxy.numeral.Symbol;

import java.util.Stack;

/**
 * rules for parsing the numerals
 * @param <T>
 */
public interface ParseRule<T extends Symbol> {

    /**
     * whether the rule is disjunctive or conjunctive (i.e. logic 'or' or 'and')
     * @return
     */
    boolean isDisjunctive();

    /**
     * enforce symbol rules while parsing the number
     * @param parsedValues
     * @param symbol
     * @return false when the rule is not applicable, true if it is applicable
     * @throws NumberFormatException
     */
    boolean check(Stack<Integer> parsedValues, T symbol) throws NumberFormatException;
}

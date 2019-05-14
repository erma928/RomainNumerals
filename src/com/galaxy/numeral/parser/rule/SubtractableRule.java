package com.galaxy.numeral.parser.rule;

import com.galaxy.exceptions.NumberFormatException;
import com.galaxy.numeral.RomanSymbol;
import com.galaxy.numeral.Symbol;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Stack;

/**
 * subtractable symbol
 * @param <T>
 */
public class SubtractableRule<T extends Symbol> extends BaseDisjunctiveRule<T> {

    @Override
    public boolean checkParsed(Stack<Integer> parsedValues, T symbol) throws NumberFormatException {
        int last = parsedValues.peek();
        if (last < symbol.getValue()) { // previous is smaller
            Method method;
            try {
                method = symbol.getClass().getMethod("getByValue", int.class);
                Symbol lastSymbol = (Symbol) method.invoke(null, last);
                if (lastSymbol==null) { // break rule: descending order
                    throw new NumberFormatException("Not in descending order: "+symbol.getCode());
                }
                if (lastSymbol.isDeductibleFrom(symbol)) {
                    parsedValues.pop();
                    parsedValues.push(symbol.getValue()-last);
                } else {
                    throw new NumberFormatException(lastSymbol+" not deductible from: "+symbol.getCode());
                }
                return true;
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}

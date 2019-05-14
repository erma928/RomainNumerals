package com.galaxy.numeral.parser.rule;

import com.galaxy.exceptions.NumberFormatException;
import com.galaxy.numeral.Symbol;

import java.util.Stack;

/**
 * repeatable symbol
 * @param <T>
 */
public class RepeatableRule<T extends Symbol> extends BaseDisjunctiveRule<T> {
    static final int MAX_REPEAT_TIMES = 3;

    @Override
    public boolean checkParsed(Stack<Integer> parsedValues, T symbol) throws NumberFormatException {
        int last = parsedValues.peek();
        if (last == symbol.getValue()) { // previous is equal to current
            if (!symbol.isRepeatable()) { // not repeatable
                throw new NumberFormatException("Not repeatable: "+symbol.getCode());
            }
            int currStackSize = parsedValues.size();
            if (currStackSize >= MAX_REPEAT_TIMES) {
                boolean isRepeatedMax = true;
                for (int i=2; i<=MAX_REPEAT_TIMES; i++) {
                    int preVal = parsedValues.elementAt(currStackSize - i);
                    if (preVal!=last) {
                        isRepeatedMax = false;
                        break;
                    }
                }
                if (isRepeatedMax) {
                    throw new NumberFormatException("Repeated exceeded permitted times: " + symbol.getCode());
                }
            }
            parsedValues.push(symbol.getValue());
            return true;
        }

        return false;

    }

}

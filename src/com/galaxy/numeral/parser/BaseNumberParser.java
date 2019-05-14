package com.galaxy.numeral.parser;

import com.galaxy.exceptions.NumberFormatException;
import com.galaxy.numeral.Number;
import com.galaxy.numeral.Symbol;
import com.galaxy.numeral.parser.rule.ParseRule;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * algorithm for parsing numbers
 */
public class BaseNumberParser<T extends Number> implements NumberParser<T> {
    List<ParseRule> parseRuleList;

    public BaseNumberParser() {
        parseRuleList = new ArrayList<>();
    }

    @Override
    public void addParseRule(ParseRule rule) {
        parseRuleList.add(rule);
    }

    /**
     * convert the roman numeral to integer
     * @param number
     * @return
     * @throws NumberFormatException
     */
    @Override
    public int parseNumber(T number) throws NumberFormatException {
        Stack<Integer> parsedValues = new Stack<>();
        for (Object currCode: number.getSymbolList()) {
            Symbol currSymbol = (Symbol)currCode;
            for (ParseRule rule: parseRuleList) {
                boolean applied = rule.check(parsedValues, currSymbol);
                if (rule.isDisjunctive()) { // 'or' logic
                    if (applied) {
                        break;
                    } else {
                        continue;
                    }
                } else { // 'and' logic
                    if (applied) {
                        continue;
                    } else {
                        break;
                    }
                }
            }
        }

        int result = 0;
        for (Integer val: parsedValues) {
            result += val;
        }

        return result;
    }

}

package com.galaxy.numeral;

import com.galaxy.exceptions.RomanNumeralFormatException;

import java.util.Stack;

/**
 * 支持罗马数与阿拉伯数字的转化逻辑
 * Created by fengjimin on 1/5/19.
 */
public class GalaxyRomanNumeral implements RomanNumeral {

    /**
     * 解析罗马数，返回对应的阿拉伯数值
     * @param numeral
     * @return
     * @throws RomanNumeralFormatException
     */
    @Override
    public int parseFromNumeral(String numeral) throws RomanNumeralFormatException {
        if (numeral==null || numeral.isEmpty()) {
            throw new RomanNumeralFormatException("Empty numeral not supported!");
        }
        Stack<Integer> parsedValues = new Stack<>();
        for (char currCode: numeral.toCharArray()) {
            RomanSymbol currSymbol = RomanSymbol.getByCode(currCode);
            if (currSymbol==null) {
                throw new RomanNumeralFormatException("Code not found: "+currCode);
            }
            if (parsedValues.empty()) {
                parsedValues.push(currSymbol.getValue());
            } else {
                int last = parsedValues.peek();
                if (last == currSymbol.getValue()) { // previous is equal to current
                    if (!currSymbol.isRepeatable()) { // not repeatable
                        throw new RomanNumeralFormatException("Not repeatable: "+currCode);
                    }
                    int currStackSize = parsedValues.size();
                    if (currStackSize>2) {
                        int secondLast = parsedValues.elementAt(currStackSize - 2);
                        int thirdLast = parsedValues.elementAt(currStackSize - 3);
                        if (secondLast == last && thirdLast == secondLast) {
                            // repeat more than 3 times
                            throw new RomanNumeralFormatException("Repeated more than 3 times: " + currCode);
                        }
                    }
                    parsedValues.push(currSymbol.getValue());
                } else if (last < currSymbol.getValue()) { // previous is smaller
                    RomanSymbol lastSymbol = RomanSymbol.getByValue(last);
                    if (lastSymbol==null) { // break rule: descending order
                        throw new RomanNumeralFormatException("Not in descending order: "+currSymbol);
                    }
                    if (lastSymbol.isDeductibleFrom(currSymbol)) {
                        parsedValues.pop();
                        parsedValues.push(currSymbol.getValue()-last);
                    } else {
                        throw new RomanNumeralFormatException(lastSymbol+" not deductible from: "+currSymbol);
                    }
                } else { // previous value is larger
                    parsedValues.push(currSymbol.getValue());
                }
            }
        }

        int result = 0;
        for (Integer val: parsedValues) {
            result += val;
        }

        return result;
    }

    public static void main(String[]args) {
        GalaxyRomanNumeral grn = new GalaxyRomanNumeral();

        try {
            int val = grn.parseFromNumeral("MCMXLIV");
            System.out.println("MCMXLIV value: "+val);
            int val1 = grn.parseFromNumeral("MMVI");
            System.out.println("MMVI value: "+val1);
            int val2 = grn.parseFromNumeral("MCMIVI");
            System.out.println("MCMIVI value: "+val2);
            int val3 = grn.parseFromNumeral("MMMVMCVI");
            System.out.println("MMMVMCVI value: "+val3);
        } catch (RomanNumeralFormatException e) {
            System.err.println("RomanNumeralFormatException: "+e.getMessage());
        }

    }
}

package com.galaxy.notes;

import com.galaxy.exceptions.NumberFormatException;
import com.galaxy.exceptions.MetalNotFoundException;
import com.galaxy.numeral.*;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * notes parsing and question answering
 *
 */
public class GalaxyNotesParser {

    static final Pattern SYMBOL_PATTERN = Pattern.compile("^(\\w+) is ([I|V|X|L|C|D|M])$", Pattern.CASE_INSENSITIVE);
    static final Pattern CREDIT_PATTERN = Pattern.compile("^(?<numeralMetal>(\\w+\\s)+)is (?<amount>\\d+) Credits$", Pattern.CASE_INSENSITIVE);
    static final Pattern HOW_MUCH_PATTERN = Pattern.compile("^how much \\w+ (?<numeral>(\\w+\\s)*(\\w+\\s?))\\?$", Pattern.CASE_INSENSITIVE);
    static final Pattern HOW_MANY_PATTERN = Pattern.compile("^how many Credits \\w+ (?<numeralMetal>(\\w+\\s)*(\\w+\\s?))\\?$", Pattern.CASE_INSENSITIVE);
    static final String GROUP_AMOUNT= "amount";
    static final String GROUP_NUMERAL = "numeral";
    static final String GROUP_NUMERAL_METAL= "numeralMetal";
    static final String UNKNOWN_QUESTION_ANSWER = "I have no idea what you are talking about";

    private NumberBuilder galaxyNumber;
    private MetalPriceCalculator calculator;

    public GalaxyNotesParser() {
        galaxyNumber = NumberBuilderFactory.getNumberBuilderByNumeralType(NumeralType.GALAXY);
        calculator = new MetalPriceCalculator(galaxyNumber);
    }

    /**
     * 该行笔记是否为信息相关：符号或credits相关信息
     * @param notesLine
     * @return
     */
    public boolean isInfo(String notesLine) {
        return (SYMBOL_PATTERN.matcher(notesLine).matches())?true:
                CREDIT_PATTERN.matcher(notesLine).matches();
    }

    /**
     * 该行笔记是否为问题
     * @param notesLine
     * @return
     */
    public boolean isHowQuestion(String notesLine) {
        return (HOW_MUCH_PATTERN.matcher(notesLine).matches())?true:
                HOW_MANY_PATTERN.matcher(notesLine).matches();
    }

    /**
     * 处理该行笔记
     * @param notesLine
     * @return 如果笔记为信息相关，则
     * @throws NumberFormatException
     */
    public String processNotesLine(String notesLine) throws NumberFormatException {
        if (isInfo(notesLine)) {
            parseSymbol(notesLine).parseCredit(notesLine);
            return null;
        } else if (isHowQuestion(notesLine)) {
            return answerHowQuestion(notesLine);
        }
        return null;
    }

    /**
     * 回答how类型问题
     * @param notesLine
     * @return
     */
    public String answerHowQuestion(String notesLine) {
        try {
            Matcher matcher = HOW_MUCH_PATTERN.matcher(notesLine);
            if (matcher.find()) {
                String numeral = matcher.group(GROUP_NUMERAL);
                return numeral + "is " + galaxyNumber.buildFromRepresentation(numeral.trim()).getValue();
            } else {
                Matcher matcher2 = HOW_MANY_PATTERN.matcher(notesLine);
                if (matcher2.find()) {
                    String numeralMetal = matcher2.group(GROUP_NUMERAL_METAL);
                    BigDecimal amount = calculator.getAmount(numeralMetal);
                    return numeralMetal + "is " + amount.intValue() + " Credits";
                }
            }
        } catch (NumberFormatException e) {
        } catch (MetalNotFoundException e) {
        }

        return UNKNOWN_QUESTION_ANSWER;
    }

    public GalaxyNotesParser parseSymbol(String notesLine) {
        Matcher matcher = SYMBOL_PATTERN.matcher(notesLine);
        if (matcher.find()) {
            String gSymbol = matcher.group(1);
            String rSymbol = matcher.group(2);
            GalaxySymbol.addSymbol(gSymbol, rSymbol);
        }
        return this;
    }

    public GalaxyNotesParser parseCredit(String notesLine) throws NumberFormatException {
        Matcher matcher = CREDIT_PATTERN.matcher(notesLine);
        if (matcher.find()) {
            String numeralMetal = matcher.group(GROUP_NUMERAL_METAL);
            String amount = matcher.group(GROUP_AMOUNT);
            calculator.addPrice(numeralMetal, new BigDecimal(amount));
        }

        return this;
    }

}

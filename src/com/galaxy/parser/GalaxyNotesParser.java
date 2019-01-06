package com.galaxy.parser;

import com.galaxy.exceptions.GalaxyMetalNotFoundException;
import com.galaxy.exceptions.GalaxyNumeralFormatException;
import com.galaxy.exceptions.RomanNumeralFormatException;
import com.galaxy.numeral.GalaxyTranslator;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 笔记解析与问题回答
 *
 * Created by fengjimin on 1/5/19.
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

    private GalaxyTranslator galaxyTranslator;

    public GalaxyNotesParser() {
        galaxyTranslator = new GalaxyTranslator();
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
     * @throws GalaxyNumeralFormatException
     * @throws RomanNumeralFormatException
     */
    public String processNotesLine(String notesLine) throws GalaxyNumeralFormatException, RomanNumeralFormatException {
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
                return numeral + "is " + galaxyTranslator.getValue(numeral.trim());
            } else {
                Matcher matcher2 = HOW_MANY_PATTERN.matcher(notesLine);
                if (matcher2.find()) {
                    String numeralMetal = matcher2.group(GROUP_NUMERAL_METAL);
                    BigDecimal amount = galaxyTranslator.getAmount(numeralMetal);
                    return numeralMetal + "is " + amount.intValue() + " Credits";
                }
            }
        } catch (GalaxyNumeralFormatException e) {
        } catch (RomanNumeralFormatException e) {
        } catch (GalaxyMetalNotFoundException e) {
        }

        return UNKNOWN_QUESTION_ANSWER;
    }

    public GalaxyNotesParser parseSymbol(String notesLine) {
        Matcher matcher = SYMBOL_PATTERN.matcher(notesLine);
        if (matcher.find()) {
            String gSymbol = matcher.group(1);
            String rSymbol = matcher.group(2);
            galaxyTranslator.addSymbol(gSymbol, rSymbol.charAt(0));
        }
        return this;
    }

    public GalaxyNotesParser parseCredit(String notesLine) throws GalaxyNumeralFormatException, RomanNumeralFormatException {
        Matcher matcher = CREDIT_PATTERN.matcher(notesLine);
        if (matcher.find()) {
            String numeralMetal = matcher.group(GROUP_NUMERAL_METAL);
            String amount = matcher.group(GROUP_AMOUNT);
            galaxyTranslator.addPrice(numeralMetal, new BigDecimal(amount));
        }

        return this;
    }

}

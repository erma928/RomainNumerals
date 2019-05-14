package com.galaxy.numeral;

import com.galaxy.numeral.parser.BaseNumberParser;
import com.galaxy.numeral.parser.NumberParser;
import com.galaxy.numeral.parser.rule.DescendingRule;
import com.galaxy.numeral.parser.rule.RepeatableRule;
import com.galaxy.numeral.parser.rule.SubtractableRule;

/**
 * factory for creating number builders
 */
public class NumberBuilderFactory {
    public static NumberBuilder getNumberBuilderByNumeralType(NumeralType type) {
        NumberParser parser;
        switch (type) {
            case ROMAN:
                parser = new BaseNumberParser<RomanNumber>();
                parser.addParseRule(new DescendingRule<RomanSymbol>());
                parser.addParseRule(new RepeatableRule<RomanSymbol>());
                parser.addParseRule(new SubtractableRule<RomanSymbol>());
                return new RomanNumberBuilder(parser);
            case GALAXY:
                parser = new BaseNumberParser<GalaxyNumber>();
                parser.addParseRule(new DescendingRule<GalaxySymbol>());
                parser.addParseRule(new RepeatableRule<GalaxySymbol>());
                parser.addParseRule(new SubtractableRule<GalaxySymbol>());
                return new GalaxyNumberBuilder(parser);
            default:
                return null;
        }
    }
}

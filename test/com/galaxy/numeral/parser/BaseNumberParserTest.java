package com.galaxy.numeral.parser;

import com.galaxy.numeral.*;
import com.galaxy.numeral.parser.rule.DescendingRule;
import com.galaxy.numeral.parser.rule.RepeatableRule;
import com.galaxy.numeral.parser.rule.SubtractableRule;
import junit.framework.TestCase;
import org.junit.Before;

/**
 */
public class BaseNumberParserTest extends TestCase {

    BaseNumberParser<GalaxyNumber> parser;
    NumberBuilder<GalaxyNumber> galaxyNumberBuilder;

    @Before
    protected void setUp() throws Exception {
        super.setUp();
        GalaxySymbol.addSymbol("glob", "I");
        GalaxySymbol.addSymbol("prok", "V");
        GalaxySymbol.addSymbol("pish", "X");
        GalaxySymbol.addSymbol("tegj", "L");
        GalaxySymbol.addSymbol("tegj", "L");
        parser =  new BaseNumberParser<>();

        parser.addParseRule(new DescendingRule<GalaxySymbol>());
        parser.addParseRule(new RepeatableRule<GalaxySymbol>());
        parser.addParseRule(new SubtractableRule<GalaxySymbol>());

        galaxyNumberBuilder = NumberBuilderFactory.getNumberBuilderByNumeralType(NumeralType.GALAXY);

    }

    @org.junit.Test
    public void testGetValue() throws Exception {
        GalaxyNumber testNum = galaxyNumberBuilder.buildFromRepresentation("pish tegj glob glob");

        int number = parser.parseNumber(testNum);
        assertEquals(number, 42);
    }

}
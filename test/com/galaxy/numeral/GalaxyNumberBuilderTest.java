package com.galaxy.numeral;

import junit.framework.TestCase;
import org.junit.Before;

/**
 */
public class GalaxyNumberBuilderTest extends TestCase {

    NumberBuilder<GalaxyNumber> galaxyNumberBuilder;

    @Before
    protected void setUp() throws Exception {
        super.setUp();
        GalaxySymbol.addSymbol("glob", "I");
        GalaxySymbol.addSymbol("prok", "V");
        GalaxySymbol.addSymbol("pish", "X");
        GalaxySymbol.addSymbol("tegj", "L");
        GalaxySymbol.addSymbol("tegj", "L");
        galaxyNumberBuilder = NumberBuilderFactory.getNumberBuilderByNumeralType(NumeralType.GALAXY);

    }

    @org.junit.Test
    public void testGetValue() throws Exception {
        GalaxyNumber number = galaxyNumberBuilder.buildFromRepresentation("pish tegj glob glob");
        assertEquals(number.getValue(), 42);
    }

}
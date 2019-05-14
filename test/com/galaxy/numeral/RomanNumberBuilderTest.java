package com.galaxy.numeral;

import junit.framework.TestCase;
import org.junit.Before;

/**
 * Created by fengjimin on 1/6/19.
 */
public class RomanNumberBuilderTest extends TestCase {

    NumberBuilder<RomanNumber> galaxyRomanNumeral;

    @Before
    protected void setUp() throws Exception {
        super.setUp();
        galaxyRomanNumeral = NumberBuilderFactory.getNumberBuilderByNumeralType(NumeralType.ROMAN);
    }

    @org.junit.Test
    public void testParseFromNumeral() throws Exception {
        RomanNumber val = galaxyRomanNumeral.buildFromRepresentation("MCMXLIV");
        assertEquals(val.getValue(), 1944);
        RomanNumber val1 = galaxyRomanNumeral.buildFromRepresentation("MMVI");
        assertEquals(val1.getValue(), 2006);
        RomanNumber val2 = galaxyRomanNumeral.buildFromRepresentation("MCMIVI");
        assertEquals(val2.getValue(), 1905);
    }
}
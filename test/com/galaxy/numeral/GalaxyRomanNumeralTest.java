package com.galaxy.numeral;

import junit.framework.TestCase;
import org.junit.Before;

import static org.junit.Assert.*;

/**
 * Created by fengjimin on 1/6/19.
 */
public class GalaxyRomanNumeralTest extends TestCase {

    GalaxyRomanNumeral galaxyRomanNumeral;

    @Before
    protected void setUp() throws Exception {
        super.setUp();
        galaxyRomanNumeral = new GalaxyRomanNumeral();
    }

    @org.junit.Test
    public void testParseFromNumeral() throws Exception {
        int val = galaxyRomanNumeral.parseFromNumeral("MCMXLIV");
        assertEquals(val, 2006);
        int val1 = galaxyRomanNumeral.parseFromNumeral("MMVI");
        assertEquals(val1, 2006);
        int val2 = galaxyRomanNumeral.parseFromNumeral("MCMIVI");
        assertEquals(val2, 1905);
    }
}
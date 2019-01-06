package com.galaxy.numeral;

import junit.framework.TestCase;
import org.junit.Before;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by fengjimin on 1/6/19.
 */
public class GalaxyTranslatorTest extends TestCase {

    GalaxyTranslator galaxyTranslator;

    @Before
    protected void setUp() throws Exception {
        super.setUp();
        galaxyTranslator = new GalaxyTranslator();
        galaxyTranslator.addSymbol("glob", 'I');
        galaxyTranslator.addSymbol("prok", 'V');
        galaxyTranslator.addSymbol("pish", 'X');
        galaxyTranslator.addSymbol("tegj", 'L');
        galaxyTranslator.addSymbol("tegj", 'L');
        galaxyTranslator.addPrice("glob glob Silver", BigDecimal.valueOf(34));

    }

    @org.junit.Test
    public void testGetValue() throws Exception {
        int val = galaxyTranslator.getValue("pish tegj glob glob");
        assertEquals(val, 42);
    }

    @org.junit.Test
    public void testGetAmount() throws Exception {
        BigDecimal val = galaxyTranslator.getAmount("glob prok Silver");
        assertEquals(val, BigDecimal.valueOf(68));
    }
}
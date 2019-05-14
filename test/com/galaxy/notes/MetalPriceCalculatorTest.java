package com.galaxy.notes;

import com.galaxy.numeral.*;
import junit.framework.TestCase;
import org.junit.Before;

import java.math.BigDecimal;

/**
 */
public class MetalPriceCalculatorTest extends TestCase {

    MetalPriceCalculator metalPriceCalculator;

    @Before
    protected void setUp() throws Exception {
        super.setUp();
        GalaxySymbol.addSymbol("glob", "I");
        GalaxySymbol.addSymbol("prok", "V");
        GalaxySymbol.addSymbol("pish", "X");
        GalaxySymbol.addSymbol("tegj", "L");
        GalaxySymbol.addSymbol("tegj", "L");
        NumberBuilder<GalaxyNumber> galaxyNumberBuilder = NumberBuilderFactory.getNumberBuilderByNumeralType(NumeralType.GALAXY);
        metalPriceCalculator = new MetalPriceCalculator(galaxyNumberBuilder);
    }

    @org.junit.Test
    public void testAddPrice() throws Exception {
        metalPriceCalculator.addPrice("glob prok Gold", BigDecimal.valueOf(57800));
        BigDecimal amount = metalPriceCalculator.getAmount("glob prok Gold");
        assertEquals(amount.intValue(), 57800);
    }
}
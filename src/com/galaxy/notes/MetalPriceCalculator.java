package com.galaxy.notes;

import com.galaxy.exceptions.MetalNotFoundException;
import com.galaxy.exceptions.NumberFormatException;
import com.galaxy.numeral.Number;
import com.galaxy.numeral.NumberBuilder;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * record and calculate prices for metal
 */
public class MetalPriceCalculator {
    private Map<String, BigDecimal> metalPriceMap;

    private NumberBuilder galaxyNumberBuilder;

    public MetalPriceCalculator(NumberBuilder galaxyNumberBuilder) {
        metalPriceMap = new HashMap<>();
        this.galaxyNumberBuilder = galaxyNumberBuilder;
    }

    public void addPrice(String galaxyNumeralsMetal, BigDecimal amount) throws NumberFormatException {
        String[] numeralMetals = galaxyNumeralsMetal.split("\\s");
        String metalName = numeralMetals[numeralMetals.length-1];
        int lastIdx = galaxyNumeralsMetal.lastIndexOf(metalName);
        Number galaxyNumber = galaxyNumberBuilder.buildFromRepresentation(galaxyNumeralsMetal.substring(0, lastIdx));

        metalPriceMap.put(metalName, amount.divide(BigDecimal.valueOf(galaxyNumber.getValue())));
    }

    public BigDecimal getAmount(String galaxyNumeralsMetal) throws NumberFormatException, MetalNotFoundException {
        String[] numeralMetals = galaxyNumeralsMetal.split("\\s");
        String metalName = numeralMetals[numeralMetals.length-1];
        BigDecimal price = metalPriceMap.get(metalName);
        if (price==null) {
            throw new MetalNotFoundException("Metal not found: "+metalName);
        }

        int lastIdx = galaxyNumeralsMetal.lastIndexOf(metalName);
        Number galaxyNumber = galaxyNumberBuilder.buildFromRepresentation(galaxyNumeralsMetal.substring(0, lastIdx));

        return price.multiply(BigDecimal.valueOf(galaxyNumber.getValue()));
    }
}

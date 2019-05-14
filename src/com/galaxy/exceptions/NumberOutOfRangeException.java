package com.galaxy.exceptions;

/**
 * integer value out of range, i.e. not representable by roman numeral
 */
public class NumberOutOfRangeException extends Exception {

    public NumberOutOfRangeException(String message) {
        super(message);
    }

}

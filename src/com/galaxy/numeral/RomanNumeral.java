package com.galaxy.numeral;

import com.galaxy.exceptions.RomanNumeralFormatException;

/**
 * Created by fengjimin on 1/5/19.
 */
public interface RomanNumeral {

    /**
     * 由罗马数字转化为阿拉伯数字
     * @param numeral
     * @return
     * @throws RomanNumeralFormatException
     */
    int parseFromNumeral(String numeral) throws RomanNumeralFormatException;
}

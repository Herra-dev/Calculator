package com.herra.back;

import com.herra.exception._DivisionByZeroException;
import com.herra.exception._SyntaxErrorException;

public class DecimalCalculator extends AbstractDecimalCalculator{
/**
 * <h3>Construct new DecimalCalculator</h3>
 * 
 * @param user_input
 */
    public DecimalCalculator(String user_input) {
        System.out.println("Construction of a DecimalCalculator");
        this._input = user_input;
        try {
            testParenthesis();
            try {
                testUserInput();
            } catch (_DivisionByZeroException e) {
                e.printStackTrace();
            }
            arrangeUserInput();
        } catch (_SyntaxErrorException e) {
            e.printStackTrace();
        }
    }
}
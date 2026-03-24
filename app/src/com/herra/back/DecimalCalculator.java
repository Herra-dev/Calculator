package com.herra.back;

public class DecimalCalculator extends AbstractDecimalCalculator{
/**
 * <h3>Construct new DecimalCalculator</h3>
 * 
 * @param user_input
 */
    public DecimalCalculator(String user_input) {
        System.out.println("Construction of one DecimalCalculator");
        this._input = user_input;
    }
}
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
            if(this.getAuthorization())
                testUserInput();
            if(this.getAuthorization())
                testParenthesis();
        } catch (_SyntaxErrorException | _DivisionByZeroException e) {
            e.printStackTrace();
        }

        this.operatorMultiply(this.getInputList());
    }

//===================================================================


}
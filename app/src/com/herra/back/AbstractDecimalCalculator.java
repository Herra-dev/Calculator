package com.herra.back;

import com.herra.exception._SyntaxErrorException;

public class AbstractDecimalCalculator extends AbstractCalculator {
//===================================================================

/**
 * <h2>testUserInput()</h2>{@link com.herra.back.AbstractCalculator#testUserInput() } <p>
 * 
 * if user input:<p>
 * - starts with {@code *} or {@code /}, <p>
 * - ends with {@code +} or {@code -} or {@code *} or {@code /}<p>
 * - contains a succession of arithmetic operator invalid for example: {@code +*}, {@code -*}, {@code **}, {@code /*}, {@code -)} ...
 * 
 * {@code raise}an {@link com.herra.exception._SyntaxErrorException}
 * 
 * @throws _SyntaxErrorException when user input is incorrect
 * 
 * @author Heriniaina {@link https://github.com/Herra-dev}
 */
    protected void testUserInput() throws _SyntaxErrorException{
        //if the user input contains nothing or white space only, remove those last and quit function
        if (this._input.isBlank()) { this._input.strip(); return; }
        
        if (this._input.startsWith("*") || this._input.startsWith("/") ||
                this._input.endsWith("-") || this._input.endsWith("+") ||
                    this._input.endsWith("*") || this._input.endsWith("/")) {
            _outPut = "SYNTAX ERROR";
            _canProcess = false;
            throw new _SyntaxErrorException("Input can't starts or ends with an arithmetic operator");
        }

        if (_input.contains("-*") || _input.contains("-/") ||            
            _input.contains("+*") || _input.contains("+/") ||
                _input.contains("**") || _input.contains("*/") ||
                _input.contains("//") || _input.contains("/*") ||
                    _input.contains("+)") || _input.contains("-)") ||
                    _input.contains("*)") || _input.contains("/)") ||
                        _input.contains("(/") || _input.contains("(*")) { 
            _outPut = "SYNTAX ERROR";
            this._canProcess = false;
            throw new _SyntaxErrorException("Verify your syntax");
        }

        this._canProcess = true;
    }

//===================================================================

/**
 * <h2>arrangeUserInput()</h2> {@link com.herra.back.AbstractCalculator#arrangeUserInput()}<p>
 * 
 * Arrange user input: (Simplify arithmetic sign)<p>
 * examples:<p>
 * - {@code 8++++++++9} --> {@code 8+9}<p>
 * - {@code 9-----6} --> {@code 9-6}
 * - {@code 9+-+-+6} --> {@code 9+6}
 * - {@code 9*+1}    --> {@code 9*1}
 * 
 * <p>
 * Like we do in math class:
 * - {@code +} * {@code +} = {@code +}
 * - {@code -} * {@code -} = {@code +}
 * - {@code +} * {@code -} = {@code -}
 * - {@code -} * {@code +} = {@code -}
 * 
 * 
 * @author Heriniaina {@link https://github.com/Herra-dev}
 */
    protected void arrangeUserInput() {  
        do {
            _input = _input.replaceAll("[+]{2}", "+");                      // ++           --> +
            _input = _input.replaceAll("[-]{2}", "+");                      // --           --> +
            _input = _input.replaceAll("[+]{1}[-]{1}|[-]{1}[+]{1}", "-");   // +- or -+     --> -
            _input = _input.replaceAll("[*]{1}[+]{1}", "*");                // *+           --> *
            _input = _input.replaceAll("[/]{1}[+]{1}", "/");                // /+           --> /
        }while (_input.contains("--") || _input.contains("++") ||
                    _input.contains("-+") || _input.contains("+-"));

        System.out.println(_input);
        this._canProcess = true;
        
    }    

//===================================================================

/**
 * Do calcul
 * 
 * @return the result of the actual calcul
 */
    protected String calcul() {
        return "";
    }

//===================================================================

}
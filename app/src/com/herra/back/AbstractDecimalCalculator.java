package com.herra.back;

import com.herra.exception._DivisionByZeroException;
import com.herra.exception._SyntaxErrorException;

public class AbstractDecimalCalculator extends AbstractCalculator {

/**
 * <h2>testUserInput()</h2>{@link com.herra.back.AbstractCalculator#testUserInput() } <p>
 * 
 * if user input:<p>
 * - starts with {@code *} or {@code /} or {@code %}, <p>
 * - ends with {@code +} or {@code -} or {@code *} or {@code /} or {@code %}<p>
 * - contains a succession of arithmetic operator invalid for example: {@code +*}, {@code -*}, {@code %%}, {@code **}, {@code %*}, {@code /*}, {@code -)} ...
 * 
 * {@code raise}an {@link com.herra.exception._SyntaxErrorException}
 * 
 * @throws _SyntaxErrorException when user input is incorrect
 * @throws _DivisionByZeroException when user input contains division by zero
 * 
 * @author Heriniaina {@link https://github.com/Herra-dev}
 * 
 */
    @Override protected void testUserInput() throws _SyntaxErrorException, _DivisionByZeroException {
        // if the user input contains nothing or white space only, remove those last and quit function
        if (this._input.isBlank()) { this._input.strip(); _outPut = "0"; return; }
        
    //-----------------------------------------------------------------------------------

        // if the user input starts with '*', '/', '%' or ends with '+', '-', '*', '/', '%', raise a _SyntaxErrorException
        if (this.getInput().startsWith("*") || this.getInput().startsWith("/") || this.getInput().startsWith("%") ||
                this.getInput().endsWith("-") || this.getInput().endsWith("+") || this.getInput().endsWith("%") ||
                    this.getInput().endsWith("*") || this.getInput().endsWith("/")) {
            _outPut = "SYNTAX ERROR";
            _canProcess = false;
            throw new _SyntaxErrorException("Input can't starts or ends with an arithmetic operator");
        }

    //-----------------------------------------------------------------------------------

        if (_input.contains("-*") || _input.contains("-/") || _input.contains("-%") ||          
            _input.contains("+*") || _input.contains("+/") || _input.contains("+%") ||
                _input.contains("**") || _input.contains("*/") || _input.contains("*%") ||
                _input.contains("//") || _input.contains("/*") || _input.contains("/%") ||
                _input.contains("%/") || _input.contains("%*") || _input.contains("%%") ||
                    _input.contains("+)") || _input.contains("-)") || _input.contains("-%") ||
                    _input.contains("*)") || _input.contains("/)") || _input.contains("/%") ||
                        _input.contains("(/") || _input.contains("(*") || _input.contains("(%")) { 
            _outPut = "SYNTAX ERROR";
            this._canProcess = false;
            throw new _SyntaxErrorException("Verify your syntax");
        }

    //-----------------------------------------------------------------------------------

        //if the user input contains a division by zero, raise a _DivisionByZeroException
        int from_index = 0;
        int index_of_zero = 0;
        String str = new String();
        
        while((index_of_zero = this.getInput().indexOf('0', from_index)) != -1) {
            if(this.getInput().charAt(index_of_zero-1) == '/' || this.getInput().charAt(index_of_zero-1) == '%') {
                
                str += (this.getInput().charAt(index_of_zero-1) == '/') ? "/0" : "%0";

                char character = this.getInput().charAt(++index_of_zero);

                while(character != '(' && character != ')' && character != '+' && character != '-' &&
                        character != '*' && character != '/' && index_of_zero < this.getInput().length()) {
                    character = this.getInput().charAt(index_of_zero++);
                    str += character;
                    for(int i = 1; i < 9; i++) {
                        if(character == (char)i) 
                            break;
                        System.out.println("i = " + i + ", character = " + character);
                    }
                        
                }

                // if(!str.matches("[/0]{1}0*[1-9]+")) {
                //     throw new _DivisionByZeroException("Division by zero");
                // } else {
                //     System.out.println("Hello Hery only");
                // }

            }
            from_index = index_of_zero+1; // new search starts at index + 1, where a zero was found
        }
        System.out.println("str = " + str);

        this._canProcess = true;
    }

//===================================================================

/**
 * <h2>arrangeUserInput()</h2> {@link com.herra.back.AbstractDecimalCalculator#arrangeUserInput()}<p>
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
 * <table >
 * <tr>
 * <td>heri</td>
 * <td>niaina</td>
 * </tr>
 * <tr>
 * <td>niaina</td>
 * <td>heri</td>
 * <tr>
 * </table>
 * 
 * @author Heriniaina {@link https://github.com/Herra-dev}
 */
    @Override protected void arrangeUserInput() {  
        do {
            _input = _input.replaceAll("[+]{2}", "+");                      // ++           --> +
            _input = _input.replaceAll("[-]{2}", "+");                      // --           --> +
            _input = _input.replaceAll("[+]{1}[-]{1}|[-]{1}[+]{1}", "-");   // +- or -+     --> -
            _input = _input.replaceAll("[*]{1}[+]{1}", "*");                // *+           --> *
            _input = _input.replaceAll("[/]{1}[+]{1}", "/");                // /+           --> /
            _input = _input.replaceAll("[%]{1}[+]{1}", "%");                // %+           --> %
        }while (_input.contains("--") || _input.contains("++") ||
                    _input.contains("-+") || _input.contains("+-"));

        System.out.println(_input);
        this._canProcess = true;
        
    }    

//===================================================================

    // protected void 

/**
 * Do calcul
 * 
 * @return the result of the actual calcul
 */
    @Override protected String calcul() {
        return "";
    }

//===================================================================

}
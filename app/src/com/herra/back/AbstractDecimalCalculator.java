package com.herra.back;

import java.util.List;

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
 * {@code raise}a {@link com.herra.exception._SyntaxErrorException}
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
            this.setOutPut("SYNTAX ERROR");
            _canProcess = false;
            throw new _SyntaxErrorException("Input can't starts or ends with an arithmetic operator");
        }

    //-----------------------------------------------------------------------------------

        // if the user input contains a succession of arithmetic operator invalid, raise a _SyntaxErrorException
        if (_input.contains("-*") || _input.contains("-/") || _input.contains("-%") ||          
            _input.contains("+*") || _input.contains("+/") || _input.contains("+%") ||
                _input.contains("**") || _input.contains("*/") || _input.contains("*%") ||
                _input.contains("//") || _input.contains("/*") || _input.contains("/%") ||
                _input.contains("%/") || _input.contains("%*") || _input.contains("%%") ||
                    _input.contains("+)") || _input.contains("-)") || _input.contains("-%") ||
                    _input.contains("*)") || _input.contains("/)") || _input.contains("/%") ||
                        _input.contains("(/") || _input.contains("(*") || _input.contains("(%")) { 
            this.setOutPut("SYNTAX ERROR");
            this._canProcess = false;
            throw new _SyntaxErrorException("Verify your syntax");
        }

    //-----------------------------------------------------------------------------------

        // if no error was encountered during previous operations, then arrange user input after that
        // put each element from user input into a list
        if(getAuthorization())
            this.arrangeUserInput();
        if(getAuthorization())
            this.setListInput(separeInput());
    
    //-----------------------------------------------------------------------------------

        //if the user input contains a division by zero, raise a _DivisionByZeroException
        MyLinkedList<String> my_list = new MyLinkedList<String>();
        my_list.addAll(this.getInputList());
        
        int index_found = 0;
        int from_index = 0;
        // check each element at index, index of an operator "/", + 1, and raise a _DivisionByZeroException if this element is equals zero
        while((index_found = my_list.indexOf("/", from_index)) != -1) {
            from_index = index_found+1;
            
            // use a regular expression to check element after division sign
            if(my_list.get(index_found+1).matches("0+")) {
                this.setOutPut("SYNTAX ERROR");
                this._canProcess = false;
                throw new _DivisionByZeroException("Division by zero");
            }
        }

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

/**
 * Do calcul
 * 
 * @return the result of the actual calcul
 */
    @Override protected String calcul(List<String> list) {
        // Quit function if during test of user input an error of syntax was occured
        if(!this.getAuthorization()) return "0";

        while(list.contains("/") || list.contains("*") || list.contains("+") || list.contains("-")) {
            list = operatorDivide(list);
            list = operatorMultiply(list);
            list = operatorPlus(list);
            list = operatorMinus(list);
        }

        return "";
    }

//===================================================================

/**
 * <h3>operatorPlus</h3>
 * {@link com.herra.back.AbstractDecimalCalculator#operatorPlus(List)}<p>
 * 
 * This method add two numbers ({@code double}), these number is taken from parameter{@code list}, example:<p>
 * <ol>
 * <li>the parameter {@code list} contains: "5", "+", "96", "-", "680" </li>
 * <li>the function search for index of the plus {@code +} sign</li>, stock this index in a variable {@code int}
 * <li>put the element at index -> index of {@code plus} signe {@code -} 1, into variable {@code Double}, The value stocked in this last is considered as {@code first number}</li>
 * <li>put the element at index -> index of {@code plus} signe {@code +} 1, into variable {@code Double}, The value stocked in this last is considered as {@code second number}<p>
 * {@code Notes}: elements are taken from the list as a {@code String} values, so they are parsed into {@code Double}</li>
 * <li>add {@code first number} to {@code second number}</li>
 * <li>removes all elements between index of{@code +}sign - 1 (<i>-2</i> if first number is a negative value) and{@code +}sign +1</li>
 * </uo><p>
 * 
 * 
 * @param list {@code List<String>}
 * 
 * @return {@code List<String>}
 */
    @Override protected List<String> operatorPlus(List<String> list) {
        // if the list doesn't contains an operator '+' quit function
        if(!list.contains("+")) return list;

        int plus_sign_index = list.indexOf("+");
        double first_Number = java.lang.Double.parseDouble(list.get(plus_sign_index-1));
        double second_number = java.lang.Double.parseDouble(list.get(plus_sign_index+1));

        int first  = plus_sign_index-1;
        int last = plus_sign_index+1;


        {
            // Case where plus sign index is equals to two(2), first number is probably a negative number wherefore
        //      necessity to check which element is stocked at index zero(0), if this last is equals to '-',
        //      changes first number to negative
        //----------------- EXAMPLE ------------------
        // input_value      =  - 5 + 12
        //                     ^   ^
        // index_input      =  0 1 2 3
        }

        if(plus_sign_index == 2 && list.get(0).equals("-")) {
            first_Number = -first_Number;
            first = 0;
        }
        // removes all elements between index first and last (both included)
        for(int i = last; i >= first; i--) 
            list.remove(i);

        list.add(first, java.lang.Double.toString(first_Number + second_number));

        return list;
        
    }

//===================================================================

    @Override protected List<String> operatorMinus(List<String> list) {
        // if the list doesn't contains an operator '-' quit function
        if(!list.contains("-")) return list;

        int minus_sign_index = list.indexOf("-");
        if(minus_sign_index == 0) {
            list.remove(0);
            
            list.set(0, "-" + list.get(0));
        }

        // System.out.println("index of minus sign = " + minus_sign_index);

        for(String str: list)
            System.out.println(str);
        System.out.println();


        return list;
    }

//===================================================================

    @Override protected List<String> operatorMultiply(List<String> list) {
        return list;
    }
    
//===================================================================
    
    @Override protected List<String> operatorDivide(List<String> list) {
        return list;
    }

}
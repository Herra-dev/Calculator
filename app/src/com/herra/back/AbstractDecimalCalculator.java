package com.herra.back;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
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

        this._canProcess = true;
        
    }    

//===================================================================

/**
 * Do calcul
 * 
 * @return the result of the actual calcul
 */
    @Override protected String calcul(List<String> list) {
        // Quit function if during the test of user input an error of syntax was occured
        if(!this.getAuthorization()) return "0";

        // If the first element of the list is a plus '+' sign, removes it
        if(list.get(0).equals("+")) list.remove(0);

    //-------------------------------------------------------------------------------
        List<String> list_copy = new LinkedList<String>();
        String output = new String();

        if(list.contains("(")) {
            int open_parenthesis_index = 0;
            int closed_parenthesis_index = list.size()-1;
            int another_one_open = 0;
            
            open_parenthesis_index = list.indexOf("(");

            if(open_parenthesis_index > 0)
                if(list.get(open_parenthesis_index-1).matches("[0-9]"))

            for(int i = open_parenthesis_index+1; i < list.size(); i++) {
                if(list.get(i).equals("(")) ++another_one_open;
                else if(list.get(i).equals(")") && another_one_open == 0) closed_parenthesis_index = i;
            }

            int a = 0;
            //copy all elements between open and closed parenthesis (both excluded) into another list
            for(int i = open_parenthesis_index+1; i < closed_parenthesis_index; i++) list_copy.add(a++, list.get(i));
            //remove all elements between open and closed parenthesis (both included) from list
            for(int i = closed_parenthesis_index; i >= open_parenthesis_index; i--) list.remove(i);

            System.out.println("open parenthesis = " + open_parenthesis_index);
            System.out.println("closed parenthesis = " + closed_parenthesis_index);


        //     // 9+9(6-3)(1-99)
        //     // 6-3 -> 3
        //     // 9+9*3(1-99)
        //     // 1-99 -> -98
        //     // 9+9*3*-98

        }

    //-------------------------------------------------------------------------------

        if(list_copy.isEmpty())
            list_copy.addAll(list);

        while(list_copy.contains("/") || list_copy.contains("*") || list_copy.contains("+") || list_copy.contains("-")) {
            list_copy = operatorDivide(list_copy);
            list_copy = operatorMultiply(list_copy);
            list_copy = operatorPlus(list_copy);
            list_copy = operatorMinus(list_copy);
        }

        output = list_copy.get(0);
        System.out.println("output : " + output);

        return output;
    }

//===================================================================

/**
 * <h3>operatorPlus</h3>
 * 
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
 * </ol><p>
 * 
 * 
 * @param list {@code List<String>}
 * 
 * @see com.herra.back.AbstractDecimalCalculator#operatorMinus(List)
 * @see com.herra.back.AbstractDecimalCalculator#operatorMultiply(List)
 * @see com.herra.back.AbstractDecimalCalculator#operatorDivide(List)
 * 
 * @return {@code List<String>}
 * 
 * @author Heriniaina {@link https://github.com/Herra-dev}
 */
    @Override protected List<String> operatorPlus(List<String> list) {
        // if the list doesn't contains an operator '+' quit function
        if(!list.contains("+")) return list;

        int plus_sign_index = list.indexOf("+");

        int first_number_index = plus_sign_index-1;
        int second_number_index = plus_sign_index+1;

        BigDecimal first_number = BigDecimal.valueOf(java.lang.Double.parseDouble(list.get(first_number_index)));
        BigDecimal second_number = BigDecimal.valueOf(java.lang.Double.parseDouble(list.get(second_number_index)));

        int first_to_remove  = first_number_index;
        int last_to_remove = second_number_index;

        // first number is a negative number if element before it is a subraction sign
        if(first_number_index > 0 && list.get(first_number_index-1).equals("-")){
            --first_to_remove;
            first_number.negate();
        }

        System.out.println("first to remove = " + first_to_remove);
        System.out.println("second to remove = " + last_to_remove);

        for(int i = last_to_remove; i >= first_to_remove; i--)
            lis

        return list;
        
    }

//===================================================================

/**
 * <h3>operatorMinus</h3>
 * 
 * {@link com.herra.back.AbstractDecimalCalculator#operatorMinus(List)}<p>
 * 
 * This method subtract two number.<p>
 * first and second numbers are taken from the parameter {@code list} in the same way function {@link com.herra.back.AbstractDecimalCalculator#operatorPlus(List)} take them.
 * 
 * @param list {@code List<String>}
 * 
 * @see com.herra.back.AbstractDecimalCalculator#operatorPlus(List)
 * @see com.herra.back.AbstractDecimalCalculator#operatorMultiply(List)
 * @see com.herra.back.AbstractDecimalCalculator#operatorDivide(List)
 * 
 * @return {@code List<String>}
 * 
 * @author Heriniaina {@link https://github.com/Herra-dev}
 */
    @Override protected List<String> operatorMinus(List<String> list) {
        // if the list doesn't contains an operator '-' quit function
        if(!list.contains("-")) return list;

    //--------------------------------------------------------------------------------

        int minus_sign_index = list.indexOf("-");
        // if the index of minus sign is equals to 0, remove it and set next element into negative
        if(minus_sign_index == 0) {
            list.remove(0);
            list.set(0, "-" + list.get(0));
            return list;
        }

    //--------------------------------------------------------------------------------

        BigDecimal first_number = BigDecimal.valueOf(java.lang.Double.parseDouble(list.get(minus_sign_index-1)));
        BigDecimal second_number = BigDecimal.valueOf(java.lang.Double.parseDouble(list.get(minus_sign_index+1)));

        // removes elements between minus_sign_index-1 and minus_sign_index+1
        for(int i = minus_sign_index+1; i >= minus_sign_index-1; i--)
            list.remove(i);

        list.add(minus_sign_index-1, first_number.subtract(second_number).toString());

        System.out.println("substract");

        return list;
    }

//===================================================================

/**
 * <h3>operatorMultiply</h3>
 * 
 * {@link com.herra.back.AbstractDecimalCalculator#operatorMultiply(List)}<p>
 * 
 * This method multiply two number.<p>
 * first and second numbers are taken from the parameter {@code list} in the same way function {@link com.herra.back.AbstractDecimalCalculator#operatorPlus(List)} take them.
 *
 * @author Heriniaina {@link https://github.com/Herra-dev}
 * 
 * @see com.herra.back.AbstractDecimalCalculator#operatorPlus(List)
 * @see com.herra.back.AbstractDecimalCalculator#operatorMinus(List)
 * @see com.herra.back.AbstractDecimalCalculator#operatorDivide(List)
 */
    @Override protected List<String> operatorMultiply(List<String> list) {
        // if the list doesn't contains an operator '*' quit function
        if(!list.contains("*")) return list;

    //--------------------------------------------------------------------------------

        int multiplication_sign_index = list.indexOf("*");

        BigDecimal first_number = BigDecimal.valueOf(java.lang.Double.parseDouble(list.get(multiplication_sign_index-1)));
        BigDecimal second_number = BigDecimal.valueOf((list.get(multiplication_sign_index+1).equals("-")) 
            ? java.lang.Double.parseDouble(list.get(multiplication_sign_index+2)) 
            : java.lang.Double.parseDouble(list.get(multiplication_sign_index+1)));

        int first = multiplication_sign_index-1;
        int last = multiplication_sign_index+1;

        // check if first number is a negative number
        if(multiplication_sign_index > 1 && list.get(multiplication_sign_index-2).equals("-")) {
            first_number = first_number.negate();
            first = multiplication_sign_index-2;
        }
        if(list.get(multiplication_sign_index+1).equals("-")) {
            second_number = second_number.negate();
            last = multiplication_sign_index+2;
        }

        // removes elements between first and last
        for(int i = last; i >= first; i--)
            list.remove(i);

        list.add(first, first_number.multiply(second_number).toString());

        return list;
    }
    
//===================================================================
    
    @Override protected List<String> operatorDivide(List<String> list) {
        // if the list doesn't contains an operator '/' quit function
        if(!list.contains("/")) return list;

        //--------------------------------------------------------------------------------

        int division_sign_index = list.indexOf("/");

        BigDecimal first_number = BigDecimal.valueOf(java.lang.Double.parseDouble(list.get(division_sign_index-1)));
        BigDecimal second_number = BigDecimal.valueOf((list.get(division_sign_index+1).equals("-")) 
            ? java.lang.Double.parseDouble(list.get(division_sign_index+2)) 
            : java.lang.Double.parseDouble(list.get(division_sign_index+1)));

        int first = division_sign_index-1;
        int last = division_sign_index+1;

        // check if first number is a negative number
        if(division_sign_index > 1 && list.get(division_sign_index-2).equals("-")) {
            first_number = first_number.negate();
            first = division_sign_index-2;
        }
        if(list.get(division_sign_index+1).equals("-")) {
            second_number = second_number.negate();
            last = division_sign_index+2;
        }

        // removes elements between first and last
        for(int i = last; i >= first; i--)
            list.remove(i);

        // To prevent case of an infinite result as 10/3=0.33333333333... 
        list.add(first, first_number.divide(second_number, 10, RoundingMode.UP).toString());

        return list;
    }

}
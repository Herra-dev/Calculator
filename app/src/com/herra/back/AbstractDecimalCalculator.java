package com.herra.back;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;

import com.herra.exception._DivisionByZeroException;
import com.herra.exception._SyntaxErrorException;

public class AbstractDecimalCalculator extends AbstractCalculator {


/**
 * <h2>countOccurrence</h2>{@link com.herra.back.AbstractCalculator#countCharacter(String, char)}<p> 
 * returns the occurrence of{@code ch} found in {@code str}
 * 
 * @param str {@code String} where search
 * @param ch {@code char} to search 
 * @return {@code int}
 * 
 * @author Heriniaina {@link https://github.com/Herra-dev}
 */
    public static int countOccurrence(String str, char ch) {
        int occ = 0;
        for(int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == ch) ++occ;
        }

        return occ;
    }

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
                        _input.contains("(/") || _input.contains("(*") || _input.contains("(%") ||
                            _input.contains("()")) { 
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
            // _input = _input.replaceAll("[.]{1}0", _input);
        }while (_input.contains("--") || _input.contains("++") ||
                    _input.contains("-+") || _input.contains("+-"));

        int opened_parenthesis_nbr = countOccurrence(_input, '(');
        int closed_parenthesis_nbr = countOccurrence(_input, ')');

        System.out.println("opened = " + opened_parenthesis_nbr + ", closed = " + closed_parenthesis_nbr);
        
        System.out.println("input before = " + _input);
        if(opened_parenthesis_nbr > closed_parenthesis_nbr) {
            int nbr_of_parenthesis_to_add  = opened_parenthesis_nbr-closed_parenthesis_nbr;
            System.out.println("to add = " + nbr_of_parenthesis_to_add);
            for(int i = 0; i < nbr_of_parenthesis_to_add; i++)
                _input += ")";
        }
        System.out.println("input after= " + _input);

        this._canProcess = true;
        
    }    



//===================================================================

    public String calcul() {
        return this.calcul(this.getInputList());
    }

/**
 * Do calcul
 * 
 * @return the result of the actual calcul
 */
    @Override public String calcul(List<String> list) {
        // Quit function if during the test of user input an error of syntax was occured
        if(!this.getAuthorization()) return "0";

        if(list.isEmpty()) return new String();

        // If the first element of the list is a plus '+' sign, removes it
        if(!list.isEmpty())
            if(list.get(0).equals("+")) list.remove(0);

    //-------------------------------------------------------------------------------
        List<String> list_copy = new LinkedList<String>();
        String output = new String();
        boolean add_multiplication_sign = false;

        if(list.contains("(")) {
            int opened_parenthesis_index = 0;
            int closed_parenthesis_index = list.size()-1;
            int another_one_opened = 0;
            
            opened_parenthesis_index = list.indexOf("(");

            if(opened_parenthesis_index > 0)
                // if element before the opened parenthesis is a natural number or decimal number or a closed parenthesis
                if(list.get(opened_parenthesis_index-1).matches("[0-9]++|[0-9]++\\p{Punct}[0-9]++") || list.get(opened_parenthesis_index-1).equals(")")) 
                    add_multiplication_sign = true;

            for(int i = opened_parenthesis_index+1; i < list.size(); i++) {
                if(list.get(i).equals("(")) ++another_one_opened;
                else if(list.get(i).equals(")")) {
                    if(another_one_opened > 0) --another_one_opened;
                    else closed_parenthesis_index = i;
                }
            }

            // if(closed_parenthesis_index+1 <= list.size()) {
            //     if(list.get(closed_parenthesis_index+1).matches("[0-9]++|[0-9]++[.]{1}[0-9]|[.]{1}[0-9]"))
            //         list.add(closed_parenthesis_index+1, "*");
            // }

            if(!(closed_parenthesis_index == opened_parenthesis_index+1)) {
                int a = 0;
                //copy all elements between opened and closed parenthesis (both excluded) into another list
                for(int i = opened_parenthesis_index+1; i < closed_parenthesis_index; i++) list_copy.add(a++, list.get(i));
                //remove all elements between opened and closed parenthesis (both included) from list
                for(int i = closed_parenthesis_index; i >= opened_parenthesis_index; i--) list.remove(i);

                String temp_string = this.calcul(list_copy);

                if(add_multiplication_sign) {
                    list.add(opened_parenthesis_index, "*");
                    list.add(opened_parenthesis_index+1, temp_string);
                } else
                    list.add(opened_parenthesis_index, temp_string);
                
            } else {
                //remove all elements between opened and closed parenthesis (both included) from list
                for(int i = closed_parenthesis_index; i >= opened_parenthesis_index; i--) list.remove(i);
            }
            

            for(String str: list)
                    System.out.print(str);
                System.out.println();

                System.out.println("in parenthesis");

            
        }

        if(list.contains("("))
            this.calcul(list);

    //-------------------------------------------------------------------------------

        // if(list_copy.isEmpty())
        //     list_copy.addAll(list);

        while(list.contains("/") || list.contains("%") || list.contains("*") || list.contains("+") || list.contains("-")) {
            while(list.contains("/")) list = operatorDivide(list);
            while(list.contains("%")) list = operatorModulo(list);
            while(list.contains("*")) list = operatorMultiply(list);
            while(list.contains("-")) list = operatorMinus(list);
            while(list.contains("+")) list = operatorPlus(list);
        }

        output = list.get(0);
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
 * * If no plus{@code +}sign was found, returns the parameter {@code list}<p>
 * * If no number was found before plus{@code +}sign, 
 * 
 * @param list {@code List<String>}
 * 
 * @see com.herra.back.AbstractDecimalCalculator#operatorMinus(List)
 * @see com.herra.back.AbstractDecimalCalculator#operatorMultiply(List)
 * @see com.herra.back.AbstractDecimalCalculator#operatorDivide(List)
 * @see com.herra.back.AbstractDecimalCalculator#operatorModulo(List)
 * 
 * @return {@code List<String>}
 * 
 * @author Heriniaina {@link https://github.com/Herra-dev}
 */
    @Override protected List<String> operatorPlus(List<String> list) {

        // if no authorization was not accorded(caused by user input syntax) returns a new empty LinkedList
        if(!this.getAuthorization()) return new LinkedList<String>();

        // if the list doesn't contains an operator '+' quit function
        if(!list.contains("+")) return list;

        //-----------------------------------------------------------------------------------------------------------
        // if plus sign is the first element of the list, remove it and returns the list
        int plus_sign_index = list.indexOf("+");
        if(plus_sign_index == 0) {
            list.remove(plus_sign_index);
            return list;
        }

        //-----------------------------------------------------------------------------------------------------------
        //
        int first_number_index = plus_sign_index-1;
        int second_number_index = plus_sign_index+1;
        int first_to_remove  = first_number_index;
        int last_to_remove = second_number_index;

        BigDecimal first_number = BigDecimal.valueOf(java.lang.Double.parseDouble(list.get(first_number_index)));
        BigDecimal second_number = BigDecimal.valueOf(java.lang.Double.parseDouble(list.get(second_number_index)));

        
        // first number is a negative number if element before it is a subraction sign
        if(first_number_index > 0 && list.get(first_number_index-1).equals("-")){
            --first_to_remove;
            first_number = first_number.negate();
        }

        // removes all used elements: first number(and his sign if it is a minus sign), plus sign and second number
        for(int i = last_to_remove; i >= first_to_remove; i--) list.remove(i);

        BigDecimal result = first_number.add(second_number);

        // if first to remove is superior to zero(0), probably there is a number before it,
        // so separe result's operator and number
        if(first_to_remove > 0) {
            if(result.equals(result.abs())) {   // result is a positive number
                list.add(first_to_remove, "+");
                list.add(first_to_remove+1, result.abs().toString());
            }
            else {                              // result is a negative number
                list.add(first_to_remove, "-");
                list.add(first_to_remove+1, result.abs().toString());
            }

        } else {
            list.add(first_to_remove, result.toString());
        }

        // System.out.println("add");

        // for(String str: list)
        //     System.out.print(str);
        // System.out.println();

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
 * @see com.herra.back.AbstractDecimalCalculator#operatorModulo(List)
 * 
 * @return {@code List<String>}
 * 
 * @author Heriniaina {@link https://github.com/Herra-dev}
 */
    @Override protected List<String> operatorMinus(List<String> list) {

        // if no authorization was not accorded(caused by user input syntax) returns a new empty LinkedList
        if(!this.getAuthorization()) return new LinkedList<String>();

        // if the list doesn't contains an operator '-' quit function
        if(!list.contains("-")) return list;

    //--------------------------------------------------------------------------------

        int minus_sign_index = list.indexOf("-");
        // if the index of minus sign is equals to 0, remove it and set next element into negative
        if(minus_sign_index == 0) {
            list.remove(minus_sign_index);
            list.set(minus_sign_index, "-" + list.get(0));
            return list;
        }

    //--------------------------------------------------------------------------------

        int first_number_index = minus_sign_index-1;
        int second_number_index = minus_sign_index+1;
        int first_to_remove = first_number_index;
        int last_to_remove = second_number_index;

        BigDecimal first_number = BigDecimal.valueOf(java.lang.Double.parseDouble(list.get(minus_sign_index-1)));
        BigDecimal second_number = BigDecimal.valueOf(java.lang.Double.parseDouble(list.get(minus_sign_index+1)));

        // first number is a negative number if element before it is a subraction sign
        if(first_number_index > 0 && list.get(first_number_index-1).equals("+")){
            --first_to_remove;
        }

        // removes all used elements: first number(and his sign if it is an minus sign), minus sign and second number
        for(int i = last_to_remove; i >= first_to_remove; i--) list.remove(i);

        BigDecimal result = first_number.subtract(second_number);

        if(first_to_remove > 0) {
            if(result.equals(result.abs())) {   // result is a positive number
                list.add(first_to_remove, "+");
                list.add(first_to_remove+1, result.abs().toString());
            }
            else {                              // result is a negative number
                list.add(first_to_remove, "-");
                list.add(first_to_remove+1, result.abs().toString());
            }

        } else {
            list.add(first_to_remove, result.toString());
        }

        // System.out.println("substract");

        // for(String str: list)
        //     System.out.print(str);
        // System.out.println();

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
 * @see com.herra.back.AbstractDecimalCalculator#operatorModulo(List)
 */
    @Override protected List<String> operatorMultiply(List<String> list) {

        // if no authorization was not accorded(caused by user input syntax) returns a new empty LinkedList
        if(!this.getAuthorization()) return new LinkedList<String>();

        // if the list doesn't contains an operator '*' quit function
        if(!list.contains("*")) return list;

    //--------------------------------------------------------------------------------

        int multiplication_sign_index = list.indexOf("*");
        int first_number_index = multiplication_sign_index-1;
        int second_number_index = multiplication_sign_index+1;
        int first_to_remove = first_number_index;
        int last_to_remove = second_number_index;


        BigDecimal first_number = BigDecimal.valueOf(java.lang.Double.parseDouble(list.get(first_number_index)));
        BigDecimal second_number = BigDecimal.valueOf((list.get(multiplication_sign_index+1).equals("-")) 
            ? java.lang.Double.parseDouble(list.get(second_number_index+1)) 
            : java.lang.Double.parseDouble(list.get(second_number_index)));

        // if first number's index is superior to 0, 
        if(first_number_index > 0){
            if(list.get(first_number_index-1).equals("-")) {
                first_number = first_number.negate();
                --first_to_remove;
            }
            else if(list.get(first_number_index-1).equals("+")) --first_to_remove;
        }
        // if element coming after multiplication sign is a subraction sign, negate second number 
        if(list.get(multiplication_sign_index+1).equals("-")) {
            second_number = second_number.negate();
            ++last_to_remove;
        }

        // removes all used elements: first number(and his sign if it is an multiplication sign), minus sign and second number
        for(int i = last_to_remove; i >= first_to_remove; i--) list.remove(i);

        BigDecimal result = first_number.multiply(second_number);

        if(first_to_remove > 0) {
            if(result.equals(result.abs())) {   // result is a positive number
                list.add(first_to_remove, "+");
                list.add(first_to_remove+1, result.abs().toString());
            }
            else {                              // result is a negative number
                list.add(first_to_remove, "-");
                list.add(first_to_remove+1, result.abs().toString());
            }

        } else {
            list.add(first_to_remove, result.toString());
        }

        // System.out.println("Multiply");

        // for(String str: list)
        //     System.out.print(str);
        // System.out.println();

        return list;
    }
    
//===================================================================
    
/**
 * <h3>operatorDivide</h3>
 * 
 * {@link com.herra.back.AbstractDecimalCalculator#operatorDivide(List)}<p>
 * 
 * This method divide two number.<p>
 * first and second numbers are taken from the parameter {@code list} in the same way function {@link com.herra.back.AbstractDecimalCalculator#operatorPlus(List)} take them.
 *
 * @author Heriniaina {@link https://github.com/Herra-dev}
 * 
 * @see com.herra.back.AbstractDecimalCalculator#operatorPlus(List)
 * @see com.herra.back.AbstractDecimalCalculator#operatorMinus(List)
 * @see com.herra.back.AbstractDecimalCalculator#operatorMultiply(List)
 * @see com.herra.back.AbstractDecimalCalculator#operatorModulo(List)
 */
    @Override protected List<String> operatorDivide(List<String> list) {

        // if no authorization was not accorded(caused by user input syntax) returns a new empty LinkedList
        if(!this.getAuthorization()) return new LinkedList<String>();

        // if the list doesn't contains an operator '/' quit function
        if(!list.contains("/")) return list;

        //--------------------------------------------------------------------------------

        int division_sign_index = list.indexOf("/");
        int first_number_index = division_sign_index-1;
        int second_number_index = division_sign_index+1;
        int first_to_remove = first_number_index;
        int last_to_remove = second_number_index;

        BigDecimal first_number = BigDecimal.valueOf(java.lang.Double.parseDouble(list.get(division_sign_index-1)));
        BigDecimal second_number = BigDecimal.valueOf((list.get(division_sign_index+1).equals("-")) 
            ? java.lang.Double.parseDouble(list.get(division_sign_index+2)) 
            : java.lang.Double.parseDouble(list.get(division_sign_index+1)));

        // if first number's index is superior to 0, 
        if(first_number_index > 0){
            if(list.get(first_number_index-1).equals("-")) {
                first_number = first_number.negate();
                --first_to_remove;
            }
            else if(list.get(first_number_index-1).equals("+")) --first_to_remove;
        }
        // if element coming after multiplication sign is a subraction sign, negate second number 
        if(list.get(division_sign_index+1).equals("-")) {
            second_number = second_number.negate();
            ++last_to_remove;
        }

        // removes all used elements: first number(and his sign if it is an division sign), minus sign and second number
        for(int i = last_to_remove; i >= first_to_remove; i--) list.remove(i);

        BigDecimal result = first_number.divide(second_number, 5, RoundingMode.HALF_UP);

        if(first_to_remove > 0) {
            if(result.equals(result.abs())) {   // result is a positive number
                if(list.get(first_to_remove-1).equals("*") || list.get(first_to_remove-1).equals("/")) {
                    list.add(first_to_remove, result.abs().toString());
                    System.out.println("not a number");
                } else {
                    list.add(first_to_remove, result.abs().toString());
                    list.add(first_to_remove, "+");
                    System.out.println("a number");
                }
            }
            else {                              // result is a negative number
                list.add(first_to_remove, "-");
                list.add(first_to_remove+1, result.abs().toString());
            }

        } else {
            list.add(first_to_remove, result.toString());
        }

        // System.out.println("Divide");

        // for(String str: list)
        //     System.out.print(str);
        // System.out.println();

        return list;
    }

//===================================================================

/**
 * <h3>operatorModulo</h3>
 * 
 * {@link com.herra.back.AbstractDecimalCalculator#operatorModulo(List)}<p>
 * 
 * This method find the modulo between two numbers.<p>
 * first and second numbers are taken from the parameter {@code list} in the same way function {@link com.herra.back.AbstractDecimalCalculator#operatorPlus(List)} take them.
 *
 * @author Heriniaina {@link https://github.com/Herra-dev}
 * 
 * @see com.herra.back.AbstractDecimalCalculator#operatorPlus(List)
 * @see com.herra.back.AbstractDecimalCalculator#operatorMinus(List)
 * @see com.herra.back.AbstractDecimalCalculator#operatorMultiply(List)
 * @see com.herra.back.AbstractDecimalCalculator#operatorDivide(List)
 */
    @Override protected List<String> operatorModulo(List<String> list) {

        // if no authorization was not accorded(caused by user input syntax) returns a new empty LinkedList
        if(!this.getAuthorization()) return new LinkedList<String>();

        // if the list doesn't contains an operator '/' quit function
        if(!list.contains("%")) return list;

        //--------------------------------------------------------------------------------

        int modulo_sign_index = list.indexOf("%");
        System.out.println("index of % = " + modulo_sign_index);
        int first_number_index = modulo_sign_index-1;
        int second_number_index = modulo_sign_index+1;
        int first_to_remove = first_number_index;
        int last_to_remove = second_number_index;

        BigDecimal first_number = BigDecimal.valueOf(java.lang.Double.parseDouble(list.get(modulo_sign_index-1)));
        BigDecimal second_number = BigDecimal.valueOf((list.get(modulo_sign_index+1).equals("-")) 
            ? java.lang.Double.parseDouble(list.get(modulo_sign_index+2)) 
            : java.lang.Double.parseDouble(list.get(modulo_sign_index+1)));

        // if first number's index is superior to 0, 
        if(first_number_index > 0){
            if(list.get(first_number_index-1).equals("-")) {
                first_number = first_number.negate();
                --first_to_remove;
            }
            else if(list.get(first_number_index-1).equals("+")) --first_to_remove;
        }
        // if element coming after multiplication sign is a subraction sign, negate second number 
        if(list.get(modulo_sign_index+1).equals("-")) {
            second_number = second_number.negate();
            ++last_to_remove;
        }

        // removes all used elements: first number(and his sign if it is an division sign), minus sign and second number
        for(int i = last_to_remove; i >= first_to_remove; i--) list.remove(i);

        BigDecimal exactDivision = new BigDecimal((first_number.toBigInteger().divide(second_number.toBigInteger()).toString()));
        BigDecimal result = first_number.subtract(second_number.multiply(exactDivision));

        if(first_to_remove > 0) {
            if(result.equals(result.abs())) {   // result is a positive number
                if(list.get(first_to_remove-1).equals("*") || list.get(first_to_remove-1).equals("/")) {
                    list.add(first_to_remove, result.abs().toString());
                    System.out.println("not a number");
                } else {
                    list.add(first_to_remove, result.abs().toString());
                    list.add(first_to_remove, "+");
                    System.out.println("a number");
                }
            }
            else {                              // result is a negative number
                list.add(first_to_remove, "-");
                list.add(first_to_remove+1, result.abs().toString());
            }

        } else {
            list.add(first_to_remove, result.toString());
        }

        System.out.println("modulo");

        for(String str: list)
            System.out.println(str);
        System.out.println();

        return list;
    }

}
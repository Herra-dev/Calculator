package com.herra.back;

import java.util.List;
import java.util.LinkedList;

import com.herra.exception._DivisionByZeroException;
import com.herra.exception._SyntaxErrorException;
import com.interfaces.sh.Observable;
import com.interfaces.sh.Observer;

public abstract class AbstractCalculator implements Observer, Observable {
    protected String _input = new String("0");
    protected boolean _canProcess = true;
    protected String _outPut = new String("0");
    protected List<String> _input_list = new LinkedList<String>();

//===================================================================
//  ABSTRACT METHODS

    protected abstract void testUserInput() throws _SyntaxErrorException, _DivisionByZeroException;
    protected abstract void arrangeUserInput();
    protected abstract String calcul();

//===================================================================
//  GETTERS

    public String getInput()            { return this._input; }
    public String getOutput()           { return this._outPut; }
    public List<String> getInputList()  { return this._input_list; }

//===================================================================
//  SETTERS

    public void setInput(String new_input)                  { this._input = new_input; }
    public void setOutPut(String new_output)                { this._outPut = new_output; }
    public void setListInput(List<String> new_input_list)   { this._input_list = new_input_list; }

//===================================================================

/**
 * <h3>testParenthesis</h3>
 * {@link com.herra.back.AbstractCalculator#testParenthesis()}
 * 
 * @throws _SyntaxErrorException when closed parenthesis {@code >} open parenthesis
 */
    protected void testParenthesis() throws _SyntaxErrorException {
        // if user input doesn't contains '(' or ')', it's not necessary to continue
        if(!this.getInput().contains("(") && !this.getInput().contains(")")) return;

        // testing open and closed parenthesis
        int open = this.countCharacter(this.getInput(), '(');
        int closed = this.countCharacter(this.getInput(), ')');
        
    //-----------------------------------------------------------------------------------

        if(open < closed) { 
            _canProcess = false; 
            // if closed parenthesis is superior than open parenthesis throw new _SyntaxErrorException
            this.setOutPut("SYNTAX ERROR");
            throw new _SyntaxErrorException("Verify your syntax: closed parenthesis must be equals or inferior of open parenthesis"); 
        }

    //-----------------------------------------------------------------------------------

        // testing that: closed parenthesis must be allways placed after an open parenthesis
        int[] array_open = new int[open];       // create an array of int containing index of all open parenthesis
        int[] array_closed = new int[closed];   // create an array of int containing index of all closed parenthesis
        int o = 0;
        int c = 0;

        for(int i = 0; i < this.getInput().length(); i++) {
            if(this.getInput().charAt(i) == '(') array_open[o++] = i;
            if(this.getInput().charAt(i) == ')') array_closed[c++] = i;
        }

        for(int _c = 0; _c < array_closed.length; _c++) {
            for(int _o = _c; _o >= 0; _o--) {
                if (array_closed[_c] < array_open[_o]) {
                    _canProcess = false;
                    this.setOutPut( "SYNTAX ERROR");
                    throw new _SyntaxErrorException("verify your syntax: never close an anopened parenthesis");
                }
            }         
        }

    //-----------------------------------------------------------------------------------
    }

//===================================================================

/**
 * <h3>countCharacter</h3>
 * 
 * @param charSequence  {@code String}
 * @param charToFind    {@code char}
 * 
 * @return returns the number of {@code charToFind} found in {@code charSequence}
 * 
 * @author Heriniaina {@see https://github.com/Herra-dev}
 */
    public int countCharacter(String charSequence, char charToFind) {
        int _nbr = 0;

        for(int i = 0; i < charSequence.length(); i++)
            if(charSequence.charAt(i) == charToFind) _nbr++;

        return _nbr;
    }

//===================================================================

/**
 * <h3>separeInput</h3>
 * {@link com.herra.back.AbstractCalculator#separeInput()}<p>
 * 
 * This methor separe user input and push each element into a list.<p>
 * <strong>Example:</strong>
 * 
 * <strong><i>Remember that user input is taken from a label or another displayer and {@code stocked} as {@link java.lang.String}</i></strong>
 * <ol>
 *  <li>if user input is equals to = "7*9+00002-668+6", then the list will stock 9 elements wich is:
 *     <ul>
 *      <li>"7"</li>
 *      <li>"*"</li>
 *      <li>"9"</li>
 *      <li>"6"</li>
 *      <li>"00002"</li>
 *      <li>"-"</li>
 *      <li>"668"</li>
 *      <li>"+"</li>
 *      <li>"6"</li>
 *  </li>
 * </ol>
 * 
 * @return returns {@code List<String>} <i>list of String</i> 
 * 
 * @author Heriniaina {@link https://github.com/Herra-dev}
 */
    public List<String> separeInput() {
        List<String> list = new LinkedList<String>();
        String input = this.getInput();

        list.clear();
        list.add("");

        for(int i = 0; i < input.length(); i++) {
            if(input.charAt(i) == '+') { addNonNumberToListNumber(list, i); continue; }
            if(input.charAt(i) == '-') { addNonNumberToListNumber(list, i); continue; }
            if(input.charAt(i) == '*') { addNonNumberToListNumber(list, i); continue; }
            if(input.charAt(i) == '/') { addNonNumberToListNumber(list, i); continue; }
            if(input.charAt(i) == '(') { addNonNumberToListNumber(list, i); continue; }
            if(input.charAt(i) == ')') { addNonNumberToListNumber(list, i); continue; }

            list.set(list.size()-1, list.get(list.size()-1)+""+input.charAt(i));
            
            if(i+1 < input.length()) {
                char c = input.charAt(i+1);
                if(c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')') list.add("");
            }
        }

        if(list.get(list.size()-1) == (String)"") list.remove(list.size()-1);

        return list;

    }

//==========================================================================================    

    private void addNonNumberToListNumber(List<String> list, int index) {
        list.set(list.size()-1, list.get(list.size()-1)+""+this.getInput().charAt(index));
        list.add("");
    }

//===================================================================

    @Override public boolean update(Object _obj) {
        return true;
    }

//===================================================================

    @Override public boolean _addObserver(Observer _observer) {
        return true;
    }

//===================================================================
    
    @Override public boolean _updateObserver() {
        return true;
    }

//===================================================================
    
}
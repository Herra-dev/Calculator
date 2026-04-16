package com.herra.back;

import java.util.List;
import java.util.LinkedList;

import com.herra.exception._DivisionByZeroException;
import com.herra.exception._SyntaxErrorException;
import com.interfaces.sh.Observable;
import com.interfaces.sh.Observer;

public abstract class Abstract_calculator implements Observer, Observable {
    protected String _input = new String("0");
    protected boolean _canProcess = true;
    protected String _outPut = new String("0");
    protected List<String> _input_list = new LinkedList<String>();
    protected List<Observer> _observer_List = new LinkedList<Observer>();

//===================================================================
//  ABSTRACT METHODS

    protected abstract void testUserInput() throws _SyntaxErrorException, _DivisionByZeroException;
    protected abstract void arrangeUserInput();
    protected abstract String _calcul();
    protected abstract String _calcul(List<String> list);
    protected abstract List<String> operatorPlus(List<String> list);
    protected abstract List<String> operatorMinus(List<String> list);
    protected abstract List<String> operatorMultiply(List<String> list);
    protected abstract List<String> operatorDivide(List<String> list);
    protected abstract List<String> operatorModulo(List<String> list);

//===================================================================
//  GETTERS

    public String getInput()            { return this._input; }
    public boolean getAuthorization()   { return this._canProcess; }
    public String getOutput()           { return this._outPut; }
    public List<String> getInputList()  { return this._input_list; }

//===================================================================
//  SETTERS

    public void setInput(String new_input)                  { this._input = new_input; }
    public void setAuthorization(boolean new_authorization) { this._canProcess = new_authorization; }
    public void setOutPut(String new_output)                { this._outPut = new_output; }
    public void setListInput(List<String> new_input_list)   { this._input_list = new_input_list; }

//===================================================================

/**
 * <h3>testParenthesis</h3>
 * {@link com.herra.back.Abstract_calculator#testParenthesis()}<p>
 * <Strong>Test if all {@code parenthesis} is well placed in user input</strong>
 * 
 * @throws _SyntaxErrorException when closed parenthesis {@code >} opened parenthesis
 */
    protected void testParenthesis() throws _SyntaxErrorException {
        this.setAuthorization(true); // before testing, set authorization to true
        // if user input doesn't contains '(' or ')', it's not necessary to continue
        if(!this.getInput().contains("(") && !this.getInput().contains(")")) return;

        // testing opened and closed parenthesis
        int opened = this.countCharacter(this.getInput(), '(');
        int closed = this.countCharacter(this.getInput(), ')');
        
    //-----------------------------------------------------------------------------------

        if(opened < closed) { 
            _canProcess = false; 
            // if closed parenthesis is superior than opened parenthesis throw new _SyntaxErrorException
            this.setOutPut("SYNTAX ERROR");
            throw new _SyntaxErrorException("Verify your syntax: closed parenthesis must be equals or inferior of opened parenthesis"); 
        }

    //-----------------------------------------------------------------------------------

        // testing that: closed parenthesis must be allways placed after an opened parenthesis
        int[] array_opened = new int[opened];       // create an array of int containing index of all opened parenthesis
        int[] array_closed = new int[closed];   // create an array of int containing index of all closed parenthesis
        int o = 0;
        int c = 0;

        for(int i = 0; i < this.getInput().length(); i++) {
            if(this.getInput().charAt(i) == '(') array_opened[o++] = i;
            if(this.getInput().charAt(i) == ')') array_closed[c++] = i;
        }

        for(int _c = 0; _c < array_closed.length; _c++) {
            for(int _o = _c; _o >= 0; _o--) {
                if (array_closed[_c] < array_opened[_o]) {
                    _canProcess = false;
                    this.setOutPut( "SYNTAX ERROR");
                    throw new _SyntaxErrorException("verify your syntax: never close an anopeneded parenthesis");
                }
            }         
        }
    }

//===================================================================

/**
 * <h3>countCharacter</h3>
 * {@link com.herra.back.Abstract_calculator#countCharacter(String, char)}<p>
 * <i>Count occurence of parameter {@code charToFind} in {@code charSequence}</i>
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
 * {@link com.herra.back.Abstract_calculator#separeInput()}<p>
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
        if(input.isEmpty()) return new LinkedList<String>();

        list.clear();
        list.add("");

        for(int i = 0; i < input.length(); i++) {
            if(input.charAt(i) == '+') { addNonNumberToListNumber(list, i); continue; }
            if(input.charAt(i) == '-') { addNonNumberToListNumber(list, i); continue; }
            if(input.charAt(i) == '*') { addNonNumberToListNumber(list, i); continue; }
            if(input.charAt(i) == '/') { addNonNumberToListNumber(list, i); continue; }
            if(input.charAt(i) == '%') { addNonNumberToListNumber(list, i); continue; }
            if(input.charAt(i) == '(') { addNonNumberToListNumber(list, i); continue; }
            if(input.charAt(i) == ')') { addNonNumberToListNumber(list, i); continue; }
            

            list.set(list.size()-1, list.get(list.size()-1)+""+input.charAt(i));
            
            if(i+1 < input.length()) {
                char c = input.charAt(i+1);
                if(c == '+' || c == '-' || c == '*' || c == '/' || c == '%' || c == '(' || c == ')') list.add("");
            }
        }

        if(list.get(list.size()-1) == "") list.remove(list.size()-1);

        return list;

    }

//==========================================================================================    

    private void addNonNumberToListNumber(List<String> list, int index) {
        list.set(list.size()-1, list.get(list.size()-1)+""+this.getInput().charAt(index));
        list.add("");
    }

//===================================================================

    @Override public boolean update(Object _obj) {
        this.setInput((String)_obj);
        this._calcul(); // do _calcul, this method (_calcul()) updates automatically the output

        return true;
    }

//===================================================================

    @Override public boolean _addObserver(Observer _observer) {
        this._observer_List.add(_observer);

        return true;
    }

//===================================================================
    
    @Override public boolean _updateObserver(Object _obj) {
        for(Observer obs: this._observer_List)
            obs.update(_obj);

        return true;
    }

//===================================================================
    
}
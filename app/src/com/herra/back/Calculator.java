package com.herra.back;

import java.util.LinkedList;
import java.util.List;

import com.herra.exception._DivisionByZeroException;
import com.herra.exception._SyntaxErrorException;
import com.interfaces.sh.Observable;
import com.interfaces.sh.Observer;

public class Calculator implements Observer, Observable {

    protected List<Observer> _cObservers = new LinkedList<Observer>();
    protected String _cInput = new String();
    protected List<Object> _cListNumber = new LinkedList<Object>();
    protected String _cOutPut = new String();
    protected boolean _canProcess = false;

//==========================================================================================

/**
 * Construct new Calculator object
 * 
 * @param _expression {@code the expression to evaluate}
 * 
 * @author Heriniaina - {@see https://github.com/Herra-dev}
 */
    public Calculator(String _input) {
        _cInput = _input;
        _initializeCalculator();
    }

//==========================================================================================
//==========================================================================================

/**
 * @return returns the list of Observer 
 */
    public List<Observer> _get_cObservers() { 
        return this._cObservers; 
    }

//==========================================================================================

/**
 * @return returns the actual input
 */
    public String _get_cInput() { 
        return this._cInput; 
    }

//==========================================================================================

/**
 * @return returns the list in listNumber
 */
    public List<Object> _get_cListNumber() { 
        return this._cListNumber; 
    }

//==========================================================================================

/**
 * @return returns the output
 */
    public String _get_cOutput() {
        return this._cOutPut;
    }

//==========================================================================================    

/**
 * Update this.cObservers to observers {@code List<Observer>}
 * 
 * @param observers {@code List<Observer>}
 */
    public void _set_cObservers(List<Observer> observers) {
        this._cObservers = observers; 
    }

//==========================================================================================

/**
 * Update this._cInput to input {@code String}
 * 
 * @param input {@String}
 */
    public void _set_cInput(String input) { 
        this._cInput = input;
        this._initializeCalculator();
    }

//==========================================================================================
    
/**
 * Update this._cListNumber to listNumber {@code List<Object>}
 * 
 * @param listNumber {@code List<Object>}
 */
    public void _set_cListNumber(List<Object> listNumber) { 
        this._cListNumber = listNumber; 
    }

//==========================================================================================

    public void _set_cOutput(String output) {
        this._cOutPut = output;
    }

//==========================================================================================    

    private void _initializeCalculator() {
        try {
            this._arrangeOperatorInput();
        } catch (_DivisionByZeroException | _SyntaxErrorException e) {
            e.printStackTrace();
        }

        try {
            this._testParenthesis();
        }catch(_SyntaxErrorException e) {
            e.printStackTrace();
        }

        this._separeInput();
    }

//==========================================================================================
//==========================================================================================

    public void _calcul() {
        _calcul(_cListNumber);
    }

//==========================================================================================

    public double _calcul(List<Object> _listN) {
        
        boolean withParenthesis = _listN.contains("(");
        List<Object> _listNumberCopy = _listN;

        // with parenthesis
        if(withParenthesis) {
            int first = 0;
            int last = 0;
            int anotherOneOpen = 0;

            first = _listN.indexOf("("); // first index of open parenthesis
            last = _listN.size()-1;

            for(int i = first+1; i < _listN.size(); i++) {
                if(_listN.get(i).equals(")")) {
                    if(anotherOneOpen == 0) {
                        last = i;
                        break;
                    }else if(anotherOneOpen > 0) --anotherOneOpen;

                }else if (_listN.get(i).equals("(")) ++anotherOneOpen;
            }

            
            for(int i = first+1; i < last; i++){
                _listNumberCopy.add(_listN.get(i)); // adding all value between first and last into an another List named _listNumberCopy
            }

            for(Object obs: _listN)
                System.out.print(obs);
            System.out.println();

            for(int i = last; i >= first; i--) {
                _listN.remove(i);
            }

            if(first > 0) {
                Object before = _listN.get(first-1);
                if(before.equals("+") || before.equals("-") ||
                     before.equals("*") || before.equals("/")) {
                    if(_listNumberCopy.contains("(")) 
                    _listN.add(first, _calcul(_listNumberCopy));

                } else {
                    _listN.add(first, "*");

                    if(_listNumberCopy.contains("(")) 
                        _listN.add(first+1, _calcul(_listNumberCopy));
                }
                    
                
            }

            for(Object obs: _listN)
                System.out.print(obs);
            System.out.println();            
        }

        // while(_listN.indexOf("+") != -1 &&  _listN.indexOf("+") != -1 && 
        //         _listN.indexOf("+") != -1 && _listN.indexOf("+") != -1) {
        //         System.out.println(_operatorMultiply(_listNumberCopy));

        // }
        System.out.println(_operatorMultiply(_listNumberCopy));
        
        return 1d;
    }

//==========================================================================================

@SuppressWarnings("unused")
    private double _operatorPlus(List<Object> _listN) {


        //  (5
        //  (5
        //  (5
        //  (5
        //  (5

        int indxOfPlusSign = _listN.indexOf("+");
        if(indxOfPlusSign == -1) return 0;

        int indxOfFirstNbr = indxOfPlusSign-1;
        int indxOfLastNbr = indxOfPlusSign+1;

        double fistNbr = java.lang.Double.parseDouble((String)_listN.get(indxOfFirstNbr));
        double secondNbr = java.lang.Double.parseDouble((String)_listN.get(indxOfLastNbr));

        return fistNbr + secondNbr;
    }

//==========================================================================================    

    @SuppressWarnings("unused")
    private double _operatorMinus(List<Object> _listN) {

        int indxOfMinuxSign = _listN.indexOf("-");
        if(indxOfMinuxSign == -1) return 0;

        int indxOfFirstNbr = indxOfMinuxSign-1;
        int indxOfLastNbr = indxOfMinuxSign+1;

        double fistNbr = java.lang.Double.parseDouble((String)_listN.get(indxOfFirstNbr));
        double secondNbr = java.lang.Double.parseDouble((String)_listN.get(indxOfLastNbr));

        return fistNbr - secondNbr;

    }

//==========================================================================================

    @SuppressWarnings("unused")
    private double _operatorMultiply(List<Object> _listN) {

        int indxOfMulSign = _listN.indexOf("*");
        if(indxOfMulSign == -1) return 0;

        int indxOfFirstNbr = indxOfMulSign-1;
        int indxOfLastNbr = ((_listN.get(indxOfMulSign+1)).equals("-")) ? indxOfMulSign+2 : indxOfMulSign+1;
        byte negative = ((_listN.get(indxOfMulSign+1)).equals("-")) ? (byte)-1 : (byte)1;

        double fistNbr = java.lang.Double.parseDouble((String)_listN.get(indxOfFirstNbr));
        double secondNbr = java.lang.Double.parseDouble((String)_listN.get(indxOfLastNbr));

        return fistNbr * (negative*secondNbr);
    }

//==========================================================================================

    @SuppressWarnings("unused")
    private double _operatorDivide(List<Object> _listN) {

        int indxOfDivSign = _listN.indexOf("/");
        if(indxOfDivSign == -1) return 0;

        int indxOfFirstNbr = indxOfDivSign-1;
        int indxOfLastNbr = ((_listN.get(indxOfDivSign+1)).equals("-")) ? indxOfDivSign+2 : indxOfDivSign+1;
        byte negative = ((_listN.get(indxOfDivSign+1)).equals("-")) ? (byte)-1 : (byte)1;

        double fistNbr = java.lang.Double.parseDouble((String)_listN.get(indxOfFirstNbr));
        double secondNbr = java.lang.Double.parseDouble((String)_listN.get(indxOfLastNbr));

        return fistNbr / (negative*secondNbr);
    }

//==========================================================================================

/**
 * This function test the user input:<p>
 * - if this last {@code starts} with: {@code *} or {@code /} or {@code )}<p>
 * - or {@code ends} with: {@code *} or {@code /} or {@code +} or {@code -}, returns {@code false}
 * 
 * <p>After that, simplify the expression(user input),as :<p>
 * - {@code +} * {@code +} = {@code +}<p>
 * - {@code -} * {@code -} = {@code +}<p>
 * - {@code +} * {@code -} = {@code -}<p>
 * - {@code -} * {@code +} = {@code -}<p>
 * 
 * @return returns {@code true} if the actual input is correct and simplified
 * 
 * @throws _DivisionByZeroException when user ask to calcul {@code number}/{@code 0}
 * @throws _SyntaxErrorException when there is an error of syntax
 * 
 * @author Heriniaina {@see https://github.com/Herra-dev}
 */
    private boolean _arrangeOperatorInput() 
        throws _DivisionByZeroException, _SyntaxErrorException{
        
        if(_cInput.contains("/0")) {
            this._cOutPut = "SYNTAX ERROR";
            this._canProcess = false;
            throw new _DivisionByZeroException(_cInput.charAt(_cInput.indexOf("/0")) + "by zero");
        }
        if(_cInput.startsWith("*") || _cInput.startsWith("/") ||  _cInput.startsWith(")")) { 
            this._cOutPut = "SYNTAX ERROR";
            this._canProcess = false;
            throw new _SyntaxErrorException(_cInput.charAt(0) + "" + _cInput.charAt(1));
        }
        if(_cInput.endsWith("*") || _cInput.endsWith("/") || 
            this._cInput.endsWith("+") || _cInput.endsWith("-")) {
            this._cOutPut = "SYNTAX ERROR"; 
            throw new _SyntaxErrorException(_cInput.charAt(_cInput.charAt(_cInput.length()-1)) + "" + _cInput.charAt(_cInput.length()));
        }
        if(_cInput.contains("-*") || _cInput.contains("-/") ||            
            _cInput.contains("+*") || _cInput.contains("+/") ||
            _cInput.contains("**") || _cInput.contains("*/") ||
            _cInput.contains("//") || _cInput.contains("/*") ||
            _cInput.contains("+)") || _cInput.contains("-)") ||
            _cInput.contains("*)") || _cInput.contains("/)") ||
            _cInput.contains("(/") || _cInput.contains("(*")) { 
                _cOutPut = "SYNTAX ERROR";
                this._canProcess = false;
                throw new _SyntaxErrorException(_cInput);
        }

        do {
            _cInput = _cInput.replaceAll("[+]{2}", "+");
            _cInput = _cInput.replaceAll("[-]{2}", "+");
            _cInput = _cInput.replaceAll("[+]{1}[-]{1}|[-]{1}[+]{1}", "-");
            _cInput = _cInput.replaceAll("[*]{1}[+]{1}", "*");
            _cInput = _cInput.replaceAll("[/]{1}[+]{1}", "/");
        }while(_cInput.contains("--") || _cInput.contains("++") || _cInput.contains("-+") || _cInput.contains("+-"));
        
        this._canProcess = true;
        return true;
    }

//==========================================================================================

/**
 * count and returns number of {@code toCount(char)} in {@code class's variable} {@code _cInput(String)}
 * 
 * @param toCount {@code char}, character to count
 * 
 * @return {@code int} - number of character
 * 
 * @author Heriniaina {@see https://github.com/Herra-dev}
 */
    private int _countCharacter(String str, char toCount) {
        int _nbr = 0;

        for(int i = 0; i < _cInput.length(); i++) {
            if(_cInput.charAt(i) == toCount) _nbr++;
        }

        return _nbr;
    }

//==========================================================================================

/**
 * returns {@code true} if the user input contains {@parenthesis}, otherwise returns {@code false}
 * 
 * @return {@code boolean}
 * 
 * @throws _SyntaxErrorException when the user input contains more closed than open parenthesis
 * 
 * @author Heriniaina {@see https://github.com/Herra-dev}
 */
    private boolean _testParenthesis() throws _SyntaxErrorException {
        // IF THE USER INPUT DOESN'T CONTAINS "(" OR ")" RETURNS false
        if(!_cInput.contains("(") && !_cInput.contains(")")) {
            this._canProcess = true;
            return false;
        } 
        
        int _openParenthesis = _countCharacter(_cInput, '(');
        int _closedParenthesis = _countCharacter(_cInput, ')');

        if(_openParenthesis < _closedParenthesis){ 
            this._cOutPut = "SYNTAX ERROR";
            this._canProcess = false;
            throw new _SyntaxErrorException("verify your syntax: closed parenthesis > open parenthesis");
        } else if(_openParenthesis > _closedParenthesis) { // add some closed parenthesis into the list's back when open parenthesis is more than closed
            for(int i = _openParenthesis-_closedParenthesis; i > 0; i--) {
                _closedParenthesis++;
                _cInput += ')';
            }
        }


        int[] _open = new int[_openParenthesis];
        int[] _close = new int[_closedParenthesis];
        int o = 0;
        int c = 0;

        for(int i = 0; i < _cInput.length(); i++) {
            if(_cInput.charAt(i) == '(') _open[o++] = i;
            if(_cInput.charAt(i) == ')') _close[c++] = i;
        }

        for(int _c = 0; _c < _close.length; _c++) {
            for(int _o = _c; _o >= 0; _o--) {
                if (_close[_c] < _open[_o]) {
                    _canProcess = false;
                    _cOutPut = "SYNTAX ERROR";
                    throw new _SyntaxErrorException("verify your syntax");
                }
            }         
        }

        _canProcess = true;
        return true;
    }

//==========================================================================================    

    private boolean _addNonNumberToListNumber(List<Object> _cListNumber, int index) {
        _cListNumber.set(_cListNumber.size()-1, _cListNumber.get(_cListNumber.size()-1)+""+_cInput.charAt(index));
        _cListNumber.add("");

        return true;
    }

//==========================================================================================    

/**
 * Separe input, and {@code put} each element in a {@code list of Object}
 * 
 * 
 * @return {@code List<Object>}
 */
    private List<Object> _separeInput() {
        _cListNumber.clear();
        _cListNumber.add("");

        for(int i = 0; i < _cInput.length(); i++) {
            if(_cInput.charAt(i) == '+') { _addNonNumberToListNumber(_cListNumber, i); continue; }
            if(_cInput.charAt(i) == '-') { _addNonNumberToListNumber(_cListNumber, i); continue; }
            if(_cInput.charAt(i) == '*') { _addNonNumberToListNumber(_cListNumber, i); continue; }
            if(_cInput.charAt(i) == '/') { _addNonNumberToListNumber(_cListNumber, i); continue; }
            if(_cInput.charAt(i) == '(') { _addNonNumberToListNumber(_cListNumber, i); continue; }
            if(_cInput.charAt(i) == ')') { _addNonNumberToListNumber(_cListNumber, i); continue; }

            _cListNumber.set(_cListNumber.size()-1, _cListNumber.get(_cListNumber.size()-1)+""+_cInput.charAt(i));
            
            if(i+1 < _cInput.length()) {
                char c = _cInput.charAt(i+1);
                if(c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')') _cListNumber.add("");
            }
            
        }

        if(_cListNumber.get(_cListNumber.size()-1) == (String)"") _cListNumber.remove(_cListNumber.size()-1);
        
        return _cListNumber;
    }

//==========================================================================================    
//==========================================================================================

    @Override public boolean update(Object _obj) {
        this._cInput = (String)_obj;

        return true;
    }

//==========================================================================================

    @Override public boolean _addObserver(Observer _observer) {
        this._cObservers.add(_observer);

        return true;
    }

//==========================================================================================

    @Override public boolean _updateObserver() {
        for(Observer obs: _cObservers)
            obs.update(obs);

        return true;
    }

//==========================================================================================

}
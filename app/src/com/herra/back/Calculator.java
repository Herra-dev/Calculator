package com.herra.back;

import java.util.LinkedList;
import java.util.List;

import com.interfaces.sh.Observable;
import com.interfaces.sh.Observer;

public class Calculator implements Observer, Observable {

    protected List<Observer> _cObservers = new LinkedList<Observer>();
    protected String _cInput = new String();
    protected List<Object> _cListNumber = new LinkedList<Object>();
    protected String _cOutPut = new String();

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
        if(_arrangeInput())
            System.out.println("Correct");
        else
            System.out.println("Incorrect");
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
//==========================================================================================

    public String _calcul() {
        return _cOutPut;
    }

//==========================================================================================

/**
 * This function test the user input:<p>
 * - if input {@code starts} with: {@code *} or {@code /} or<p>
 * - if input {@code ends} with: {@code *} or {@code /} or {@code +} or {@code -}, returns {@code false}
 * 
 * <p>After that, 
 * 
 * @return returns {@code true} if the actual input is correct
 * 
 * @author Heriniaina {@see https://github.com/Herra-dev}
 */
    private boolean _arrangeInput() {
        if(_cInput.startsWith("*") || _cInput.startsWith("/"))  return false;
        if(_cInput.endsWith("*") || _cInput.endsWith("/") || 
           _cInput.endsWith("+") || _cInput.endsWith("-"))      return false;

        do {
            _cInput = _cInput.replaceAll("[+]{2}", "+");
            _cInput = _cInput.replaceAll("[-]{2}", "+");
            _cInput = _cInput.replaceAll("[+]{1}[-]{1}|[-]{1}[+]{1}", "-");
        }while(_cInput.contains("--") || _cInput.contains("++") || _cInput.contains("-+") || _cInput.contains("+-"));
        
        return true;
    }

//==========================================================================================    

    @SuppressWarnings("unused")
    private List<Object> _separeInput() {
        
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
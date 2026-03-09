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
        _cListNumber = this._separeInput();
        _arrangeInput();
        for(Object obs: _cListNumber)
            System.out.print(obs);
        System.out.println();
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
        this._cInput = input; this._separeInput(); 
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
 * This function test the expression to evaluate:<p>
 * - if there is a succession of operator {@code -} and {@code +}, these last are simplified.<p> 
 * Rules :<p>
 * {@code -} * {@code -} = {@code +}<p>
 * {@code +} * {@code +} = {@code +}<p>
 * {@code -} * {@code +} = {@code -}<p>
 * {@code +} * {@code -} = {@code -}<p>
 * - if there is a succession of operator ({@code -} or {@code +}) and ({@code *} or {@code /}), {@code FALSE} will be the returns value.<p>
 * example :<p>
 * {@code +} * {@code *} = {@code error}<p>
 * {@code -} * {@code /} = {@code error}<p>
 * {@code /} * {@code +} = {@code error}<p>
 * {@code *} * {@code -} = {@code error}<p>
 * 
 * @return returns {@code true} if the actual input is correct
 * 
 * @author Heriniaina {@see https://github.com/Herra-dev}
 */
    private boolean _arrangeInput() {
        int index = 0;

        Object obj0 = null;
        Object obj1 = null;
        while(index < _cListNumber.size()-1) {
            obj0 = _cListNumber.get(index);
            obj1 = _cListNumber.get(index+1);
            if(obj0.getClass() == obj1.getClass()) {
                if((obj0.equals('-') || obj0.equals('+')) &&
                    (obj1.equals('-') || obj1.equals('+'))) {
                    if(obj0 == obj1)
                        _cListNumber.set(index, '+');
                    else
                        _cListNumber.set(index, '-');
                    
                    _cListNumber.remove(index+1);
                    index = 0;
                }else {
                    if((obj0.equals('+') || obj0.equals('-')) &&
                        (!obj1.equals('+') && !obj1.equals('-') &&
                            !obj1.equals('('))) {
                        System.out.println("error 1");
                        return false;
                    } else if ((obj1.equals('+') || obj1.equals('-')) &&
                        (!obj0.equals('+') && !obj0.equals('-') &&
                            !obj0.equals('(') && !obj0.equals(')') &&
                                !obj0.equals('*') && !obj0.equals('/'))) {
                        System.out.println("error 2");
                        return false;
                    } 
                    else if((obj0.equals('*') || obj0.equals('/')) && 
                        (!obj1.equals('(') &&
                            !obj1.equals('+') && !obj1.equals('-'))) {
                        System.out.println("error 3");
                        return false;
                    }
                }
            }
            
            index++;
        }
        
        return true;
    }

//==========================================================================================    

    private List<Object> _separeInput() {
        _cListNumber.clear();
        _cListNumber.add("");

        for(int i = 0; i < _cInput.length(); i++)
        {
            if(_cInput.charAt(i) == '+')      { _cListNumber.add(_cInput.charAt(i)); continue;}
            else if(_cInput.charAt(i) == '-') { _cListNumber.add(_cInput.charAt(i)); continue;}
            else if(_cInput.charAt(i) == '*') { _cListNumber.add(_cInput.charAt(i)); continue;}
            else if(_cInput.charAt(i) == '/') { _cListNumber.add(_cInput.charAt(i)); continue;}
            else if(_cInput.charAt(i) == '(') { _cListNumber.add(_cInput.charAt(i)); continue;}
            else if(_cInput.charAt(i) == ')') { _cListNumber.add(_cInput.charAt(i)); continue;}
            else {
                java.lang.Character c = ' ';
                if(_cListNumber.get(_cListNumber.size()-1).getClass().equals(c.getClass()))
                    _cListNumber.add("");

                _cListNumber.set((_cListNumber.size() - 1), (_cListNumber.get(_cListNumber.size() - 1) + "" + _cInput.charAt(i)));
                _cListNumber.set((_cListNumber.size() - 1), java.lang.Double.parseDouble((String)_cListNumber.get((_cListNumber.size() - 1))));
            }
        }

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
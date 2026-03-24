package com.herra.back;

import java.util.List;
import java.util.LinkedList;

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

    protected abstract void testUserInput() throws _SyntaxErrorException;
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
package com.herra.back;

import com.herra.exception._SyntaxErrorException;
import com.interfaces.sh.Observable;
import com.interfaces.sh.Observer;

public abstract class AbstractCalculator implements Observer, Observable {
    protected String _input = new String("0");
    protected boolean _canProcess = true;
    protected String _outPut = new String("0");

//===================================================================
//  ABSTRACT METHODS

    protected abstract void testUserInput() throws _SyntaxErrorException;
    protected abstract void arrangeUserInput();
    protected abstract String calcul();

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
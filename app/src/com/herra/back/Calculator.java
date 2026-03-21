package com.herra.back;

import com.interfaces.sh.Observable;
import com.interfaces.sh.Observer;

public class Calculator implements Observer, Observable {
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
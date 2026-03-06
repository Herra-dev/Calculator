package com.herra.back;

import java.util.LinkedList;
import java.util.List;

import com.interfaces.sh.Observable;
import com.interfaces.sh.Observer;

public class Calculator implements Observer, Observable{

    protected List<Observer> _cObservers = new LinkedList<Observer>();
    protected String _cNumbers = "";

    public boolean update(Object _obj) {
        this._cNumbers = (String)_obj;

        return true;
    }

    public boolean _addObserver(Observer _observer) {
        this._cObservers.add(_observer);

        return true;
    }
    public boolean _updateObserver() {
        for(Observer obs: _cObservers)
            obs.update(obs);

        return true;
    }
}
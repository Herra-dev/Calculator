package com.herra.back;

import java.util.LinkedList;
import java.util.List;

import com.interfaces.sh.Observable;
import com.interfaces.sh.Observer;

public class Calculator implements Observer, Observable{

    protected List<Observer> _cObservers = new LinkedList<Observer>(); 

    public boolean update(Object _obj) {

        return true;
    }

    public boolean _addObserver(Observer _observer) {

        return true;
    }
    public boolean _updateObserver() {

        return true;
    }
}
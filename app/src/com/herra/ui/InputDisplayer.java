package com.herra.ui;

import java.util.LinkedList;
import java.util.List;

import javax.swing.JLabel;

import com.interfaces.sh.Observable;
import com.interfaces.sh.Observer;

public class InputDisplayer extends JLabel implements Observable{

    protected List<Observer> _c_observer = new LinkedList<Observer>();

    public InputDisplayer() {
        this.setHorizontalAlignment(RIGHT);
    }

//==========================================================================================

    @Override public boolean _addObserver(Observer _observer) {
        _c_observer.add(_observer);

        return true;
    }

//==========================================================================================

    @Override public boolean _updateObserver() {
        for(Observer obs: _c_observer)
            obs.update(obs);
        return true;
    }

//==========================================================================================


}
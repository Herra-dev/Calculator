package com.herra.ui;

import java.util.LinkedList;
import java.util.List;

import javax.swing.JLabel;

import com.interfaces.sh.Observable;
import com.interfaces.sh.Observer;

public class InputDisplayer extends JLabel implements Observable{

    protected List<Observer> _cObserver = new LinkedList<Observer>();

    public InputDisplayer() {
        this.setHorizontalAlignment(RIGHT);
    }

//==========================================================================================

    @Override public boolean _addObserver(Observer _observer) {
        _cObserver.add(_observer);

        return true;
    }

//==========================================================================================

    @Override public boolean _updateObserver() {
        for(Observer obs: _cObserver)
            obs.update(obs);
        return true;
    }

//==========================================================================================


}
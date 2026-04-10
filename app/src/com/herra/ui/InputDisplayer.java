package com.herra.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import com.interfaces.sh.Observable;
import com.interfaces.sh.Observer;

public class InputDisplayer extends JLabel implements Observable, Observer{

    protected List<Observer> _cObserver = new LinkedList<Observer>();

    public InputDisplayer() {
        this.setHorizontalAlignment(RIGHT);
        this.setText("INPUT");
        this.setForeground(Color.GREEN);
        this.setFont(new Font("Arial", 1, 40));
        
        this.setBorder(LineBorder.createBlackLineBorder());
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

    @Override public boolean update(Object _obj) {
        return true;
    }
}
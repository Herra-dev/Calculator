package com.herra.ui;

import javax.swing.JLabel;

import com.interfaces.sh.Observable;
import com.interfaces.sh.Observer;

public class Displayer extends JLabel implements Observable{

    @Override public boolean _addObserver(Observer _observer) {
        return true;
    }


    @Override public boolean _updateObserver() {
        return true;
    }
}
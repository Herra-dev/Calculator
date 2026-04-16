package com.herra.ui;

import java.awt.Color;

import javax.swing.JLabel;

import com.interfaces.sh.Observer;

public class OutputDisplayer extends JLabel implements Observer{

    protected String _errString = "SYNTAX ERROR";

    public OutputDisplayer() {
        this.setHorizontalAlignment(RIGHT);
        this.setText("OUTPUT");
    }

//==========================================================================================

    @Override public boolean update(Object _obj) {
        if(_obj.equals(_errString)) this.setForeground(Color.RED);
        else                        this.setForeground(Color.BLACK);
        
        this.setText((String)_obj);

        return true;
    }

//==========================================================================================    

}
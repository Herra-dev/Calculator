package com.herra.ui;

import javax.swing.JLabel;

import com.interfaces.sh.Observer;

public class OutputDisplayer extends JLabel implements Observer{

    public OutputDisplayer() {
        this.setHorizontalAlignment(RIGHT);
        this.setText("OUTPUT");
    }

//==========================================================================================

    @Override public boolean update(Object _obj) {
        this.setText((String)_obj);

        return true;
    }

//==========================================================================================    

}
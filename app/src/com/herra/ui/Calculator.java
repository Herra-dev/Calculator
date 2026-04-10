package com.herra.ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.interfaces.sh.Observable;
import com.interfaces.sh.Observer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.LinkedList;

public class Calculator extends JFrame implements KeyListener, Observable{
    protected LinkedList<JLabel> displayer_list = this.setDisplayer(); 
    protected LinkedList<String> key_list_number = this.setKeyNumber();
    protected JPanel displayer_panel = new JPanel(new GridLayout(2, 1)); // two lines and one column
    protected JPanel number_panel = new JPanel(new GridLayout(5, 4)); // four lines and three columns
    protected String user_input = new String(""); // String to stock user input

//==================================================================================

    public Calculator() {
        this.setCalculatorProperty(); // set properties for the Calculator Window

        this.addButtonToPanel(number_panel, key_list_number);
        this.addDisplayerToPanel(displayer_panel, displayer_list);

        this.getContentPane().add(number_panel, BorderLayout.CENTER);
        this.getContentPane().add(displayer_panel, BorderLayout.NORTH);

        this.addKeyListener(this);       
    }

//==================================================================================

    public void setCalculatorProperty() {
        this.setLocationRelativeTo(null);
        this.setTitle("Calculator");
        this.setSize(400, 400);
        this.setMinimumSize(new Dimension(400, 400));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setVisible(true);
    }

//==================================================================================

    public void addDisplayerToPanel(JPanel panel, LinkedList<JLabel> to_add) {
        if(!(panel.getLayout().getClass().equals(new GridLayout().getClass()))) return; // only for panel having GridLayout as Layout

        for(JLabel lab: to_add) {
            lab.setSize(new Dimension(200, 100));
            panel.add(lab);
        }
    }

//==================================================================================

    public void addButtonToPanel(JPanel panel, LinkedList<String> to_add) {
        if(!(panel.getLayout().getClass().equals(new GridLayout().getClass()))) return; // only for panel having GridLayout as Layout

        for(String str: to_add) {
            JButton button = new JButton(str);
            button.setFocusable(false);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    if(button.getText().matches("Del")) {
                        String temp_string = new String();
                        for(int i = 0; i < temp_string.length(); i++) {
                            
                        }
                    }
                    System.out.println("Button : " + button.getText() + " clicked");
                }
            });
            if(str.matches("[0-9]") || str.equals(".")) {
                button.setBackground(Color.CYAN);
                button.setFont(new Font("Z003", 1, 40));
            } else if(button.getText().matches("Del|Clear")) {
                button.setFont(new Font("Arial", 1, 20));
            } else {
                button.setFont(new Font("Arial", 1, 40));
            }


            panel.add(button);
        }
    }

//==================================================================================

    public LinkedList<String> setKeyNumber() {
        String key_number = "789+456-123*0.%/";
        LinkedList<String> key = new LinkedList<String>();

        for(int i = 0; i < key_number.length(); i++) key.add(key_number.charAt(i)+"");

        key.add("Del");
        key.add("Clear");

        return key;
    }

//==================================================================================

    public LinkedList<JLabel> setDisplayer() {
        LinkedList<JLabel> label = new LinkedList<JLabel>();

        label.add(new InputDisplayer());
        label.add(new OutputDisplayer());

        return label;
    }

//==================================================================================

    @Override public void keyPressed(KeyEvent event) {
        System.out.println("key pressed : " + KeyEvent.getKeyText(event.getKeyCode()));
    }

//==================================================================================

    @Override public void keyTyped(KeyEvent event) { }

//==================================================================================

    @Override public void keyReleased(KeyEvent event) { }

//==================================================================================

    @Override public boolean _addObserver(Observer _observer) {
        return true;
    }

//==================================================================================

    @Override public boolean _updateObserver() {
        return true;
    }

}

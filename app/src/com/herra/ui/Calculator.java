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

public class Calculator extends JFrame implements KeyListener, Observable, ActionListener{

    protected LinkedList<JLabel> displayer_list = this.setDisplayer(); 
    protected LinkedList<JButton> key_button = this.setButton();
    protected JPanel displayer_panel = new JPanel(new GridLayout(2, 1)); // two lines and one column
    protected JPanel number_panel = new JPanel(new GridLayout(5, 4)); // four lines and three columns
    protected String user_input = new String(); // String to stock user input
    protected String output = new String(); // output
    protected Calculator calc = new Calculator();

//==================================================================================

    public Calculator() {
        this.setCalculatorProperty(); // set properties for the Calculator Window

        this.addButtonToPanel(number_panel, key_button);
        this.addDisplayerToPanel(displayer_panel, displayer_list);

        this.getContentPane().add(number_panel, BorderLayout.CENTER);
        this.getContentPane().add(displayer_panel, BorderLayout.NORTH);

        this.addKeyListener(this);       
    }

//==================================================================================

    public String getUserInput() {
        return this.user_input;
    }

//==================================================================================

    public void setUserInput(String new_user_input) {
        this.user_input = new_user_input;
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

    public void addButtonToPanel(JPanel panel, LinkedList<JButton> to_add) {
        if(!(panel.getLayout().getClass().equals(new GridLayout().getClass()))) return; // only for panel having GridLayout as Layout

        for(JButton bt: to_add) {
            bt.setFocusable(false);
            bt.addActionListener(this);
                
            if(bt.getText().matches("[0-9]") || bt.getText().equals(".")) {
                bt.setBackground(Color.CYAN);
                bt.setFont(new Font("Z003", 1, 40));
            } else if(bt.getText().matches("Del|Clear")) {
                bt.setFont(new Font("Arial", 1, 20));
            } else {
                bt.setFont(new Font("Arial", 1, 40));
            }


            panel.add(bt);
        }
    }

//==================================================================================

    public LinkedList<JLabel> setDisplayer() {
        LinkedList<JLabel> label = new LinkedList<JLabel>();

        label.add(new InputDisplayer());
        label.add(new OutputDisplayer());

        return label;
    }

    public LinkedList<JButton> setButton() {
        LinkedList<JButton> button = new LinkedList<JButton>();
        String key_number = "789+456-123*0.%/";

        for(int i = 0; i < key_number.length(); i++) button.add(new JButton(key_number.charAt(i)+""));

        button.add(new JButton("Del"));
        button.add(new JButton("Clear"));

        return button;
    }

//==================================================================================

    @Override public void keyPressed(KeyEvent event) {
        String keyPressed = event.getKeyChar()+"";
        if(keyPressed.matches("[0-9]|NumPad-[0-9]")) {
            this.setUserInput(this.getUserInput()+keyPressed);
            this.displayer_list.get(0).setText(this.getUserInput());
        }
        if(KeyEvent.getKeyText(event.getKeyCode()).matches("Backspace")) {
            String _user_input = this.getUserInput();
            String _temp_user_input = new String();

            for(int j = 0; j < _user_input.length()-1; j++) _temp_user_input += _user_input.charAt(j);
            this.setUserInput(_temp_user_input);
            this.displayer_list.get(0).setText(this.getUserInput());
        }
        if(keyPressed.equals("+") || keyPressed.equals("-") || keyPressed.equals("*") ||
            keyPressed.equals("/") || keyPressed.equals("%") || keyPressed.equals(".")) {
            
            this.setUserInput(this.getUserInput()+keyPressed);
            this.displayer_list.get(0).setText(this.getUserInput());
        }
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

//==================================================================================

    @Override public void actionPerformed(ActionEvent event) {
        for(int i = 0; i < key_button.size(); i++) {
            if(event.getSource() == key_button.get(i) && key_button.get(i).getText().equals("Del")) {
                String _user_input = this.getUserInput();
                String _temp_user_input = new String();

                for(int j = 0; j < _user_input.length()-1; j++) _temp_user_input += _user_input.charAt(j);
                this.setUserInput(_temp_user_input);
                this.displayer_list.get(0).setText(this.getUserInput());
            }
            if(event.getSource() == key_button.get(i) && key_button.get(i).getText().equals("Clear")){
                this.setUserInput(new String());
                this.displayer_list.get(0).setText(this.getUserInput());
            }
            if(event.getSource() == key_button.get(i) && key_button.get(i).getText().matches("[0-9]|\\p{Punct}")) {
                this.setUserInput(this.getUserInput()+key_button.get(i).getText());
                this.displayer_list.get(0).setText(this.getUserInput());
            }
        }
    }

}

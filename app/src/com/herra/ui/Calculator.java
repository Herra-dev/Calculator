package com.herra.ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.herra.back.DecimalCalculator;
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
import java.util.List;

public class Calculator extends JFrame implements KeyListener, Observable, ActionListener{

    protected LinkedList<JLabel> _displayer_list = this.setDisplayer(); 
    protected LinkedList<JButton> _key_button = this.setButton();
    protected JPanel _displayer_panel = new JPanel(new GridLayout(2, 1)); // two lines and one column
    protected JPanel _number_panel = new JPanel(new GridLayout(5, 4)); // four lines and three columns
    protected String _user_input = new String(); // String to stock user input
    protected DecimalCalculator _calc = new DecimalCalculator(new String());
    protected List<Observer> _observer_list = new LinkedList<Observer>();

//==================================================================================

    public Calculator() {
        this.setCalculatorProperty(); // set properties for the Calculator Window

        this.addButtonToPanel(_number_panel, _key_button);
        this.addDisplayerToPanel(_displayer_panel, _displayer_list);

        this.getContentPane().add(_number_panel, BorderLayout.CENTER);
        this.getContentPane().add(_displayer_panel, BorderLayout.NORTH);

        this.addKeyListener(this);   
        this._addObserver(_calc); // add _calc(DecimalCalculator) as observer of this   
    }

//==================================================================================

    public String getUserInput() {
        return this._user_input;
    }

//==================================================================================

    public void setUserInput(String new__user_input) {
        this._user_input = new__user_input;
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
            } else if(bt.getText().matches("Del")) {
                bt.setFont(new Font("Arial", 1, 20));
                bt.setBackground(Color.GRAY);
            } else if(bt.getText().matches("Clear")) {
                bt.setFont(new Font("Arial", 1, 20));
                bt.setBackground(Color.RED);
            }

            else {
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
        String key_number = "789+456-123*0.%/()";

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
            this._displayer_list.get(0).setText(this.getUserInput());
        }
        if(KeyEvent.getKeyText(event.getKeyCode()).matches("Backspace")) {
            String __user_input = this.getUserInput();
            String _temp__user_input = new String();

            for(int j = 0; j < __user_input.length()-1; j++) _temp__user_input += __user_input.charAt(j);
            this.setUserInput(_temp__user_input);
            this._displayer_list.get(0).setText(this.getUserInput());
        }
        if(keyPressed.equals("+") || keyPressed.equals("-") || keyPressed.equals("*") ||
            keyPressed.equals("/") || keyPressed.equals("%") || keyPressed.equals(".")) {
            
            this.setUserInput(this.getUserInput()+keyPressed);
            this._displayer_list.get(0).setText(this.getUserInput());
        }

        this._calc.setInput(this.getUserInput());
        this._updateObserver(this.getUserInput());
        
        this._calc._calcul();
        String outPut = this._calc.getOutput();
        
        this._displayer_list.get(1).setText(outPut);
    }

//==================================================================================

    @Override public void keyTyped(KeyEvent event) { }

//==================================================================================

    @Override public void keyReleased(KeyEvent event) { }

//==================================================================================

    @Override public boolean _addObserver(Observer _observer) {
        this._observer_list.add(_observer);

        return true;
    }

//==================================================================================

    @Override public boolean _updateObserver(Object _obj) {
        for(Observer obs: this._observer_list)
            obs.update(_obj);

        return true;
    }

//==================================================================================

    @Override public void actionPerformed(ActionEvent event) {
        for(int i = 0; i < _key_button.size(); i++) {
            if(event.getSource() == _key_button.get(i) && _key_button.get(i).getText().equals("Del")) {
                String __user_input = this.getUserInput();
                String _temp__user_input = new String();

                for(int j = 0; j < __user_input.length()-1; j++) _temp__user_input += __user_input.charAt(j);
                this.setUserInput(_temp__user_input);
                this._displayer_list.get(0).setText(this.getUserInput());
            }
            if(event.getSource() == _key_button.get(i) && _key_button.get(i).getText().equals("Clear")){
                this.setUserInput(new String());
                this._displayer_list.get(0).setText(this.getUserInput());
                this._displayer_list.get(1).setText("OUTPUT");
                return;
            }
            if(event.getSource() == _key_button.get(i) && _key_button.get(i).getText().matches("[0-9]|\\p{Punct}")) {
                this.setUserInput(this.getUserInput()+_key_button.get(i).getText());
                this._displayer_list.get(0).setText(this.getUserInput());
            }
        }

        this._calc.setInput(this.getUserInput());
        this._displayer_list.get(1).setForeground(Color.BLACK);
        
        this._calc._calcul();
        String outPut = this._calc.getOutput();
        
        this._displayer_list.get(1).setText(outPut);
        if(this._displayer_list.get(1).getText().equals("SYNTAX ERROR"))
            this._displayer_list.get(1).setForeground(Color.RED);
    }

}

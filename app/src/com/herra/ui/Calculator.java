package com.herra.ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

public class Calculator extends JFrame implements KeyListener {
    protected LinkedList<String> key_list_number = this.setKeyNumber();
    protected LinkedList<String> key_list_operator = this.setKeyOperator();
    protected JPanel DisplayerPanel = new JPanel(new GridLayout(2, 1)); // two lines and one column
    protected JPanel NumberPanel = new JPanel(new GridLayout(key_list_number.size()/3, 3)); // four lines and three columns
    protected JPanel OperatorPanel = new JPanel(new GridLayout(key_list_operator.size(), 1));

//==================================================================================

    public Calculator() {
        this.setLocationRelativeTo(null);
        this.setTitle("Calculator");
        this.setSize(500, 500);
        this.setMinimumSize(new Dimension(200, 200));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        this.addToPanel(NumberPanel, key_list_number);
        this.addToPanel(OperatorPanel, key_list_operator);

        this.getContentPane().add(NumberPanel, BorderLayout.WEST);
        this.getContentPane().add(OperatorPanel, BorderLayout.EAST);

        JTextField field = new JTextField("text here");
        this.getContentPane().add(field, BorderLayout.CENTER);

        this.addKeyListener(this);
              
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setVisible(true);
    }

//==================================================================================

    public void addToPanel(JPanel panel, LinkedList<String> to_add) {
        for(String str: to_add) {
            JButton bt = new JButton(str);
            bt.setFocusable(false);
            panel.add(bt);
        }
    }

//==================================================================================

    public LinkedList<String> setKeyNumber() {
        String key_number = "7894561230.%";
        LinkedList<String> key = new LinkedList<String>();

        for(int i = 0; i < key_number.length(); i++) key.add(key_number.charAt(i)+"");

        return key;
    }

//==================================================================================

    public LinkedList<String> setKeyOperator() {
        String key_operator = "+-*/()";
        LinkedList<String> key = new LinkedList<String>();

        for(int i = 0; i < key_operator.length(); i++) key.add(key_operator.charAt(i)+"");

        return key;
    }

//==================================================================================

    public LinkedList<String> getKeyNumber() {
        return this.key_list_number;
    }

//==================================================================================

    public LinkedList<String> getKeyOperator() {
        return this.key_list_operator;
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

    public JButton addActionListener(JButton button) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.out.println("you pressed the button : " + button.getText() + ", via addActionListener button");
            }
        });

        return button;
    }

//==================================================================================    

}

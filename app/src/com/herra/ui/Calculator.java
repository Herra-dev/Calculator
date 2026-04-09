package com.herra.ui;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

public class Calculator extends JFrame implements KeyListener {
    protected LinkedList<String> key_list_number = new LinkedList<String>();
    protected LinkedList<String> key_list_operator = new LinkedList<String>();

//==================================================================================

    public Calculator() {
        this.setLocationRelativeTo(null);
        this.setTitle("Calculator");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        this.getContentPane().add(new InputDisplayer(), BorderLayout.NORTH);
        this.getContentPane().add(new InputDisplayer(), BorderLayout.WEST);
        this.getContentPane().add(new OutputDisplayer(), BorderLayout.SOUTH);

        JButton button0 = new JButton("B");
        JButton button1 = new JButton("V");
        
        button0 = this.addActionListener(button0);
        button0.setFocusable(false);
        button1 = this.addActionListener(button1); 
        button1.setFocusable(false);

        this.addKeyListener(this);
              
        this.getContentPane().add(button0, BorderLayout.CENTER);
        this.getContentPane().add(button1, BorderLayout.EAST);

        this.setKeyNumber();
        this.setKeyOperator();

        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setVisible(true);
    }

//==================================================================================

    public void setKeyNumber() {
        this.key_list_number.add("7");
        this.key_list_number.add("8");
        this.key_list_number.add("9");
        this.key_list_number.add("4");
        this.key_list_number.add("5");
        this.key_list_number.add("6");
        this.key_list_number.add("1");
        this.key_list_number.add("2");
        this.key_list_number.add("3");
        this.key_list_number.add("0");
        this.key_list_number.add(".");
        this.key_list_number.add("%");
    }

//==================================================================================

    public void setKeyOperator() {
        this.key_list_operator.add("+");
        this.key_list_operator.add("-");
        this.key_list_operator.add("*");
        this.key_list_operator.add("/");
        this.key_list_operator.add("(");
        this.key_list_operator.add(")");
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

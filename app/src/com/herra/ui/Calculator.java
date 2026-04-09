package com.herra.ui;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
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
    protected JPanel NumberPanel = new JPanel(new GridLayout(4, 3)); // four lines and three columns
    protected JPanel OperatorPanel = new JPanel(new GridLayout(5, 1));

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

        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setVisible(true);
    }

//==================================================================================

    public LinkedList<String> setKeyNumber() {
        LinkedList<String> key = new LinkedList<String>();

        key.add("7");
        key.add("8");
        key.add("9");
        key.add("4");
        key.add("5");
        key.add("6");
        key.add("1");
        key.add("2");
        key.add("3");
        key.add("0");
        key.add(".");
        key.add("%");

        return key;
    }

//==================================================================================

    public LinkedList<String> setKeyOperator() {
        LinkedList<String> key = new LinkedList<String>();
        key.add("+");
        key.add("-");
        key.add("*");
        key.add("/");
        key.add("(");
        key.add(")");

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

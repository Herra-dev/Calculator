package com.herra.ui;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Calculator extends JFrame implements KeyListener{
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
        button1 = this.addActionListener(button1); 

        this.addKeyListener(this);
              
        this.getContentPane().add(button0, BorderLayout.CENTER);
        this.getContentPane().add(button1, BorderLayout.EAST);
    }

//==================================================================================

    @Override public void keyPressed(KeyEvent event) {
        System.out.println("key pressed");
    }

//==================================================================================

    @Override public void keyTyped(KeyEvent event) {
        System.out.println("key typed");
    }

//==================================================================================

    @Override public void keyReleased(KeyEvent event) {
        
    }

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

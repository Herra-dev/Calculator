package com.herra.ui;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Calculator extends JFrame{
    public Calculator() {
        this.setLocationRelativeTo(null);
        this.setTitle("Calculator");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        this.getContentPane().add(new InputDisplayer(), BorderLayout.NORTH);
        this.getContentPane().add(new InputDisplayer(), BorderLayout.WEST);
        this.getContentPane().add(new OutputDisplayer(), BorderLayout.SOUTH);

        JButton button0 = new JButton("JButton 0");
        JButton button1 = new JButton("JButton 1");
        button0 = this.addActionListener(button0);
        button1 = this.addActionListener(button1);
        
        button0.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent event) {
                if(KeyEvent.getKeyText(event.getKeyCode()) == "F3"){
                    System.out.println("You pressed F3");
                    System.out.println("You pressed " + KeyEvent.getKeyText(event.getKeyCode()));
                }else {
                    System.out.println("You pressed another key than F3");
                }
            }
            public void keyTyped(KeyEvent event) {

            }
            public void keyReleased(KeyEvent event) {
                
            }
        });

        this.getContentPane().add(button0, BorderLayout.CENTER);
        this.getContentPane().add(button1, BorderLayout.EAST);
    }

    public JButton addActionListener(JButton button) {
        button.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent event) {
        System.out.println("you pressed the button : " + button.getText() + ", via addActionListener button");
    }
        });

        return button;
    }
}
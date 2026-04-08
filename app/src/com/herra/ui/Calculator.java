package com.herra.ui;

import java.awt.Color;

import javax.swing.JFrame;

public class Calculator extends JFrame{
    public Calculator() {
        this.setLocationRelativeTo(null);
        this.setTitle("Calculator");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        CalculatorPanel pan = new CalculatorPanel();
        pan.setBackground(Color.CYAN);
        this.setBackground(pan.getBackground());
        
        this.setContentPane(pan);
    }
}
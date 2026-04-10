package com.herra.ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

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

public class Calculator extends JFrame implements KeyListener {
    protected LinkedList<String> key_list_number = this.setKeyNumber();
    protected JPanel DisplayerPanel = new JPanel(new GridLayout(2, 1)); // two lines and one column
    protected JPanel NumberPanel = new JPanel(new GridLayout(4, 4)); // four lines and three columns

//==================================================================================

    public Calculator() {
        this.setCalculatorProperty(); // set properties for the Calculator Window
        

        this.addButtonToPanel(NumberPanel, key_list_number);
        this.getContentPane().add(NumberPanel, BorderLayout.CENTER);

        this.addKeyListener(this);
              
        
    }

//==================================================================================

    public void setCalculatorProperty() {
        this.setLocationRelativeTo(null);
        this.setTitle("Calculator");
        this.setSize(500, 500);
        this.setMinimumSize(new Dimension(400, 400));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setVisible(true);
    }

    public void addButtonToPanel(JPanel panel, LinkedList<String> to_add) {
        if(!(panel.getLayout().getClass().equals(new GridLayout().getClass()))) return;

        for(String str: to_add) {
            JButton button = new JButton(str);
            button.setFocusable(false);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    System.out.println("Button : " + button.getText() + " clicked");
                }
            });
            if(str.matches("[0-9]") || str.equals("."))
                button.setBackground(Color.CYAN);
            
            button.setFont(new Font("Arial", 1, 50));

            panel.add(button);
        }
    }

//==================================================================================

    public LinkedList<String> setKeyNumber() {
        String key_number = "789+456-123*0.%/";
        LinkedList<String> key = new LinkedList<String>();

        for(int i = 0; i < key_number.length(); i++) key.add(key_number.charAt(i)+"");

        return key;
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

}

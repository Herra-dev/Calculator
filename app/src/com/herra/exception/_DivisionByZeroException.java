package com.herra.exception;

public class _DivisionByZeroException extends Exception {
    public _DivisionByZeroException(String error) {
        System.out.println("Exception : " + error);
    }    
}
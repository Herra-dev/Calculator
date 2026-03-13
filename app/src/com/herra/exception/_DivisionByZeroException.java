package com.herra.exception;

public class _DivisionByZeroException extends Exception {
    public _DivisionByZeroException(String divByZ) {
        System.out.println("Exception : " + divByZ);
    }    
}
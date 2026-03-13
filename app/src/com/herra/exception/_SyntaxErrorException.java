package com.herra.exception;

public class _SyntaxErrorException extends Exception {
    public _SyntaxErrorException(String syntax) {
        System.out.println("Error : " + syntax);
    }
}
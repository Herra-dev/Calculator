package com.herra.exception;

public class _SyntaxErrorException extends Exception {
    public _SyntaxErrorException(String error) {
        System.out.println("Exception : " + error);
    }
}
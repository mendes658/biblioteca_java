package com.pbl.biblioteca.exceptionHandler;

public class tooManyReservesException extends Exception{
    public tooManyReservesException(String s){
        super(s);
    }
}

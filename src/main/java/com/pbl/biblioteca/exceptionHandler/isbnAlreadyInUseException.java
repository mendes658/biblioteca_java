package com.pbl.biblioteca.exceptionHandler;

public class isbnAlreadyInUseException extends Exception{

    public isbnAlreadyInUseException(String s){
        super(s);
    }
}

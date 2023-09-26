package com.pbl.biblioteca.exceptionHandler;

public class usernameAlreadyInUseException extends Exception{

    public usernameAlreadyInUseException(String s) {
        super(s);
    }
}

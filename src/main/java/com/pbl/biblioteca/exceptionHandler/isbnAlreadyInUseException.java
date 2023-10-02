package com.pbl.biblioteca.exceptionHandler;

/**
 * @author      Pedro Mendes <mendes @ ecomp.uefs.br>
 * @version     1.0
 */
public class isbnAlreadyInUseException extends Exception{

    public isbnAlreadyInUseException(String s){
        super(s);
    }
}

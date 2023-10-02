package com.pbl.biblioteca.exceptionHandler;

/**
 * @author      Pedro Mendes <mendes @ ecomp.uefs.br>
 * @version     1.0
 */
public class tooManyReservesException extends Exception{
    public tooManyReservesException(String s){
        super(s);
    }
}

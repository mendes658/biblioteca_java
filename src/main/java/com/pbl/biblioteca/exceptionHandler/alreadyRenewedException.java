package com.pbl.biblioteca.exceptionHandler;

/**
 * @author      Pedro Mendes <mendes @ ecomp.uefs.br>
 * @version     1.0
 */
public class alreadyRenewedException extends Exception{
    public alreadyRenewedException(String s){
        super(s);
    }
}

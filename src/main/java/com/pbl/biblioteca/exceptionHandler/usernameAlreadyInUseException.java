package com.pbl.biblioteca.exceptionHandler;

/**
 * @author      Pedro Mendes <mendes @ ecomp.uefs.br>
 * @version     1.0
 */
public class usernameAlreadyInUseException extends Exception{

    public usernameAlreadyInUseException(String s) {
        super(s);
    }
}

package com.pbl.biblioteca.model;

import java.io.Serializable;

/**
 * @author      Pedro Mendes <mendes @ ecomp.uefs.br>
 * @version     1.0
 */
public class Operator extends User implements Serializable {

    protected Operator(String username, String password, String address, String telephone,
                    String name, String type){
        super(username, password, address, telephone, name, type);
    }

}


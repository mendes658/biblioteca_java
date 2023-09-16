package com.pbl.biblioteca.model;
import com.pbl.biblioteca.dao.Operator.OperatorFileImpl;

import java.io.Serializable;

public class Operator extends User implements Serializable {
    private final String type;


    public Operator(String username, String password, String address, String telephone,
                    String name, String type){
        super(username, password, address, telephone, name);

        this.type = type;
    }


    public String getType() {
        return type;
    }


}


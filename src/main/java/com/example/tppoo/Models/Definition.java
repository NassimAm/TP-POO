package com.example.tppoo.Models;

import java.io.Serializable;

public class Definition implements Serializable {
    private String definition;
    private String mot;

    Definition(String definition,String mot)
    {
        this.definition = definition;
        this.mot = mot;
    }

    public String getDefinition()
    {
        return definition;
    }

    public String getMot()
    {
        return mot;
    }
}

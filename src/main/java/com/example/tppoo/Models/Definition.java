package com.example.tppoo.Models;
public class Definition {
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

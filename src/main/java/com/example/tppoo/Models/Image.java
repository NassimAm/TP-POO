package com.example.tppoo.Models;

import java.io.Serializable;

public class Image implements Serializable {
    private String lien;
    private String mot;

    Image(String lien,String mot)
    {
        this.lien = lien;
        this.mot = mot;
    }

    public String getLien()
    {
        return lien;
    }
    
    public String getMot()
    {
        return mot;
    }
}

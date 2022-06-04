package com.example.tppoo.Models;

import java.io.Serializable;

public class CaseParcours extends Case {
    CaseParcours(int numero)
    {
        this.setCouleur(Couleur.SANSCOULEUR);
        this.setNumero(numero);
    }

    public void action(Joueur joueur){}
}

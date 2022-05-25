package com.example.tppoo;
import java.util.Random;

public class CaseSaut extends Case {
    CaseSaut(int numero)
    {
        this.setCouleur(Couleur.ORANGE);
        this.setNumero(numero);
    }

    public void action(Joueur joueur){
        this.sauterJoueur(joueur,genererNumero());
    }

    private int genererNumero()
    {
        Random rand = new Random();
        return rand.nextInt(100) + 1;
    }
}

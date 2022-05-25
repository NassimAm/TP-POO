package com.example.tppoo;
public class CaseMalus extends Case {
    final int malusPoints = -10;
    final int malusDeplacement = -2;
    CaseMalus(int numero)
    {
        this.setCouleur(Couleur.ROUGE);
        this.setNumero(numero);
    }

    public void action(Joueur joueur){
        this.ajouterPoints(joueur,malusPoints);
        this.ajouterCases(joueur, malusDeplacement);
    }
}

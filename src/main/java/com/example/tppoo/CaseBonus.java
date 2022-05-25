package com.example.tppoo;
public class CaseBonus extends Case {
    final int bonusPoints = 10;
    final int bonusDeplacement = 2;
    CaseBonus(int numero)
    {
        this.setCouleur(Couleur.VERTE);
        this.setNumero(numero);
    }

    public void action(Joueur joueur){
        this.ajouterPoints(joueur,bonusPoints);
        this.ajouterCases(joueur, bonusDeplacement);
    }
}

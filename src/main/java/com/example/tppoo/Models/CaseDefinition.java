package com.example.tppoo.Models;
public class CaseDefinition extends CaseQuestion {
    private Definition definition;
    final int bonusPoints = 20;
    final int bonusCases = 4;
    final int malusPoints = -10;
    private boolean justesseReponse = false;
    
    CaseDefinition(int numero,Definition definition)
    {
        this.setCouleur(Couleur.BLEU);
        this.setNumero(numero);
        this.definition = definition;
    }

    public void CreerFenetre(){};

    public boolean verifierReponse(String reponse)
    {
        return (reponse == this.definition.getMot()); 
    }

    protected void succes(Joueur joueur){
        this.ajouterPoints(joueur, bonusPoints);
        this.ajouterCases(joueur, bonusCases);
    };
    protected void echec(Joueur joueur){
        this.ajouterPoints(joueur, malusPoints);
    };

    public void action(Joueur joueur){
        if(justesseReponse)
            succes(joueur);
        else
            echec(joueur);
    }
}

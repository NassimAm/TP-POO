package com.example.tppoo;
public class Joueur {

    private int id;
    private String nom;
    private int meilleurScore;
    private int position;
    private int score;

    public void setPosition(int position)
    {
        this.position = position;
    }

    //Deplacer le joueur sur le plateau
    public void deplacer(int dp) {
        this.position =+ dp;
    }

    //generer le lancer des deux des
    public int[] lancerDes(Dé dé1,Dé dé2) {
        int désValeurs[] = new int[2];
        désValeurs[0] = dé1.GenererCoup();
        désValeurs[1] = dé2.GenererCoup();
        return désValeurs;
    }

    //retourner le numero de case cliquée
    public int cliquerCase() {
        return 0;
    }

    //ajouter "points" au score courant
    public void ajouterPoints(int points) {
        this.score =+ points;
    }
}

package com.example.tppoo.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Joueur implements Serializable {

    private int id;
    private String nom;
    private int meilleurScore = 0;
    private int position = 0;
    private int score;
    private int prochainePosition = 0;

    private int nombreDePartie = 0;

    //nouveau
    public ArrayList<Partie> parties = new ArrayList<Partie>(); //une liste des parties du meme joueur
    public static int nbrJoueurs = 0;
    public Joueur(String nom)
    {
        nbrJoueurs++;
        this.id=0;
        this.nom = nom;
        this.position = 0;
        this.score = 0;
        this.meilleurScore = 0;
    }
    public void setPosition(int position)
    {
        this.position = position;
    }

    public void setMeilleurScore(int meilleurScore) {
        this.meilleurScore = meilleurScore;
    }

    //Deplacer le joueur sur le plateau
    public void deplacer(int dp) {
        this.position += dp;
    }

    //generer le lancer des deux des
    public int[] lancerDes(Dé dé1,Dé dé2) {
        int désValeurs[] = new int[2];
        désValeurs[0] = dé1.GenererCoup();
        désValeurs[1] = dé2.GenererCoup();
        return désValeurs;
    }

    //ajouter "points" au score courant
    public void ajouterPoints(int points) {
        this.score += points;
    }

    public String getNom()
    {
        return nom;
    }

    public int getId()
    {
        return id;
    }

    public int getMeilleurScore()
    {
        if(score > meilleurScore)
        {
            meilleurScore = score;
        }
        return this.meilleurScore;
    }

    public int getPosition() {
        return position;
    }

    public int getScore() {
        return score;
    }

    public void reset()
    {
        this.position = 0;
        this.score = 0;
    }

    public int getProchainePosition() {
        return prochainePosition;
    }

    public void setProchainePosition(int prochainePosition) {
        this.prochainePosition = prochainePosition;
    }
}

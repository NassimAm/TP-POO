package com.example.tppoo.Models;

import java.io.Serializable;

public abstract class Case implements Serializable {

    private Couleur couleur;
    private int numero;

    protected void ajouterPoints(Joueur joueur,int points) {
        joueur.ajouterPoints(points);
    }

    protected void ajouterCases(Joueur joueur,int nbcases) {
        joueur.deplacer(nbcases);
    }

    protected void sauterJoueur(Joueur joueur,int position)
    {
        joueur.setPosition(position);
    }

    public abstract void action(Joueur joueur);

    public void setCouleur(Couleur couleur)
    {
        this.couleur = couleur;
    }

    public void setNumero(int numero)
    {
        this.numero = numero;
    }

    public Couleur getCouleur()
    {
        return this.couleur;
    }

    int getNumero()
    {
        return this.numero;
    }
}

package com.example.tppoo;
public abstract class Case {

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

    protected abstract void action(Joueur joueur);

    void setCouleur(Couleur couleur)
    {
        this.couleur = couleur;
    }

    void setNumero(int numero)
    {
        this.numero = numero;
    }

    Couleur getCouleur()
    {
        return this.couleur;
    }

    int getNumero()
    {
        return this.numero;
    }
}

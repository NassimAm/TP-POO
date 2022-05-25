package com.example.tppoo;
public class Partie {

    private Joueur joueur;
    private boolean enCours;
    private boolean suspendu;
    private boolean enPause;
    private int id;
    private Image[] images;
    private Definition[] definitions;
    public Plateau plateau;

    public Partie() {

    }

    public void creerUnePartie() {

    }

    public void setEnPause(boolean enPause) {
        this.enPause = enPause;
    }

    //charger les images, mots, definitions du fichier ou ils sont stockés
    public void chargerDonnees(String nomFichier) {}

    //placer les cases sur le plateau
    public void PlacerCases() {}

    //le joueur s'identifie en introduisant son nom
    public void identifierJoueur(String nom) {}

    //la fonction principale qui fait donctionner la partie
    public void derouler() {}

    //mettre fin à la partie
    public void finPartie() {}
}

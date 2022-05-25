package com.example.tppoo;
public class Jeu {

    public Joueur[] joueurs; //une liste des joueurs
    public Partie[] parties; //une liste des parties

    //commencer le jeu
    public void jouer() {

    }

    //afficher le meilleur score d'un joueur
    public void afficherMeilleurScore(Joueur joueur) {}

    //afficher le meilleur score atteint dans le jeu
    public void afficherRecordABattre() {}

    //enregistrer la partie
    public void enregistrer(Partie partie) {
        //yes
    }

    //mettre en pause la partie
    public void pauser(Partie partie) {
        partie.setEnPause(true);
    }

    //reprendre la partie
    public void reprendre(Partie partie) {

    }

    public void quitter(){}

}

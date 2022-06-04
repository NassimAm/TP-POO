package com.example.tppoo.Models;
import com.example.tppoo.MainApplication;
import javafx.application.Platform;
import javafx.scene.Scene;

import java.io.*;
import java.util.*;
public class Jeu implements Serializable {

    public ArrayList<Joueur>joueurs = new ArrayList<Joueur>(); //une liste des joueurs
    //public ArrayList<Partie> parties = new ArrayList<Partie>(); //une liste des parties
    private Joueur joueur_courant;
    private  Partie partie_courante;

    //commencer le jeu
    public void jouer() {}

    //Identifier le joueur
    public boolean identifierJoueur(String nom)
    {
        boolean resultat = false;
        for (Joueur joueur : joueurs) {
            if (Objects.equals(joueur.getNom(), nom)) {
                resultat = true;
                break;
            }
        }
        return resultat;
    }

    public void enregistrerJoueur(String nom)
    {

        //chargerJoueurs("Joueurs");
       for (Joueur joueur:joueurs) {
            System.out.println(joueur.getNom());
        }
        if(identifierJoueur(nom)) //si le joueur existe
        {
            System.out.println(nom + " a ete indentifie avec succes");
            //charger ses parties
            //initialiser le joueur courant
            for (Joueur joueur: joueurs) {
                if (joueur.getNom().equals(nom)) {
                    this.joueur_courant = joueur;
                }
            }
        }
        else //c'est un nouveau joueur
        {
            System.out.println(nom + " est un nouveau joueur");
            this.joueur_courant = new Joueur(nom);
            this.joueurs.add(this.joueur_courant);

        }


    }

    //afficher les meilleurs scores
    public void afficherMeilleursScores() {


    }

    //afficher le meilleur score atteint dans le jeu
    public void afficherRecordABattre() {}

    //enregistrer la partie
    public void enregistrer(Partie partie) {
        //yes

    }

    //reprendre la partie
    public void reprendre(Partie partie) {

    }

    public void sauvegarder(){
        this.partie_courante.setScore(this.joueur_courant.getScore());
        this.partie_courante.setJoueur_position(this.joueur_courant.getPosition());
        this.partie_courante.setProchaine_position(this.joueur_courant.getProchainePosition());

        ObjectOutputStream out;
        try {
            out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File("jeu.dat"))));
            out.writeObject(this);
            //
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setJoueur_courant(Joueur joueur_courant) {
        this.joueur_courant = joueur_courant;
    }

    public void ajouterPartieCourante(Partie partie_courante) {

        this.partie_courante = partie_courante;
        this.joueur_courant.ajouterPartie(this.partie_courante);
    }

    public Joueur getJoueur_courant() {
        return joueur_courant;
    }

    public Partie getPartie_courante() {
        return partie_courante;
    }

    public void setPartie_courante(Partie partie_courante) {
        this.partie_courante = partie_courante;
    }

    public ArrayList<Joueur> getJoueurs() {
        return joueurs;
    }
}

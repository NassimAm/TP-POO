package com.example.tppoo.Models;
import java.io.*;
import java.util.*;
public class Jeu {

    public ArrayList<Joueur>joueurs = new ArrayList<Joueur>(); //une liste des joueurs
    public ArrayList<Partie> parties = new ArrayList<Partie>(); //une liste des parties
    private Joueur joueur_courant;
    private  Partie partie_courante;

    //commencer le jeu
    public void jouer() {

    }

    public void chargerJoueurs(String filename)
    {
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                this.joueurs.add(new Joueur(data));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred While reading Players File.");
            e.printStackTrace();
        }
    }

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
        chargerJoueurs("Joueurs.txt");
        if(identifierJoueur(nom))
        {
            System.out.println(nom + " a ete indentifie avec succes");
        }
        else
        {
            System.out.println(nom + " est un nouveau joueur");
            this.joueurs.add(new Joueur(nom));
            try {
                FileWriter myWriter = new FileWriter("Joueurs.txt",true);
                myWriter.write(nom+"\n");
                myWriter.close();
            } catch (IOException e) {
                System.out.println("An error occurred While Writing in Players File.");
                e.printStackTrace();
            }
        }
        this.joueur_courant = new Joueur(nom);
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

    public void setJoueur_courant(Joueur joueur_courant) {
        this.joueur_courant = joueur_courant;
    }

    public void ajouterPartieCourante(Partie partie_courante) {
        this.partie_courante = partie_courante;
        this.parties.add(partie_courante);
    }

    public Joueur getJoueur_courant() {
        return joueur_courant;
    }

    public Partie getPartie_courante() {
        return partie_courante;
    }
}

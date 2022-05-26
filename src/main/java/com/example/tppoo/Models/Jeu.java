package com.example.tppoo.Models;
import java.io.*;
import java.util.*;
public class Jeu {

    public ArrayList<Joueur>joueurs = new ArrayList<Joueur>(); //une liste des joueurs
    public ArrayList<Partie> parties = new ArrayList<Partie>(); //une liste des parties

    //commencer le jeu
    public void jouer() {
        chargerJoueurs("Joueurs.txt");
        Scanner sc = new Scanner(System.in);
        System.out.print("Entrez votre nom : ");
        String nom = sc.nextLine();
        if(identifierJoueur(nom))
        {
            System.out.println(nom + " a ete indentifie avec succes");
        }
        else
        {
            System.out.println(nom + " est un nouveau joueur");
            this.joueurs.add(new Joueur(nom));
            try {
                FileWriter myWriter = new FileWriter("Joueurs.txt");
                myWriter.write(nom);
                myWriter.close();
            } catch (IOException e) {
                System.out.println("An error occurred While Writing in PLayers File.");
                e.printStackTrace();
            }
        }
    }

    public void chargerJoueurs(String filename)
    {
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
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
        for(int i=0;i<joueurs.size();i++)
        {
            System.out.println(joueurs.get(i).getNom());
            if(joueurs.get(i).getNom() == nom)
            {
                resultat = true;
                break;
            }
        }
        return resultat;
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

package com.example.tppoo.Models;
import javafx.scene.Scene;

import java.util.*;

public class Partie {

    private Joueur joueur;
    private boolean enCours;
    private boolean suspendu;
    private boolean enPause;
    private int id;
    private ArrayList<Image> images;
    private ArrayList<Definition> definitions;
    private Plateau plateau;

    public Partie(Joueur joueur, Scene scene) {
        this.joueur = joueur;
        this.enCours = true;
        this.enPause = false;
        this.suspendu = false;
        this.plateau = new Plateau();
        this.plateau.genererPlateau(scene);
        chargerDonnees("");
    }

    public void setEnPause(boolean enPause) {
        this.enPause = enPause;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    //charger les images, mots, definitions du fichier ou ils sont stockés
    public void chargerDonnees(String nomFichier) {}

    //placer les cases sur le plateau
    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public boolean isEnCours() {
        return enCours;
    }

    public boolean isSuspendu() {
        return suspendu;
    }

    public boolean isEnPause() {
        return enPause;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Image> getImages() {
        return images;
    }

    public ArrayList<Definition> getDefinitions() {
        return definitions;
    }

    public Plateau getPlateau() {
        return plateau;
    }
}
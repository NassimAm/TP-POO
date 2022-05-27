package com.example.tppoo.Models;
import com.example.tppoo.MainApplication;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

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
    private Dé dé1;
    private Dé dé2;

    public Partie(Joueur joueur, Scene scene) {
        this.joueur = joueur;
        this.enCours = true;
        this.enPause = false;
        this.suspendu = false;
        this.plateau = new Plateau();
        this.plateau.genererPlateau(scene);
        this.dé1 = new Dé();
        this.dé2 = new Dé();
        chargerDonnees("");
    }

    public void rechargerPartie(Scene scene)
    {
        Label player_name = (Label) scene.lookup("#player_name_label");
        player_name.setText(MainApplication.jeu.getJoueur_courant().getNom());

        javafx.scene.image.Image image1 = new javafx.scene.image.Image(Objects.requireNonNull(MainApplication.class.getResourceAsStream("images/dice/dice" + Integer.toString(dé1.getValeur()) + ".png")));
        ImageView dice_image1 = (ImageView) scene.lookup("#dice_image1");
        dice_image1.setImage(image1);
        javafx.scene.image.Image image2 = new javafx.scene.image.Image(Objects.requireNonNull(MainApplication.class.getResourceAsStream("images/dice/dice" + Integer.toString(dé2.getValeur()) + ".png")));
        ImageView dice_image2 = (ImageView) scene.lookup("#dice_image2");
        dice_image2.setImage(image2);

        this.plateau.chargerPlateauSurScene(scene);
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

    public Dé getDé1() {
        return dé1;
    }

    public Dé getDé2() {
        return dé2;
    }
}

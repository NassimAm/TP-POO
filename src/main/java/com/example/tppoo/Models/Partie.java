package com.example.tppoo.Models;
import com.example.tppoo.MainApplication;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.*;

public class Partie implements Serializable {
    private ArrayList<Image> images;
    private ArrayList<Definition> definitions;
    private Plateau plateau;
    private Dé dé1;
    private Dé dé2;

    private int joueur_position;
    private int prochaine_position;
    private int score;

    private int id;
    private static int nbParties = 0;



    public Partie(Scene scene) {
        this.plateau = new Plateau();
        this.dé1 = new Dé();
        this.dé2 = new Dé();
        this.definitions = new ArrayList<Definition>();
        this.images = new ArrayList<Image>();
        chargerDonnees("GameData.txt");
        this.plateau.genererPlateau(scene,this.definitions,this.images);
        this.id = nbParties;
        nbParties++;
        //this.plateau.genererPlateauPerso(scene,this.definitions,this.images);
    }

    public void rechargerPartie(Scene scene)
    {
        Joueur joueur = MainApplication.jeu.getJoueur_courant();
        int prochaine_position = joueur.getProchainePosition();
        Label player_name = (Label) scene.lookup("#player_name_label");
        player_name.setText(joueur.getNom());

        Label score_label = (Label) scene.lookup("#score_label");
        score_label.setText(Integer.toString(score));

        Label position_label = (Label) scene.lookup("#position_label");
        position_label.setText(Integer.toString(joueur_position));

        javafx.scene.image.Image image1 = new javafx.scene.image.Image(Objects.requireNonNull(MainApplication.class.getResourceAsStream("images/dice/dice" + Integer.toString(dé1.getValeur()) + ".png")));
        ImageView dice_image1 = (ImageView) scene.lookup("#dice_image1");
        dice_image1.setImage(image1);
        javafx.scene.image.Image image2 = new javafx.scene.image.Image(Objects.requireNonNull(MainApplication.class.getResourceAsStream("images/dice/dice" + Integer.toString(dé2.getValeur()) + ".png")));
        ImageView dice_image2 = (ImageView) scene.lookup("#dice_image2");
        dice_image2.setImage(image2);

        if(joueur_position != prochaine_position)
        {
            HBox hint_container = (HBox) scene.lookup("#hint_container");
            hint_container.setVisible(true);

            Label hint_text = (Label) scene.lookup("#hint_text");
            hint_text.setText("Click on the case N°"+Integer.toString(prochaine_position));

            Button roll_button = (Button) scene.lookup("#roll_button");
            roll_button.setDisable(true);
        }

        joueur.setPosition(joueur_position);
        joueur.setProchainePosition(prochaine_position);
        joueur.setScore(score);
        this.plateau.chargerPlateauSurScene(joueur_position,prochaine_position, scene);
    }

    public int traiterPosition(int joueur_position,Scene scene) throws DestinationException
    {
        boolean auto = false;
        if(joueur_position>=100)
        {
            joueur_position = 99 - (joueur_position-99);
            auto = true;
        }

        var button = (Button)scene.lookup("#case"+Integer.toString(joueur_position));
        Platform.runLater(
                () -> {
                    button.setGraphic(this.getPlateau().generateJoueurImage());
                }
        );


        if(joueur_position == 99) {
            System.out.println("Vous avez fini la partie");
            System.out.println("Score : "+Integer.toString(MainApplication.jeu.getJoueur_courant().getScore()));
        }
        return joueur_position;
    }

    //charger les images, mots, definitions du fichier ou ils sont stockés
    public void chargerDonnees(String nomFichier) {
        try {
            File myObj = new File(nomFichier);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] line = data.substring(2,data.length()).split(" ",2);
                if(data.charAt(0) == 'I')
                {
                    this.images.add(new Image(line[1],line[0]));
                }
                else
                {
                    this.definitions.add(new Definition(line[1],line[0]));
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred While reading Players File.");
            e.printStackTrace();
        }
    }

    //placer les cases sur le plateau
    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
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

    public void setJoueur_position(int joueur_position) {
        this.joueur_position = joueur_position;
    }

    public void setProchaine_position(int prochaine_position) {
        this.prochaine_position = prochaine_position;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getId() {
        return id;
    }
}

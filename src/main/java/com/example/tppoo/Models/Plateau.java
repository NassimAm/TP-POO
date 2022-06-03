package com.example.tppoo.Models;
import com.example.tppoo.MainApplication;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.*;
public class Plateau {
    public final int nbCases = 100; //le nombre total de cases dans le plateau
    private Case cases[] = new Case[nbCases];

    public void genererPlateau(Scene scene,ArrayList<Definition> definitions,ArrayList<Image> images)
    {
        
        cases[0] = new CaseDepart(0);
        //Case Bonus
        for(int i=0;i<5;i++)
        {
            Random rand = new Random();
            int position = rand.nextInt(nbCases-1)+1;
            cases[position] = new CaseBonus(position);
        }
        //Case Image
        for(int i=0;i<5;i++)
        {
            Boolean trouv = false;
            int position = 0;
            while(!trouv)
            {
                Random rand = new Random();
                position = rand.nextInt(nbCases-1)+1;
                if((cases[position] == null)&&(position != nbCases-2))
                {
                    trouv = true;
                }
            }

            if(images.size()>=4)
            {
                cases[position] = new CaseImage(position,"",null);
            }
            else {
                cases[position] = new CaseParcours(position);
            }
        }
        //Case Malus
        for(int i=0;i<5;i++)
        {
            Boolean trouv = false;
            int position = 0;
            while(!trouv)
            {
                Random rand = new Random();
                position = rand.nextInt(nbCases-1)+1;
                if(cases[position] == null)
                {
                    if(position-2>0)
                    {
                        if(cases[position-2] != null)
                        {
                            if((cases[position-2].getCouleur() != Couleur.VERTE) && (cases[position-2].getCouleur() != Couleur.ROSE))
                            {
                                trouv = true;
                            }
                        }
                        else
                        {
                            trouv = true;
                        }
                    }
                    else
                    {
                        trouv = true;
                    }
                }
            }
            cases[position] = new CaseMalus(position);
            
        }
        //Case Definition
        for(int i=0;i<5;i++)
        {
            Boolean trouv = false;
            int position = 0;
            while(!trouv)
            {
                Random rand = new Random();
                position = rand.nextInt(nbCases-1)+1;
                if((cases[position] == null)&&(position != nbCases-3))
                {
                    trouv = true;
                }
            }

            cases[position] = new CaseDefinition(position,null);
            
        }
        //Case Saut
        for(int i=0;i<5;i++)
        {
            Boolean trouv = false;
            int position = 0;
            while(!trouv)
            {
                Random rand = new Random();
                position = rand.nextInt(nbCases-1)+1;
                if(cases[position] == null)
                {
                    trouv = true;
                }
            }
            cases[position] = new CaseSaut(position);
        }

        //Case Parcours
        for(int i = 1; i<nbCases-1;i++) {
            if (cases[i] == null) {
                cases[i] = new CaseParcours(i);
            }
        }

        cases[nbCases-1] = new CaseFin(nbCases-1);

        chargerPlateauSurScene(0,scene);
    }

    public void chargerPlateauSurScene(int position_joueur,Scene scene)
    {
        //Generer son interface ==================================================
        for(int i=0;i<this.nbCases;i++)
        {
            var button = (Button)scene.lookup("#case"+Integer.toString(i));
            //System.out.println(plateau.cases[i].getCouleur());
            button.getStyleClass().clear();
            button.getStyleClass().add(getClassNameFromCouleur(this.cases[i].getCouleur()));
            if(i == position_joueur)
                button.setGraphic(generateJoueurImage());

        }
    }

    public String getClassNameFromCouleur(Couleur couleur)
    {
        switch(couleur) {
            case JAUNE: {
                return "case-jaune";
            }
            case VERTE: {
                return "case-verte";
            }
            case ROUGE: {
                return "case-rouge";
            }
            case ORANGE: {
                return "case-orange";
            }
            case BLEU: {
                return "case-bleue";
            }
            case ROSE: {
                return "case-rose";
            }
            case NOIR: {
                return "case-noire";
            }
            default: {
                return "case-standard";
            }
        }
    }

    public VBox generateJoueurImage()
    {
        VBox vbox = new VBox();
        ImageView imageview = new ImageView();
        imageview.setFitHeight(49.0);
        imageview.setFitWidth(33.0);
        imageview.setPickOnBounds(true);
        imageview.setPreserveRatio(true);
        javafx.scene.image.Image image = new javafx.scene.image.Image(String.valueOf(MainApplication.class.getResource("images/Golden_star.svg.png")));
        imageview.setImage(image);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(0,0,5,0));
        vbox.getChildren().add(imageview);

        return vbox;
    }

    /*public String getJoueurClassNameFromCouleur(Couleur couleur)
    {
        switch(couleur) {
            case JAUNE: {
                return "case-jaune-joueur";
            }
            case VERTE: {
                return "case-verte-joueur";
            }
            case ROUGE: {
                return "case-rouge-joueur";
            }
            case ORANGE: {
                return "case-orange-joueur";
            }
            case BLEU: {
                return "case-bleue-joueur";
            }
            case ROSE: {
                return "case-rose-joueur";
            }
            case NOIR: {
                return "case-noire";
            }
            default: {
                return "case-standard-joueur";
            }
        }
    }*/

    public void genererPlateauPerso(Scene scene,ArrayList<Definition> definitions,ArrayList<Image> images)
    {
        for(int i=0;i<nbCases;i++)
        {
            this.cases[i] = new CaseParcours(i);
        }

        this.cases[4] = new CaseFin(4);

        chargerPlateauSurScene(0,scene);
    }

    public Case[] getCases() {
        return cases;
    }
}

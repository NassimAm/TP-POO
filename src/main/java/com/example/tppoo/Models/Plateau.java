package com.example.tppoo.Models;
import javafx.scene.Scene;

import java.util.*;
public class Plateau {
    public final int nbCases = 100; //le nombre total de cases dans le plateau
    public Case cases[] = new Case[nbCases];

    public void genererPlateau(Scene scene)
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
                if(cases[position] == null)
                {
                    trouv = true;
                }
            }
            cases[position] = new CaseImage(position,"",null);
            
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
                if(cases[position] == null)
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

        chargerPlateauSurScene(scene);
    }

    public void chargerPlateauSurScene(Scene scene)
    {
        //Generer son interface ==================================================
        for(int i=0;i<this.nbCases;i++)
        {
            var button = scene.lookup("#case"+Integer.toString(i));
            //System.out.println(plateau.cases[i].getCouleur());
            button.getStyleClass().clear();
            switch(this.cases[i].getCouleur()) {
                case JAUNE: {
                    button.getStyleClass().add("case-jaune");
                    break;
                }
                case VERTE: {
                    button.getStyleClass().add("case-verte");
                    break;
                }
                case ROUGE: {
                    button.getStyleClass().add("case-rouge");
                    break;
                }
                case ORANGE: {
                    button.getStyleClass().add("case-orange");
                    break;
                }
                case BLEU: {
                    button.getStyleClass().add("case-bleue");
                    break;
                }
                case ROSE: {
                    button.getStyleClass().add("case-rose");
                    break;
                }
                case NOIR: {
                    button.getStyleClass().add("case-noire");
                    break;
                }
                default:
                {
                    button.getStyleClass().add("case-standard");
                    break;
                }
            }
        }
    }
}

package com.example.tppoo.Models;
import javafx.scene.Scene;

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
                if(cases[position] == null)
                {
                    trouv = true;
                }
            }

            ArrayList<Image> copyimages = new ArrayList<Image>(images);
            Image[] caseimages = new Image[4];

            int images_index = 0;
            if(copyimages.size()>=4)
            {
                for(int j=0;j<4;j++)
                {
                    Random rand = new Random();
                    images_index = rand.nextInt(copyimages.size());
                    caseimages[j] = copyimages.get(images_index);
                    copyimages.remove(images_index);
                }

                Random rand = new Random();
                images_index = rand.nextInt(caseimages.length);
                cases[position] = new CaseImage(position,caseimages[images_index].getMot(),caseimages);
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
                if(cases[position] == null)
                {
                    trouv = true;
                }
            }

            Random rand = new Random();
            cases[position] = new CaseDefinition(position,definitions.get(rand.nextInt(definitions.size())));
            
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
            var button = scene.lookup("#case"+Integer.toString(i));
            //System.out.println(plateau.cases[i].getCouleur());
            button.getStyleClass().clear();
            if(i == position_joueur)
                button.getStyleClass().add(getJoueurClassNameFromCouleur(this.cases[i].getCouleur()));
            else
                button.getStyleClass().add(getClassNameFromCouleur(this.cases[i].getCouleur()));
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

    public String getJoueurClassNameFromCouleur(Couleur couleur)
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
    }

    public void genererPlateauPerso(Scene scene,ArrayList<Definition> definitions,ArrayList<Image> images)
    {
        for(int i=0;i<nbCases;i++)
        {
            this.cases[i] = new CaseParcours(i);
        }

        ArrayList<Image> copyimages = new ArrayList<Image>(images);
        Image[] caseimages = new Image[4];

        int images_index = 0;
        if(copyimages.size()>=4)
        {
            for(int j=0;j<4;j++)
            {
                Random rand = new Random();
                images_index = rand.nextInt(copyimages.size());
                caseimages[j] = copyimages.get(images_index);
                copyimages.remove(images_index);
            }

            Random rand = new Random();
            images_index = rand.nextInt(caseimages.length);
            cases[4] = new CaseImage(4,caseimages[images_index].getMot(),caseimages);
        }
        else {
            cases[4] = new CaseParcours(4);
        }

        Random rand = new Random();

        this.cases[8] = new CaseDefinition(8,definitions.get(rand.nextInt(definitions.size())));
        this.cases[12] = new CaseSaut(12);

        chargerPlateauSurScene(0,scene);
    }

    public Case[] getCases() {
        return cases;
    }
}

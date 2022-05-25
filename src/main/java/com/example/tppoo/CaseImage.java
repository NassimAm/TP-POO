package com.example.tppoo;
public class CaseImage extends CaseQuestion {
    private Image[] images;
    private String mot;
    final int bonusPoints = 10;
    final int bonusCases = 2;
    private boolean justesseReponse = false;
    
    CaseImage(int numero,String mot,Image[] images)
    {
        this.setCouleur(Couleur.ROSE);
        this.setNumero(numero);
        this.mot = mot;
        this.images = images;
    }

    public void CreerFenetre(){};

    public boolean verifierReponse(int index)
    {
        return (mot == this.images[index].getMot()); 
    }

    protected void succes(Joueur joueur){
        this.ajouterPoints(joueur, bonusPoints);
        this.ajouterCases(joueur, bonusCases);
    };
    protected void echec(Joueur joueur){};

    public void action(Joueur joueur){
        if(justesseReponse)
            succes(joueur);
        else
            echec(joueur);
    }
}

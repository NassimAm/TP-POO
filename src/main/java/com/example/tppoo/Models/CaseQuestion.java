package com.example.tppoo.Models;
public abstract class CaseQuestion extends Case {
    protected abstract void CreerFenetre();
    protected abstract void succes(Joueur joueur);
    protected abstract void echec(Joueur joueur);
}

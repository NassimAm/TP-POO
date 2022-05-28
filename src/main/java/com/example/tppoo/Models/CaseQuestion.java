package com.example.tppoo.Models;

import javafx.scene.Scene;

public abstract class CaseQuestion extends Case {
    protected abstract void CreerFenetre();
    protected abstract void succes(Joueur joueur);
    protected abstract void echec(Joueur joueur);
}

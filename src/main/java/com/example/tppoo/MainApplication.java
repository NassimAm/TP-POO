package com.example.tppoo;

import com.example.tppoo.Models.Dé;
import com.example.tppoo.Models.Jeu;
import com.example.tppoo.Models.Plateau;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Jeu jeu = new Jeu();
        Dé dé1 = new Dé();
        Dé dé2 = new Dé();

        Plateau plateau = new Plateau();
        plateau.genererPlateau();

        for(int i=0;i<plateau.nbCases;i++)
        {
            System.out.println(plateau.cases[i].getCouleur());
        }

        jeu.jouer();
        launch();
    }
}
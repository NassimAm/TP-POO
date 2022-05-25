package com.example.tppoo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
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
        launch();
    }
}
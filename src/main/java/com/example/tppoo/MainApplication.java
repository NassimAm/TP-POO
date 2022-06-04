package com.example.tppoo;

import com.example.tppoo.Models.Dé;
import com.example.tppoo.Models.Jeu;
import com.example.tppoo.Models.Plateau;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.*;

public class MainApplication extends Application {
    public static Jeu jeu;
    @Override
    public void start(Stage stage){
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("login-view.fxml"));
        Scene scene = null;
        try
        {
            scene = new Scene(fxmlLoader.load());
        }
        catch(IOException e)
        {
            e.printStackTrace();
            System.out.println("Couldn't load FXML file");
        }

        ObjectInputStream in;
        try {
            in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File("jeu.dat"))));
            try {
                jeu = (Jeu)in.readObject();
                in.close();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        catch (FileNotFoundException e) {
            jeu = new Jeu();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Dé dé1 = new Dé();
        Dé dé2 = new Dé();

        Plateau plateau = new Plateau();
        //plateau.genererPlateau(scene);


        //jeu.jouer();
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
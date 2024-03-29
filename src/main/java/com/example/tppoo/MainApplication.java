package com.example.tppoo;

import com.example.tppoo.Models.Dé;
import com.example.tppoo.Models.Jeu;
import com.example.tppoo.Models.Plateau;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.*;

public class MainApplication extends Application {
    public static Jeu jeu;
    private static boolean testmode = false;
    private static Stage stage;
    @Override
    public void start(Stage stage){
        MainApplication.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("acceuil-view.fxml"));
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
        stage.getIcons().add(new Image(String.valueOf(MainApplication.class.getResource("icon/Peak_Logo_Icon.png"))));
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop()
    {
        jeu.sauvegarder();
    }

    public static void main(String[] args) {
        launch();
    }

    public static Stage getStage() {return stage;}

    public static boolean getTestMode()
    {
        return testmode;
    }

    public static void setTestMode(boolean b)
    {
        testmode = b;
    }
}
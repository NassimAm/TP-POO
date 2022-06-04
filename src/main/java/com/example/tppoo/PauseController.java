package com.example.tppoo;

import com.example.tppoo.Models.Joueur;
import com.example.tppoo.Models.Partie;
import com.example.tppoo.Models.Plateau;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class PauseController {

    @FXML
    private Button quit_button;

    @FXML
    private Button resume_button;

    @FXML
    private Button save_button;

    @FXML
    void continuer(ActionEvent event) {
        Stage stage = null;
        Parent newscene = null;

        if(event.getSource() == resume_button)
        {
            stage = (Stage) resume_button.getScene().getWindow();
            try {
                newscene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("game-view.fxml")));
            }
            catch(IOException e)
            {
                e.printStackTrace();
                System.out.println("couldn't load FXML File");
            }

            Scene scene = new Scene(newscene);
            Joueur joueur = MainApplication.jeu.getJoueur_courant();
            Partie partie_courante = MainApplication.jeu.getPartie_courante();
            partie_courante.setJoueur_position(joueur.getPosition());
            partie_courante.setProchaine_position(joueur.getProchainePosition());
            partie_courante.setScore(joueur.getScore());
            partie_courante.rechargerPartie(scene);
            stage.setScene(scene);
            stage.setTitle("English Game");
            stage.show();
        }
    }

    @FXML
    void quitter(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("mainmenu-view.fxml"));
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

        MainApplication.jeu.sauvegarder();

        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void sauvegarder(ActionEvent event) {

        Stage newStage = (Stage) save_button.getScene().getWindow();
        try {
            Parent newRoot = (new FXMLLoader(MainApplication.class.getResource("mainmenu-view.fxml"))).load();
            Scene scene = new Scene(newRoot);

            MainApplication.jeu.sauvegarder();

            newStage.setScene(scene);
            newStage.setTitle("MainMenu");
            newStage.show();

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

}

package com.example.tppoo;

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
            MainApplication.jeu.getPartie_courante().rechargerPartie(MainApplication.jeu.getJoueur_courant().getPosition(),scene);
            stage.setScene(scene);
            stage.setTitle("English Game");
            stage.show();
        }
    }

    @FXML
    void quitter(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void sauvegarder(ActionEvent event) {

    }

}

package com.example.tppoo;

import com.example.tppoo.Models.Partie;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.*;
import java.util.Objects;

public class MainMenuController {

    @FXML
    private Button continue_game_button;

    @FXML
    private Button leaderboards_button;

    @FXML
    private Button new_game_button;

    @FXML
    private Button quit_menu_button;

    @FXML
    void continer_partie(ActionEvent event) {

    }

    @FXML
    void nouvelle_partie(ActionEvent event) {
        Stage stage = null;
        Parent newscene = null;

        if(event.getSource() == new_game_button)
        {
            stage = (Stage) new_game_button.getScene().getWindow();
            try {
                newscene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("game-view.fxml")));
            }
            catch(IOException e)
            {
                e.printStackTrace();
                System.out.println("couldn't load FXML File");
            }

            Scene scene = new Scene(newscene);
            Partie partie = new Partie(scene);
            MainApplication.jeu.ajouterPartieCourante(partie);


            Label player_name = (Label) scene.lookup("#player_name_label");
            player_name.setText(MainApplication.jeu.getJoueur_courant().getNom());
            stage.setScene(scene);
            stage.setTitle("English Game");
            stage.show();
        }
    }

    @FXML
    void quitter(ActionEvent event) {
        MainApplication.jeu.sauvegarder();
        Platform.exit();
    }

    @FXML
    void vers_classements(ActionEvent event) {

    }

}

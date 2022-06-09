package com.example.tppoo;

import com.example.tppoo.Models.Joueur;
import com.example.tppoo.Models.Partie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class EndController {

    @FXML
    private Label meilleur_score_label;

    @FXML
    private Button ok_button;

    @FXML
    private Label score_label;

    @FXML
    void okay(ActionEvent event) {
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

        Stage substage = (Stage)ok_button.getScene().getWindow();
        Stage stage = (Stage)substage.getOwner();
        stage.setScene(scene);
        stage.setTitle("Main Menu");

        substage.close();
        Joueur joueur = MainApplication.jeu.getJoueur_courant();
        Partie partie_courante = MainApplication.jeu.getPartie_courante();
        joueur.reset();
        joueur.getParties().remove(partie_courante);

        stage.show();
    }

}
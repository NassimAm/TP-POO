package com.example.tppoo.Models;

import com.example.tppoo.MainApplication;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class CaseFin extends Case {
    CaseFin(int numero)
    {
        this.setNumero(numero);
        this.setCouleur(Couleur.NOIR);
    }

    public void action(Joueur joueur)
    {
        Platform.runLater(
                () -> {
                    Stage stage = new Stage();
                    FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("end-view.fxml"));
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

                    stage.setTitle("End Game");

                    Label score_label = (Label)scene.lookup("#score_label");
                    score_label.setText(Integer.toString(MainApplication.jeu.getJoueur_courant().getScore()));

                    Label meilleur_score_label = (Label)scene.lookup("#meilleur_score_label");
                    meilleur_score_label.setText(Integer.toString(MainApplication.jeu.getJoueur_courant().getMeilleurScore()));

                    stage.getIcons().add(new Image(String.valueOf(MainApplication.class.getResource("icon/Peak_Logo_Icon.png"))));
                    stage.setScene(scene);

                    stage.initOwner(MainApplication.getStage());
                    stage.initModality(Modality.WINDOW_MODAL);

                    stage.show();
                }
        );
    }
}

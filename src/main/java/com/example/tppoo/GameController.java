package com.example.tppoo;

import com.example.tppoo.Models.DestinationException;
import com.example.tppoo.Models.Dé;
import com.example.tppoo.Models.Joueur;
import com.example.tppoo.Models.Partie;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class GameController {

    @FXML
    private ImageView dice_image1;

    @FXML
    private ImageView dice_image2;

    @FXML
    private Button pause_button;

    @FXML
    private Button roll_button;

    @FXML
    private Label error_text;

    @FXML
    void pause(ActionEvent event) {
        Stage stage = null;
        Parent newscene = null;

        if(event.getSource() == pause_button)
        {
            stage = (Stage) pause_button.getScene().getWindow();
            try {
                newscene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("pause-view.fxml")));
            }
            catch(IOException e)
            {
                e.printStackTrace();
                System.out.println("couldn't load FXML File");
            }

            Scene scene = new Scene(newscene);
            stage.setScene(scene);
            stage.setTitle("English Game");
            stage.show();
        }
    }

    @FXML
    void roll(ActionEvent event) {
        roll_button.setDisable(true);

        Thread thread = new Thread(){
            public void run(){
                try {
                    Dé dé1 = MainApplication.jeu.getPartie_courante().getDé1();
                    Dé dé2 = MainApplication.jeu.getPartie_courante().getDé2();
                    for (int i = 0; i < 10; i++) {
                        dé1.GenererCoup();
                        dé2.GenererCoup();
                        Image image1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/dice/dice" + Integer.toString(dé1.getValeur()) + ".png")));
                        dice_image1.setImage(image1);
                        Image image2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/dice/dice" + Integer.toString(dé2.getValeur()) + ".png")));
                        dice_image2.setImage(image2);
                        Thread.sleep(50);
                    }
                    roll_button.setDisable(false);
                    Joueur joueur = MainApplication.jeu.getJoueur_courant();
                    int save_position = joueur.getPosition();
                    joueur.deplacer(dé1.getValeur()+ dé2.getValeur());
                    try {
                        int joueur_position = MainApplication.jeu.getPartie_courante().traiterPosition(joueur.getPosition(),joueur.getA_clique(),joueur.getCase_clique(),roll_button.getScene());
                        joueur.setPosition(joueur_position);

                        var button = roll_button.getScene().lookup("#case"+Integer.toString(save_position));
                        button.getStyleClass().clear();
                        button.getStyleClass().add(MainApplication.jeu.getPartie_courante().getPlateau().getClassNameFromCouleur(MainApplication.jeu.getPartie_courante().getPlateau().getCases()[save_position].getCouleur()));

                        Label score_label = (Label) roll_button.getScene().lookup("#score_label");
                        Label position_label = (Label) roll_button.getScene().lookup("#position_label");

                        Platform.runLater(
                                () -> {
                                    score_label.setText(Integer.toString(MainApplication.jeu.getJoueur_courant().getScore()));
                                    position_label.setText(Integer.toString(MainApplication.jeu.getJoueur_courant().getPosition()));
                                }
                        );

                        save_position = joueur.getPosition();
                        MainApplication.jeu.getPartie_courante().getPlateau().getCases()[joueur_position].action(joueur);

                        while(joueur_position != joueur.getPosition())
                        {
                            Thread.sleep(500);
                            joueur_position = MainApplication.jeu.getPartie_courante().traiterPosition(joueur.getPosition(),joueur.getA_clique(),joueur.getCase_clique(),roll_button.getScene());
                            joueur.setPosition(joueur_position);

                            button = roll_button.getScene().lookup("#case"+Integer.toString(save_position));
                            button.getStyleClass().clear();
                            button.getStyleClass().add(MainApplication.jeu.getPartie_courante().getPlateau().getClassNameFromCouleur(MainApplication.jeu.getPartie_courante().getPlateau().getCases()[save_position].getCouleur()));

                            Platform.runLater(
                                    () -> {
                                        score_label.setText(Integer.toString(MainApplication.jeu.getJoueur_courant().getScore()));
                                        position_label.setText(Integer.toString(MainApplication.jeu.getJoueur_courant().getPosition()));
                                    }
                            );

                            save_position = joueur.getPosition();
                            MainApplication.jeu.getPartie_courante().getPlateau().getCases()[joueur_position].action(joueur);
                        }
                    }
                    catch (DestinationException e)
                    {
                        e.printStackTrace();
                        error_text.setText("Cliquez sur la bonne case, la case "+Integer.toString(joueur.getPosition()));
                    }
                    error_text.setText("");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        thread.start();
    }

}

package com.example.tppoo;

import com.example.tppoo.Models.Joueur;
import com.example.tppoo.Models.Partie;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;

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
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("listparties-view.fxml"));
        Scene scene = null;
        try
        {
            scene = new Scene(fxmlLoader.load());
            Button button = (Button)event.getSource();
            Stage stage = (Stage)button.getScene().getWindow();
            stage.setScene(scene);
            stage.show();

            VBox vbox = (VBox)scene.lookup("#list_parties");
            ArrayList<Partie> parties = MainApplication.jeu.getJoueur_courant().getParties();
            for(int i=0;i<parties.size();i++)
            {
                Button bt = new Button();
                bt.setPrefWidth(vbox.getPrefWidth());
                bt.setText("Partie "+Integer.toString(i+1));
                bt.setFont(new Font("Segoe UI Semibold",14));
                bt.getStyleClass().add("pause-button3");
                bt.setId("partie"+Integer.toString(i));
                bt.setPadding(new Insets(10));
                int finalI = i;
                bt.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("game-view.fxml"));
                        Scene scene = null;
                        try
                        {
                            scene = new Scene(fxmlLoader.load());
                            Joueur joueur = MainApplication.jeu.getJoueur_courant();
                            Partie partie = joueur.getParties().get(finalI);
                            partie.rechargerPartie(scene);
                            MainApplication.jeu.setPartie_courante(partie);
                            stage.setScene(scene);
                            stage.show();
                        }
                        catch(IOException e)
                        {
                            e.printStackTrace();
                            System.out.println("Couldn't load FXML file");
                        }
                    }
                });
                vbox.getChildren().add(bt);
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
            System.out.println("Couldn't load FXML file");
        }
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
            Joueur joueur = MainApplication.jeu.getJoueur_courant();
            joueur.reset();
            Partie partie = new Partie(scene);
            MainApplication.jeu.ajouterPartieCourante(partie);


            Label player_name = (Label) scene.lookup("#player_name_label");
            player_name.setText(joueur.getNom());
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
    void vers_classements(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("listplayers-view.fxml"));
        Scene scene = null;
        try
        {
            scene = new Scene(fxmlLoader.load());
            Button button = (Button)event.getSource();
            Stage stage = (Stage)button.getScene().getWindow();
            stage.setScene(scene);
            stage.show();

            VBox vbox = (VBox)scene.lookup("#list_players");
            ArrayList<Joueur> joueurs = MainApplication.jeu.getJoueurs();
            Collections.sort(joueurs);
            for (Joueur joueur : joueurs) {
                HBox hbox = new HBox();
                hbox.setAlignment(Pos.CENTER);
                hbox.setPrefWidth(540);
                hbox.setPrefHeight(38);
                hbox.setSpacing(20);
                hbox.setPadding(new Insets(7, 0, 7, 0));
                hbox.getStyleClass().add("player-label");

                Label namelabel = new Label();
                namelabel.setFont(new Font("Segoe UI Semibold", 16));
                namelabel.setText(joueur.getNom());

                HBox hbox2 = new HBox();
                hbox2.setAlignment(Pos.CENTER);
                hbox2.setPrefWidth(53);
                hbox2.setPrefHeight(26);
                hbox2.getStyleClass().add("player-score");

                Label scorelabel = new Label();
                scorelabel.setFont(new Font("Segoe UI Semibold", 14));
                scorelabel.setText(Integer.toString(joueur.getMeilleurScore()));
                hbox2.getChildren().add(scorelabel);

                hbox.getChildren().add(namelabel);
                hbox.getChildren().add(hbox2);

                vbox.getChildren().add(hbox);
            }

        }
        catch(IOException e)
        {
            e.printStackTrace();
            System.out.println("Couldn't load FXML file");
        }
    }

}

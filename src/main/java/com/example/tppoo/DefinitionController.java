package com.example.tppoo;

import com.example.tppoo.Models.Case;
import com.example.tppoo.Models.CaseDefinition;
import com.example.tppoo.Models.DestinationException;
import com.example.tppoo.Models.Joueur;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class DefinitionController {

    @FXML
    private Label definition_label;

    @FXML
    private TextField input_answer;

    @FXML
    private Button submit_button;

    @FXML
    private HBox word_hint;

    @FXML
    void submit(ActionEvent event) {
        Joueur joueur = MainApplication.jeu.getJoueur_courant();
        CaseDefinition case_definition = (CaseDefinition) MainApplication.jeu.getPartie_courante().getPlateau().getCases()[joueur.getPosition()];
        Stage stage = (Stage)(submit_button.getScene().getWindow());
        Scene scene = stage.getOwner().getScene();
        stage.close();

        Thread thread = new Thread(){
            public void run(){

                int save_position = joueur.getPosition();
                boolean reponse = case_definition.verifierReponse(input_answer.getText().substring(0,word_hint.getChildren().size()),joueur);

                Button button;
                Label score_label = (Label) scene.lookup("#score_label");
                Label position_label = (Label) scene.lookup("#position_label");
                Label hint_text = (Label) scene.lookup("#hint_text");
                HBox hint_container = (HBox) scene.lookup("#hint_container");
                Platform.runLater(
                        () -> {
                            if(reponse)
                            {
                                hint_text.setText("Good Answer !");
                                hint_text.setTextFill(Color.GREEN);
                            }
                            else
                            {
                                hint_text.setText("Wrong Answer !");
                                hint_text.setTextFill(Color.RED);
                            }
                        }
                );
                hint_container.setVisible(true);

                while(joueur.getPosition() != save_position)
                {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    button = (Button)scene.lookup("#case"+Integer.toString(save_position));
                    Button finalButton = button;
                    Platform.runLater(
                            () -> {
                                finalButton.setGraphic(null);
                            }
                    );

                    int joueur_position = 0;
                    try {
                        joueur_position = MainApplication.jeu.getPartie_courante().traiterPosition(joueur.getPosition(),scene);
                    } catch (DestinationException e) {
                        throw new RuntimeException(e);
                    }
                    joueur.setPosition(joueur_position);

                    Platform.runLater(
                            () -> {
                                score_label.setText(Integer.toString(MainApplication.jeu.getJoueur_courant().getScore()));
                                position_label.setText(Integer.toString(MainApplication.jeu.getJoueur_courant().getPosition()));
                            }
                    );

                    save_position = joueur.getPosition();
                    MainApplication.jeu.getPartie_courante().getPlateau().getCases()[joueur_position].action(joueur);
                }

                if(joueur.getPosition() == save_position)
                {
                    Platform.runLater(
                            () -> {
                                score_label.setText(Integer.toString(MainApplication.jeu.getJoueur_courant().getScore()));
                            }
                    );
                }
                joueur.setProchainePosition(joueur.getPosition());
            }
        };

        thread.start();
    }

}

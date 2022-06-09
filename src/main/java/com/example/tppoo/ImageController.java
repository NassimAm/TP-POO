package com.example.tppoo;

import com.example.tppoo.Models.CaseImage;
import com.example.tppoo.Models.DestinationException;
import com.example.tppoo.Models.Joueur;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ImageController {

    @FXML
    private FlowPane image_container0;

    @FXML
    private FlowPane image_container1;

    @FXML
    private FlowPane image_container2;

    @FXML
    private FlowPane image_container3;

    @FXML
    private Label word_label;

    @FXML
    void click0(MouseEvent event) {
        clickImage(0);
    }

    @FXML
    void click1(MouseEvent event) {
        clickImage(1);
    }

    @FXML
    void click2(MouseEvent event) {
        clickImage(2);
    }

    @FXML
    void click3(MouseEvent event) {
        clickImage(3);
    }

    private void clickImage(int index)
    {
        Joueur joueur = MainApplication.jeu.getJoueur_courant();
        CaseImage case_image = (CaseImage) MainApplication.jeu.getPartie_courante().getPlateau().getCases()[joueur.getPosition()];
        Stage stage = (Stage)(word_label.getScene().getWindow());
        Scene scene = stage.getOwner().getScene();
        stage.close();

        Thread thread = new Thread(){
            public void run(){

                int save_position = joueur.getPosition();
                boolean reponse = case_image.verifierReponse(index,joueur);

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

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
                case_image.verifierReponse(index,joueur);

                Button button;
                Label score_label = (Label) scene.lookup("#score_label");
                Label position_label = (Label) scene.lookup("#position_label");
                while(joueur.getPosition() != save_position)
                {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    button = (Button)scene.lookup("#case"+Integer.toString(save_position));
                    button.getStyleClass().clear();
                    button.getStyleClass().add(MainApplication.jeu.getPartie_courante().getPlateau().getClassNameFromCouleur(MainApplication.jeu.getPartie_courante().getPlateau().getCases()[save_position].getCouleur()));

                    int joueur_position = 0;
                    try {
                        joueur_position = MainApplication.jeu.getPartie_courante().traiterPosition(joueur.getPosition(),joueur.getA_clique(),joueur.getCase_clique(),scene);
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
            }
        };

        thread.start();
    }
}

package com.example.tppoo;

import com.example.tppoo.Models.Dé;
import com.example.tppoo.Models.Partie;
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
                System.out.println("Thread Running");
                try {
                    for (int i = 0; i < 10; i++) {
                        Dé dé1 = MainApplication.jeu.getPartie_courante().getDé1();
                        Dé dé2 = MainApplication.jeu.getPartie_courante().getDé2();
                        dé1.GenererCoup();
                        dé2.GenererCoup();
                        Image image1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/dice/dice" + Integer.toString(dé1.getValeur()) + ".png")));
                        dice_image1.setImage(image1);
                        Image image2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/dice/dice" + Integer.toString(dé2.getValeur()) + ".png")));
                        dice_image2.setImage(image2);
                        Thread.sleep(50);
                    }
                    roll_button.setDisable(false);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        thread.start();
    }

}

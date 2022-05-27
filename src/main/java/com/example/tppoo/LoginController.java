package com.example.tppoo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoginController {

    @FXML
    private TextField input_player_name;

    @FXML
    private Button login_play_button;

    @FXML
    void login_play(ActionEvent event) {
        Stage stage = null;
        Parent newscene = null;

        if(event.getSource() == login_play_button)
        {
            stage = (Stage) login_play_button.getScene().getWindow();
            try {
                newscene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainmenu-view.fxml")));
            }
            catch(IOException e)
            {
                e.printStackTrace();
                System.out.println("couldn't load FXML File");
            }

            MainApplication.jeu.enregistrerJoueur(input_player_name.getText());

            Scene scene = new Scene(newscene);
            stage.setScene(scene);
            stage.setTitle("English Game");
            stage.show();
        }
    }

}

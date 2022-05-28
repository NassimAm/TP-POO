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
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class CaseDefinition extends CaseQuestion {
    private Definition definition;
    final int bonusPoints = 20;
    final int bonusCases = 4;
    final int malusPoints = -10;
    
    CaseDefinition(int numero,Definition definition)
    {
        this.setCouleur(Couleur.BLEU);
        this.setNumero(numero);
        this.definition = definition;
    }

    public void CreerFenetre(){
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("definition-view.fxml"));
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

        stage.setTitle("Definition Game");

        Label definition_label = (Label) scene.lookup("#definition_label");
        definition_label.setText(this.definition.getDefinition());

        HBox word_hint = (HBox) scene.lookup("#word_hint");
        for(int i=0;i<this.definition.getMot().length();i++)
        {
            FlowPane flowPane = new FlowPane();
            flowPane.setAlignment(Pos.CENTER);
            flowPane.setColumnHalignment(HPos.CENTER);
            flowPane.setRowValignment(VPos.CENTER);
            flowPane.setMinHeight(70);
            flowPane.setMinWidth(59);
            flowPane.setPrefHeight(70);
            flowPane.setPrefWidth(59);
            flowPane.setStyle("-fx-border-color: #FFFFFF; -fx-border-width: 5; -fx-border-style: hidden hidden solid hidden;");
            Label label = new Label();
            label.setText("");
            label.setFont(new Font("Segoe UI Semibold",50));
            flowPane.getChildren().add(label);
            HBox.setMargin(flowPane,new Insets(0,5,10,5));
            word_hint.getChildren().add(flowPane);
        }

        TextField input_answer =(TextField)scene.lookup("#input_answer");
        input_answer.textProperty().addListener(
                (observableValue, oldValue, newValue) -> {
                    for(int i=0;i<Math.min(newValue.length(),word_hint.getChildren().size());i++)
                    {
                        FlowPane flowPane = (FlowPane) (word_hint.getChildren().get(i));
                        Label label = (Label)(flowPane.getChildren().get(0));
                        label.setText(Character.toString(newValue.charAt(i)).toUpperCase());
                    }

                    for(int i=newValue.length();i<word_hint.getChildren().size();i++)
                    {
                        FlowPane flowPane = (FlowPane) (word_hint.getChildren().get(i));
                        Label label = (Label)(flowPane.getChildren().get(0));
                        label.setText("");
                    }
                }
        );

        stage.setScene(scene);

        stage.initOwner(MainApplication.jeu.getPartie_courante().getCurrentScene().getWindow());
        stage.initModality(Modality.WINDOW_MODAL);

        stage.show();
    };

    public void verifierReponse(String reponse,Joueur joueur)
    {
        if(Objects.equals(reponse.toUpperCase(), this.definition.getMot().toUpperCase()))
            succes(joueur);
        else
            echec(joueur);
    }

    protected void succes(Joueur joueur){
        this.ajouterPoints(joueur, bonusPoints);
        this.ajouterCases(joueur, bonusCases);
    };
    protected void echec(Joueur joueur){
        this.ajouterPoints(joueur, malusPoints);
    };

    public void action(Joueur joueur){
        Platform.runLater(
                this::CreerFenetre
        );
    }
}

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
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class CaseImage extends CaseQuestion {
    private Image[] images;
    private String mot;
    final int bonusPoints = 10;
    final int bonusCases = 2;
    private boolean justesseReponse = false;
    
    CaseImage(int numero,String mot,Image[] images)
    {
        this.setCouleur(Couleur.ROSE);
        this.setNumero(numero);
        this.mot = mot;
        this.images = images;
    }

    public void CreerFenetre(){
        GenererQuestion();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("image-view.fxml"));
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

        stage.setTitle("Image Game");

        Label word_label = (Label) scene.lookup("#word_label");
        word_label.setText(this.mot);

        FlowPane flowPane;
        for(int i=0;i<this.images.length;i++)
        {
            flowPane = (FlowPane) scene.lookup("#image_container"+Integer.toString(i));
            ImageView imageView = (ImageView) flowPane.getChildren().get(0);

            Platform.runLater(
                    () -> {
                        Rectangle clip = new Rectangle(imageView.getFitWidth(), imageView.getFitHeight());
                        clip.setArcWidth(20);
                        clip.setArcHeight(20);
                        imageView.setClip(clip);
                    }
            );

            imageView.setImage(new javafx.scene.image.Image(String.valueOf(MainApplication.class.getResource(this.images[i].getLien()))));
        }
        stage.getIcons().add(new javafx.scene.image.Image(String.valueOf(MainApplication.class.getResource("icon/Peak_Logo_Icon.png"))));
        stage.setScene(scene);

        stage.initOwner(MainApplication.getStage());
        stage.initModality(Modality.WINDOW_MODAL);

        stage.show();
    };

    public void GenererQuestion(){
        ArrayList<Image> images = MainApplication.jeu.getPartie_courante().getImages();
        ArrayList<Image> copyimages = new ArrayList<Image>(images);
        Image[] caseimages = new Image[4];

        int images_index = 0;
        if(copyimages.size()>=4)
        {
            for(int j=0;j<4;j++)
            {
                Random rand = new Random();
                images_index = rand.nextInt(copyimages.size());
                caseimages[j] = copyimages.get(images_index);
                copyimages.remove(images_index);
            }

            Random rand = new Random();
            images_index = rand.nextInt(caseimages.length);
            this.mot = caseimages[images_index].getMot();
            this.images = caseimages;
        }
    }

    public void verifierReponse(int index,Joueur joueur)
    {
        if(Objects.equals(mot, this.images[index].getMot()))
            succes(joueur);
        else
            echec(joueur);
    }

    protected void succes(Joueur joueur){
        this.ajouterPoints(joueur, bonusPoints);
        this.ajouterCases(joueur, bonusCases);
    };
    protected void echec(Joueur joueur){};

    public void action(Joueur joueur){
        Platform.runLater(
                this::CreerFenetre
        );
    }
}

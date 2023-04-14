package com.example.xixixi;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ChangeUnitParams {

    public static void display(int spiderI) {
        Stage window=new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Choose an object to change");
        window.setMinWidth(250);
        window.setMinHeight(500);

   ArrayList<String> paramsToChange = Main.getParamsToChange( spiderI );


        VBox layout = new VBox(11);
        //layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);


        Label nameLabel=new Label();
        nameLabel.setText("Name:");
        TextField nameText = new TextField();
        nameText.setText(paramsToChange.get(0));

        Label healthLabel=new Label();
        healthLabel.setText("Health:");
        TextField healthText = new TextField();
        healthText.setText(paramsToChange.get(1));

        Label damageLabel=new Label();
        damageLabel.setText("Damage:");
        TextField damageText = new TextField();
        damageText.setText(paramsToChange.get(2));

        Label sideLabel= new Label();
        sideLabel.setText("Side:");
        TextField sideText = new TextField();
        sideText.setText(paramsToChange.get(3));

        Label xLabel=new Label();
        xLabel.setText("X:");
        TextField xText = new TextField();
        xText.setText(paramsToChange.get(4));

        Label yLabel=new Label();
        yLabel.setText("Y:");
        TextField yText = new TextField();
        yText.setText(paramsToChange.get(5));


        Button okButton=new Button("OK");
        okButton.setOnAction(e->{

            String sName= nameText.getText();
            String sDamage = damageText.getText();
            String sHealth= healthText.getText();
            String sSide = sideText.getText();
            String sX = xText.getText();
            String sY = yText.getText();

            Line life = new Line(Double.parseDouble(sX),Double.parseDouble(sY+15),Double.parseDouble(sX+sHealth),Double.parseDouble(sY+15));
            life.setStrokeWidth(6);
            life.setStroke(Color.GREEN);


            Main.createNewSpider(sName, sDamage,sHealth,sSide, sX, sY);
            window.close();});
        layout.getChildren().addAll(nameLabel, nameText, healthLabel, healthText,damageLabel,damageText,sideLabel,sideText, xLabel, xText, yLabel, yText, okButton);
        Scene scene=new Scene(layout,303,300);
        window.setScene(scene);
        window.showAndWait();
    }
}



package com.example.xixixi;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class UnitParams {
    public static void displayUnit(double x, double y) {

    Stage window=new Stage();
    window.initModality(Modality.APPLICATION_MODAL);
    window.setTitle("Enter new unit parameters!");
    window.setMinWidth(400);
    window.setMinHeight(500);


    VBox layout = new VBox(11);
    //layout.getChildren().addAll(label, closeButton);
    layout.setAlignment(Pos.CENTER);


    Label nameLabel=new Label();
    nameLabel.setText("Name:");
    TextField nameText = new TextField();

    Label damageLabel=new Label();
    damageLabel.setText("Damage:");
    TextField damageText = new TextField();

    Label healthLabel=new Label();
    healthLabel.setText("Health:");
    TextField healthText = new TextField();

    Label sideLabel= new Label();
    sideLabel.setText("Side:");
    TextField sideText = new TextField();


    Label xLabel=new Label();
    xLabel.setText("X:");
    TextField xText = new TextField();
    xText.setText(Double.toString(x));

    Label yLabel=new Label();
    yLabel.setText("Y:");
    TextField yText = new TextField();
    yText.setText(Double.toString(y));
        Button okButton=new Button("OK");
        okButton.setOnAction(e->{

            String sName= nameText.getText();
            String sDamage = damageText.getText();
            String sHealth= healthText.getText();
            String sSide = sideText.getText();
            String sX = xText.getText();
            String sY = yText.getText();

            Main.createNewSpider(sName,sDamage,sHealth,sSide, sX, sY);

            window.close();});
            layout.getChildren().addAll(nameLabel, nameText, healthLabel, healthText,damageLabel,damageText,sideLabel,sideText, xLabel, xText, yLabel, yText, okButton);
            Scene scene=new Scene(layout,303,300);
            window.setScene(scene);
            window.showAndWait();
        }
}

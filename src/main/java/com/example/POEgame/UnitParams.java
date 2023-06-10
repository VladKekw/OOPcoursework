package com.example.POEgame;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.util.Random;

public class UnitParams {

    static Random random = new Random();
    static boolean side;

    private static ToggleGroup gr;

    public static void displayUnit(double x, double y) {

            Stage window=new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Enter new unit parameters!");
            window.setMinWidth(400);
            window.setMinHeight(700);

            VBox layout = new VBox(11);
            //layout.getChildren().addAll(label, closeButton);
            layout.setAlignment(Pos.CENTER);

            Label nameLabel=new Label();
            nameLabel.setText("Name:");
            TextField nameText = new TextField();

            String[] someNames =  Main.createSuggestedNames();
            nameText.setText(someNames[random.nextInt(3)]);

            Label damageLabel=new Label();
            damageLabel.setText("Damage:");
            TextField damageText = new TextField();

            Label healthLabel=new Label();
            healthLabel.setText("Health:");
            TextField healthText = new TextField();

            Label xLabel=new Label();
            xLabel.setText("X:");
            TextField xText = new TextField();
            xText.setText(Double.toString(x));

            Label yLabel=new Label();
            yLabel.setText("Y:");
            TextField yText = new TextField();
            yText.setText(Double.toString(y));

            gr = new ToggleGroup();

            RadioButton rad = new RadioButton("Radiant side");
            rad.setToggleGroup(gr);
            rad.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event){
                    side = true;
                }
                            });
            RadioButton dire = new RadioButton("Dire side");
            dire.setToggleGroup(gr);
            dire.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event){
                    side = false;
                }
            });

            Label cLabel = new Label();
            cLabel.setText("Choose type of the Unit:");
            ComboBox comboBox = new ComboBox<>();
            comboBox.getItems().addAll(
                    "1. Spider",
                    "2. Golem",
                    "3. Ice Elemental"
            );


            Button okButton=new Button("OK");
            okButton.setOnAction(e->{

                String sName= nameText.getText();
                String sDamage = damageText.getText();
                String sHealth= healthText.getText();
                String sSide = Boolean.toString(side);
                String sX = xText.getText();
                String sY = yText.getText();
                /*String sA =Boolean.toString(active);*/


                if(comboBox.getValue()=="1. Spider"){
                Main.createNewSpider(sName,sDamage,sHealth,sSide, sX, sY, someNames);}

                if(comboBox.getValue()=="2. Golem"){
                    Main.createNewGolem(sName,sDamage,sHealth,sSide, sX, sY, someNames);
                }
                if(comboBox.getValue()=="3. Ice Elemental"){
                    Main.createNewIceElemental(sName,sDamage,sHealth,sSide, sX, sY, someNames);
                }
                window.close();});
                layout.getChildren().addAll(nameLabel, nameText, healthLabel, healthText,
                        damageLabel,damageText,
                        xLabel, xText, yLabel, yText,rad,dire,
                        cLabel,comboBox, okButton);
                Scene scene=new Scene(layout,303,300);
                window.setScene(scene);
                window.showAndWait();
            }

}

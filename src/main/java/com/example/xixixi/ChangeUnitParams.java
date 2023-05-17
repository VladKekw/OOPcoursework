package com.example.xixixi;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;


public class ChangeUnitParams {
    private static ToggleGroup gr;
    static boolean side;
    static Random random = new Random();

    public static void display(int index,String s) {
        Stage window=new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Choose an object to change");
        window.setMinWidth(250);
        window.setMinHeight(500);
        VBox layout = new VBox(11);
        //layout.getChildren().addAll(label, closeButton);
        String[] spiderNames =  Main.createSuggestedNames();
        layout.setAlignment(Pos.BASELINE_LEFT);
        ArrayList<String>  SpiderParamsToChange= new ArrayList<>();
        ArrayList<String>  GolemParamsToChange= new ArrayList<>();
        ArrayList<String> IceElementalParamsToChange = new ArrayList<>();
        if(s.equals("spider")){
            SpiderParamsToChange = Main.SpiderGetParamsToChange( index);
            Label nameLabel=new Label();
            nameLabel.setText("Name:");
            TextField nameText = new TextField();
            nameText.setText(SpiderParamsToChange.get(0));

            Label healthLabel=new Label();
            healthLabel.setText("Health:");
            TextField healthText = new TextField();
            healthText.setText(SpiderParamsToChange.get(1));

            Label damageLabel=new Label();
            damageLabel.setText("Damage:");
            TextField damageText = new TextField();
            damageText.setText(SpiderParamsToChange.get(2));



            Label xLabel=new Label();
            xLabel.setText("X:");
            TextField xText = new TextField();
            xText.setText(SpiderParamsToChange.get(4));
            gr = new ToggleGroup();


            Label yLabel=new Label();
            yLabel.setText("Y:");
            TextField yText = new TextField();
            yText.setText(SpiderParamsToChange.get(5));
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
            Button okButton=new Button("OK");
            okButton.setOnAction(e->{

                String sName= nameText.getText();
                String sDamage = damageText.getText();
                String sHealth= healthText.getText();
                String sSide = Boolean.toString(side);
                String sX = xText.getText();
                String sY = yText.getText();

                Line life = new Line(Double.parseDouble(sX),Double.parseDouble(sY+15),Double.parseDouble(sX+sHealth),Double.parseDouble(sY+15));
                life.setStrokeWidth(6);
                life.setStroke(Color.GREEN);


                Main.changeSpider(sName, sDamage,sHealth,sSide, sX, sY,spiderNames,index-1 );
                window.close();});
            layout.getChildren().addAll(nameLabel, nameText, healthLabel, healthText,
                    damageLabel,damageText, xLabel, xText, yLabel, yText,
                    rad,dire, okButton);
        }
        if(s.equals("golem")){
            GolemParamsToChange = Main.GolemGetParamsToChange2(index) ;
            Label nameLabel=new Label();
            nameLabel.setText("Name:");
            TextField nameText = new TextField();
            nameText.setText(GolemParamsToChange.get(0));

            Label healthLabel=new Label();
            healthLabel.setText("Health:");
            TextField healthText = new TextField();
            healthText.setText(GolemParamsToChange.get(1));

            Label damageLabel=new Label();
            damageLabel.setText("Damage:");
            TextField damageText = new TextField();
            damageText.setText(GolemParamsToChange.get(2));



            Label xLabel=new Label();
            xLabel.setText("X:");
            TextField xText = new TextField();
            xText.setText(GolemParamsToChange.get(4));
            gr = new ToggleGroup();


            Label yLabel=new Label();
            yLabel.setText("Y:");
            TextField yText = new TextField();
            yText.setText(GolemParamsToChange.get(5));
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
            Button okButton=new Button("OK");
            okButton.setOnAction(e->{

                String sName= nameText.getText();
                String sDamage = damageText.getText();
                String sHealth= healthText.getText();
                String sSide = Boolean.toString(side);
                String sX = xText.getText();
                String sY = yText.getText();

                Line life = new Line(Double.parseDouble(sX),Double.parseDouble(sY+15),Double.parseDouble(sX+sHealth),Double.parseDouble(sY+15));
                life.setStrokeWidth(6);
                life.setStroke(Color.GREEN);


                Main.changeGolem(sName, sDamage,sHealth,sSide, sX, sY,spiderNames,index-1 );
                window.close();});
            layout.getChildren().addAll(nameLabel, nameText, healthLabel, healthText,
                    damageLabel,damageText, xLabel, xText, yLabel, yText,
                    rad,dire, okButton);
        }
        if(s.equals("iceElemental")){
            IceElementalParamsToChange = Main.IceElementalGetParamsToChange3( index);
            Label nameLabel=new Label();
            nameLabel.setText("Name:");
            TextField nameText = new TextField();
            nameText.setText(IceElementalParamsToChange.get(0));

            Label healthLabel=new Label();
            healthLabel.setText("Health:");
            TextField healthText = new TextField();
            healthText.setText(IceElementalParamsToChange.get(1));

            Label damageLabel=new Label();
            damageLabel.setText("Damage:");
            TextField damageText = new TextField();
            damageText.setText(IceElementalParamsToChange.get(2));



            Label xLabel=new Label();
            xLabel.setText("X:");
            TextField xText = new TextField();
            xText.setText(IceElementalParamsToChange.get(4));
            gr = new ToggleGroup();


            Label yLabel=new Label();
            yLabel.setText("Y:");
            TextField yText = new TextField();
            yText.setText(IceElementalParamsToChange.get(5));
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
            Button okButton=new Button("OK");
            okButton.setOnAction(e->{

                String sName= nameText.getText();
                String sDamage = damageText.getText();
                String sHealth= healthText.getText();
                String sSide = Boolean.toString(side);
                String sX = xText.getText();
                String sY = yText.getText();

                Line life = new Line(Double.parseDouble(sX),Double.parseDouble(sY+15),Double.parseDouble(sX+sHealth),Double.parseDouble(sY+15));
                life.setStrokeWidth(6);
                life.setStroke(Color.GREEN);


                Main.changeIceElemental(sName, sDamage,sHealth,sSide, sX, sY,spiderNames,index-1 );
                window.close();});
            layout.getChildren().addAll(nameLabel, nameText, healthLabel, healthText,
                    damageLabel,damageText, xLabel, xText, yLabel, yText,
                    rad,dire, okButton);
        }





        Scene scene=new Scene(layout,303,300);
        window.setScene(scene);
        window.showAndWait();
    }
}



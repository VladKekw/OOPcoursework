package com.example.xixixi;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ChooseUnit {
    public static void choose(){
    Stage window=new Stage();
    window.initModality(Modality.APPLICATION_MODAL);
    window.setTitle("Choose unit!");
    window.setMinWidth(200);
    window.setMinHeight(200);
        ArrayList<String> spiders = Main.getSpiderNames();
        ArrayList<String> golems = Main.getGolemNames();
        ArrayList<String> elems = Main.getElemNames();
        Label label= new Label("Choose an object to change");

        ComboBox cBox = new ComboBox();

        int countS=1;
        for( String s:spiders ) {

            cBox.getItems().add(countS +" "+ s);
            countS++;
        }
        int countG=1;
        for(String g: golems){

            cBox.getItems().add(countG +" "+ g);
            countG++;
        }
        int countI=1;
        for (String i: elems ){
            cBox.getItems().add(countI +" "+ i);
            countI++;
        }
        ComboBox c = new ComboBox();
        for(Spider s: Main.uni){
            c.getItems().add(s);
        }

        VBox layout = new VBox(11);
        layout.setAlignment(Pos.CENTER);

        Button okButton=new Button("OK");
        okButton.setOnAction(e->{
            if( cBox.getValue()!=null ) {
                String[] strChoice = cBox.getValue().toString().split(" ");
                if (cBox.getValue().toString().contains("Spider")) {
                    ChangeUnitParams.display(Integer.parseInt(strChoice[0]), "spider");
                }

                if (cBox.getValue().toString().contains("Golem")) {
                    ChangeUnitParams.display(Integer.parseInt(strChoice[0]), "golem");
                }
                if(cBox.getValue().toString().contains("IceElemental")){
                    ChangeUnitParams.display(Integer.parseInt(strChoice[0]), "iceElemental");
                }
            }

            window.close();});
        layout.getChildren().addAll(label,cBox,c,okButton);
        Scene scene=new Scene(layout,303,300);
        window.setScene(scene);
        window.showAndWait();


    }

}

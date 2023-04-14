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
    public static void choose(double x, double y){
    Stage window=new Stage();
    window.initModality(Modality.APPLICATION_MODAL);
    window.setTitle("Choose unit!");
    window.setMinWidth(200);
    window.setMinHeight(200);
        ArrayList<String> spiders = Main.getNames();

        Label label= new Label("Choose an object to change");

        ComboBox cBox = new ComboBox();

        int count=1;
        for( String s:spiders ) {
            cBox.getItems().add(Integer.toString(count)+" "+ s);
            count++;
        }

        VBox layout = new VBox(11);
        //layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        Button okButton=new Button("OK");
        okButton.setOnAction(e->{
            if( cBox.getValue()!=null ){
                String[] strChoice= cBox.getValue().toString().split(" ");
                //System.out.println("Message from String.split:"+ Arrays.toString(strChoice));

                ChangeUnitParams.display(Integer.parseInt(strChoice[0])-1 );


            }

            window.close();});
        layout.getChildren().addAll(label,cBox,okButton);
        Scene scene=new Scene(layout,303,300);
        window.setScene(scene);
        window.showAndWait();

    }
}

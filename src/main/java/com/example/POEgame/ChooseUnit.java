package com.example.POEgame;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ChooseUnit {
    public static void choose() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Choose unit!");
        window.setMinWidth(200);
        window.setMinHeight(200);
        Label label = new Label("Choose an object to change");

        int count = 1;
        ComboBox c = new ComboBox();
        for (Spider s : Main.uni) {
            c.getItems().add(count + " " +s);
            count++;
        }

        VBox layout = new VBox(11);
        layout.setAlignment(Pos.CENTER);

        Button okButton = new Button("OK");
        okButton.setOnAction(e -> {
            if (c.getValue() != null) {
                String[] strChoice = c.getValue().toString().split(" ");
                if (c.getValue().toString().contains("Spider")) {
                    ChangeUnitParams.display(Integer.parseInt(strChoice[0]), "spider");
                }
                if (c.getValue().toString().contains("Golem")) {
                    ChangeUnitParams.display(Integer.parseInt(strChoice[0]), "golem");
                }
                if (c.getValue().toString().contains("IceElemental")) {
                    ChangeUnitParams.display(Integer.parseInt(strChoice[0]), "iceElemental");
                }
            }
            window.close();
        });
        layout.getChildren().addAll(label, c, okButton);
        Scene scene = new Scene(layout, 303, 300);
        window.setScene(scene);
        window.showAndWait();
    }
}

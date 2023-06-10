package com.example.POEgame;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Tower {
    public Group towerGroup;
    Rectangle rect;

    public enum State {
        NEUTRAL, DIRE, RADIANT;
    }
    private State state;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Tower (double posX, double posY, String n, ArrayList<Spider> list){

        Image towerImage = new Image(Main.class.getResource("tower.png").toString(),90,90,false,false);
        ImageView towerImgv = new ImageView(towerImage);
        towerImgv.setX(posX+30);
        towerImgv.setY(posY+45);
        rect = new Rectangle(posX, posY, 150,150);
        rect.setStroke(Color.WHITE);
        rect.setFill(Color.TRANSPARENT);
        rect.setStrokeWidth(2);

        Label name = new Label(n);
        name.setFont(new Font("Arial", 18));
        name.setTextFill(Color.WHITE);
        name.setTranslateX(posX-3);
        name.setTranslateY(posY-30);
        state= State.NEUTRAL;

        towerGroup = new Group(rect,towerImgv,name);

        towerGroup.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Stage window = new Stage();
                window.initModality(Modality.APPLICATION_MODAL);
                window.setTitle("List of units in this tower");
                window.setMinWidth(200);
                window.setMinHeight(100);
                VBox layout = new VBox(20);
                Label stateLabel = new Label(getState().name());
                stateLabel.setLayoutX(100);
                stateLabel.setLayoutY(120);
                layout.setAlignment(Pos.CENTER);
                ComboBox cBox = new ComboBox();
                cBox.setLayoutX(100);
                cBox.setLayoutY(25);
                int count = 1;
                for (Spider spider : list) {
                    cBox.getItems().add(count + " " + spider);
                }
                Button okButton = new Button("OK");
                okButton.setLayoutX(100);
                okButton.setLayoutY(65);

                okButton.setOnAction(e -> {
                    String[] str = cBox.getValue().toString().split(" ");
                    System.out.println(str[0]);
                    for (int i = 0; i < list.size(); i++) {
                        Spider spider = list.get(Integer.parseInt(str[0]) - 1);
                        spider.setSafe(false);
                        spider.getGroupP().setLayoutX(Double.parseDouble(spider.getPosX()) +70.0);
                        spider.getGroupP().setLayoutY(Double.parseDouble(spider.getPosY()) +70.0);
                        spider.getGroup(spider).setVisible(true);

                        list.remove(Integer.parseInt(str[0]) - 1);
                    }
                    window.close();
                });
                layout.getChildren().addAll(cBox, okButton,stateLabel);
                Scene scene = new Scene(layout, 300, 150);
                window.setScene(scene);
                window.showAndWait();

            }
        });
    }

    public void towerPaint() {
        if (state==State.DIRE){
            this.rect.setStroke(Color.RED);
        }
        else if (state==State.RADIANT){
            this.rect.setStroke(Color.YELLOW);
        }
    }

}


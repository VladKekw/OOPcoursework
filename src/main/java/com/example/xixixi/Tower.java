package com.example.xixixi;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class Tower {
    public Group towerGroup;
    private ArrayList<Spider> towerList = new ArrayList<>(8);
    public enum state {
        NEUTRAL, DIRE, RADIANT;
    }

    public Tower (double posX, double posY, String n){
        Image towerImage = new Image(Main.class.getResource("tower.png").toString(),90,90,false,false);
        ImageView towerImgv = new ImageView(towerImage);
        towerImgv.setX(posX+30);
        towerImgv.setY(posY+45);
        Rectangle rect = new Rectangle(posX, posY, 150,150);
        rect.setStroke(Color.WHITE);
        rect.setFill(Color.TRANSPARENT);

        Label name = new Label(n);
        name.setFont(new Font("Arial", 18));
        name.setTextFill(Color.WHITE);
        name.setTranslateX(posX-3);
        name.setTranslateY(posY-30);
        towerGroup = new Group(rect,towerImgv,name);


    }
}

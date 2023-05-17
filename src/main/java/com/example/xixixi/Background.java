package com.example.xixixi;


import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.FileNotFoundException;

public class Background {
    Image img;
    public  static ImageView iv;
    Label l;
    private static Group BackgroundGroup;
    public static Rectangle border;
    static Group gr = new Group();
    static Group g;
    Background() throws FileNotFoundException {
        img = new Image(Main.class.getResource("background.jpg").toString(),2600,2000,false,false);
        ImageView imageView = new ImageView(img);
        imageView.setFitWidth(2600);
        imageView.setFitHeight(2000);
        this.iv = imageView;


        border = new Rectangle(0,0,this.iv.getFitWidth(), this.iv.getFitHeight());
        border.setFill(Color.TRANSPARENT);
        border.setStroke(Color.RED);

        BackgroundGroup = new Group(this.iv,border);
        Group sub = new Group(BackgroundGroup);

        this.g = sub;
    }

    public static Group getBackGrp() {
        return g;
    }
}

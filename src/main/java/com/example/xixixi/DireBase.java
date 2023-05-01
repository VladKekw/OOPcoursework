package com.example.xixixi;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.io.FileNotFoundException;

public class DireBase {
    private Image i;
    private ImageView iv;
    private Rectangle r;
    public Group g;
    private Label l;

    DireBase(double x, double y) throws FileNotFoundException {

        i = new Image(Main.class.getResource("dire.png").toString(),100,100,false,false);
        ImageView imageView = new ImageView(i);
        imageView.setX(x);
        imageView.setY(y);
        imageView.setPreserveRatio(true);
        this.iv = imageView;

        this.l = new Label("Dire");
        this.l.setFont(new Font("Arial", 20));
        this.l.setTextFill(Color.WHITE);
        this.l.setTranslateX(x+33);
        this.l.setTranslateY(y+100);

        this.r = new Rectangle(x-28, y-30, 150, 150);
        this.r.setFill(Color.TRANSPARENT);
        this.r.setStroke(Color.WHITE);


        this.g = new Group();
        this.g.getChildren().addAll(this.r, this.iv, this.l);

    }
}
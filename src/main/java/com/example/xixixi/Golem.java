package com.example.xixixi;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;


public class Golem extends Spider{

    Golem(int hp, double dmg,String n,String s, double posX, double posY, String[] golemS){

        super(hp,dmg,n,s,posX,posY,golemS);
        this.step = 20;
        this.i = new Image(Main.class.getResource("heavy.png").toString(),70,70,false,false);
        ImageView imgView = new ImageView(i);
        imgView.setX(x-3);
        imgView.setY(y+15);
        this.imageView=imgView;
        Circle c =new Circle(posX+35,posY+60,45);
        c.setStrokeWidth(2);
        if(active) {c.setFill(Color.YELLOW);}
        else{c.setFill(Color.LIGHTBLUE);}
        this.circle=c;

        life = new Line(x,y+15,x+(Integer.parseInt(getHealthPoint()))/1.55,y+15);
        life.setStrokeWidth(6);
        life.setStroke(Color.GREEN);
        Group gr = new Group(circle, imageView,name,sideLabel,life);
        this.g=gr;
        System.out.println("a golem has been created");
        Main.group.getChildren().addAll(g);
        g.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton().equals(MouseButton.PRIMARY))
                    for (Golem g: Main.cave){ switchActivation();}
            }
        });
    }


    public Golem(){

        super(100,100,"GigaGolem","",0,0,Main.bodya);
        Image image = new Image(Main.class.getResource("heavy.png").toString(),70,70,false,false);
        ImageView imageView1= new ImageView(image);
        this.g.relocate(20,20);
        System.out.println("A golem without parameters has been created!");
    }

    @Override
    public String toString() {
        return "Golem {" +
                "damage=" + getDamage() +
                ", healthPoint=" + getHealthPoint() +
                ", name=" + name.getText() +
                ", side=" + side +
                ", active=" + active +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Golem golem = (Golem) obj;
        return getDamage() == golem.getDamage() &&
                side==golem.side &&
                name.equals(golem.name) &&
                getHealthPoint() == golem.getHealthPoint();
    }
}

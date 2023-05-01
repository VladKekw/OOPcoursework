package com.example.xixixi;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;


public class Golem extends Spider{

    protected Group golemGroup = new Group();



    Golem(int hp, double dmg,String n,String s, double posX, double posY, String[] golemS){
        super(hp,dmg,n,s,posX,posY,golemS);
        this.i = new Image(Main.class.getResource("heavy.png").toString(),70,70,false,false);

        ImageView imgView = new ImageView(i);
        imgView.setX(x-3);
        imgView.setY(y+15);
        this.imageView=imgView;
        if(active) {
            circle = new Circle(posX+35,posY+60,45);
            circle.setStrokeWidth(2);
            circle.setFill(Color.YELLOW);
            /*switchActivation();*/
        }
        else{
            circle = new Circle(posX+35,posY+60,45);
            circle.setStrokeWidth(2);
            circle.setFill(Color.LIGHTBLUE);
        }
        this.x = posX;
        this.y = posY;
        if(s.equals("true")){side=true;} else if (s.equals("false")) {side=false;}
        else{}
        if(side){
            sideLabel=new Label("R:");
            sideLabel.setLayoutX(posX-20);
            sideLabel.setLayoutY(posY-6);
            sideLabel.setTextFill(Color.YELLOW);
            sideLabel.setFont(new Font(15));
        }
        else{sideLabel=new Label("D:");
            sideLabel.setLayoutX(posX-20);
            sideLabel.setLayoutY(posY-6);
            sideLabel.setTextFill(Color.RED);
            sideLabel.setFont(new Font(15));
        }

        name=new Label(n);
        name.setLayoutX(posX);
        name.setLayoutY(posY-6);
        name.setTextFill(Color.WHITE);
        name.setFont(new Font(15));

        life = new Line(x,y+15,x+(Integer.parseInt(getHealthPoint()))/1.55,y+15);
        life.setStrokeWidth(6);
        life.setStroke(Color.GREEN);
        Group gr = new Group(circle, imgView,name,sideLabel,life);
        golemGroup=gr;
        System.out.println("a golem has been created");
        golemGroup.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton().equals(MouseButton.PRIMARY))
                    for (Golem g: Main.cave){ switchActivation();}
            }
        });
    }
  /*  @Override
    public void switchActivation() {
        this.active = !this.active;
        if (this.active)
            circle.setFill(Color.YELLOW);
        else
            circle.setFill(Color.LIGHTBLUE);
    }*/
    public Golem(){

        super(100,100,"GigaGolem","",0,0,Main.bodya);
/*        this(100,100,"GigaGolem","",0,0,Main.bodya);*/
        Image img = new Image(Main.class.getResource("heavy.png").toString(),70,70,false,false);
        this.i = img;
        imageView = new ImageView(i);
        this.g.relocate(20,20);
        System.out.println("A golem without parameters has been created!");
    }

    @Override
    public String toString() {
        return "Golem{" +
                "damage=" + getDamage() +
                ", healthPoint=" + getHealthPoint() +
                ", name=" + name.getText() +
                ", side=" + side +
                ", active=" + active +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
/*    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Golem golem = (Golem) obj;
        return damage == golem.damage &&
                side==golem.side &&
                name.equals(golem.name) &&
                healthPoint == golem.healthPoint;
    }*/
}

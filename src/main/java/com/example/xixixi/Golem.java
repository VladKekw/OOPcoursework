package com.example.xixixi;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;


public class Golem extends Spider{

    Golem(int hp, double dmg,String n,String s, double posX, double posY, String[] golemS){

        super(hp,dmg,n,s,posX,posY,golemS);
        this.step = 25;
        this.i = new Image(Main.class.getResource("heavy.png").toString(),70,70,false,false);
        this.imageView.setImage(this.i);
        SurvivedCounter counter = new SurvivedCounter();
        imageView.setX(x-3);
        imageView.setY(y+15);
        init(counter,posX,posY);
        System.out.println("a golem has been created");

        this.g.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton().equals(MouseButton.PRIMARY))
                    for (Spider s: Main.uni){ switchActivation();}
            }
        });
    }

    public Golem(){

        super(100,100,"GigaGolem","",0,0,Main.bodya);
        Image image = new Image(Main.class.getResource("heavy.png").toString(),70,70,false,false);
        ImageView imageView1= new ImageView(image);
        SurvivedCounter counter = new SurvivedCounter();
        this.g.relocate(20,20);
        init(counter,0,0);
        System.out.println("A golem without parameters has been created!");
    }
    @Override
    public String toString() {
        return "Golem{" + "name=" + name.getText() +
                ", damage=" + getDamage() + ", health=" + getHealthPoint() + ", side="
                + side + ", x=" + getPosX() + ", y=" + getPosY()+ ", survived seconds"+
                getSurvived() + ", active=" + active + ", safe+"+getSafe() +'}';
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

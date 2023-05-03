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

public class IceElemental extends Golem{


    IceElemental(int hp, double dmg,String n,String s, double posX, double posY, String[] golemS){
        super(hp,dmg,n,s,posX,posY,golemS);
        this.i=new Image(Main.class.getResource("ice.png").toString(),80,80,false,false);
        ImageView imgView = new ImageView(i);
        imgView.setX(x-3);
        imgView.setY(y+15);
        this.imageView=imgView;
        Circle aura =new Circle(posX+35,posY+60, 70);
        aura.setFill(Color.TRANSPARENT);
        Circle c =new Circle(posX+35,posY+60,45);
        c.setStrokeWidth(2);
        if(active) {c.setFill(Color.YELLOW);}
        else{c.setFill(Color.LIGHTBLUE);}
        this.circle=c;

        life = new Line(x,y+15,x+(Integer.parseInt(getHealthPoint()))/1.55,y+15);
        life.setStrokeWidth(6);
        life.setStroke(Color.GREEN);
        Group gr = new Group(circle,aura, imageView,name,sideLabel,life);
        this.g=gr;
        System.out.println("an ice elemental has been created");
        Main.group.getChildren().addAll(g);
        g.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton().equals(MouseButton.PRIMARY))
                for (IceElemental ice : Main.river){ switchActivation();}
            }
        });

    }

    public IceElemental(){
        super(100,100,"FrozenElem","",0,0,Main.bodya);
        this.i=new Image(Main.class.getResource("ranged.png").toString(),75,75,false,false);
        ImageView elemImgView = new ImageView(i);
        this.g.relocate(20,20);
    }

    @Override
    public String toString() {
        return "IceElemental {" +
                ", damage"+ getDamage()+
                ", healthpoint"+getHealthPoint()+
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
        IceElemental iceElemental = (IceElemental) obj;
        return getDamage() == iceElemental.getDamage() &&
                side==iceElemental.side &&
                name.equals(iceElemental.name) &&
                getHealthPoint() == iceElemental.getHealthPoint();
    }
}

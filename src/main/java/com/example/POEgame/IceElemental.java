package com.example.POEgame;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class IceElemental extends Golem{


    IceElemental(int hp, double dmg,String n,String s, double posX, double posY, String[] golemS){
        super(hp,dmg,n,s,posX,posY,golemS);
        this.i=new Image(Main.class.getResource("ice.png").toString(),80,80,false,false);
        this.imageView.setImage(this.i);
        SurvivedCounter counter = new SurvivedCounter();
        imageView.setX(x-3);
        imageView.setY(y+15);


        init(counter,posX,posY,true);
        System.out.println("an ice elemental has been created");
        logger.log(this.toString());
        this.g.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton().equals(MouseButton.PRIMARY))
                for (Spider s : Main.uni){ switchActivation();}
            }
        });

    }
    public IceElemental(int healthPoint, double damage, String n, String s, double posX, double posY, String[] spiderN , boolean active, double counsec) {
        counter = new SurvivedCounter();
        counter.setSecondsSurvived((int)counsec);
        this.active =active;
        this.x = posX;
        this.y = posY;
        this.damage = damage;
        this.healthPoint = healthPoint;
        this.sideS = s;
        this.nameS = n;
        this.i = new Image(Main.class.getResource("ice.png").toString(), 75, 75, false, false);
        imageView.setImage(i);
        imageView.setX(x - 3);
        imageView.setY(y + 15);
        init(counter, posX, posY, true);
        predictTheWin();
        logger.log(this.toString());
        System.out.println("Customised ice elemental has been created");

        this.g.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY)) switchActivation();
            }
        });
    }

    public IceElemental(){
        super(100,100,"FrozenElem","",0,0,Main.bodya);
        this.i=new Image(Main.class.getResource("ranged.png").toString(),75,75,false,false);
        ImageView elemImgView = new ImageView(i);
        SurvivedCounter counter = new SurvivedCounter();
        this.g.relocate(20,20);
        init(counter,0,0,true);
        System.out.println(" ice elemental without parameters has been created");
        logger.log(this.toString());
    }
    @Override
    public void receiveDamageFromObjects(Spider target) {
        target.setHealthPoint((Integer.parseInt(target.getHealthPoint())) - 0.01);

    }

    @Override
    public String toString() {
        return "IceElemental{" + "name=" + name.getText() +
                ", damage=" + getDamage() + ", health=" + getHealthPoint() + ", side="
                + side + ", x=" + getPosX() + ", y=" + getPosY()+ ", survived seconds "+
                getSurvived() + ", active=" + active +", safe+"+getSafe() + '}';
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

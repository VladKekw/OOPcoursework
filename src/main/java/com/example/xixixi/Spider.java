package com.example.xixixi;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.Random;

public class Spider implements Cloneable, Comparable<Spider>, Comparator {
    protected AnchorPane scene;

    protected Label name;
    protected Line life;
    protected Label sideLabel;
    protected Circle c;
    protected Image i;
    protected Group g;

    protected static Image imageSpider;
    protected static ImageView imageView;

    protected Circle circle;
    private int step = 40;


    private String [] suggestedNames = new String[3];


    protected boolean side;
    protected boolean active=false;
    /*private Point2D point = newRANDpntCreator();*/
    protected static boolean to_Base = false;



    protected static boolean rnd_move = false;
    protected double x,y;
/*    static double newX;
    static double newY;*/


    private int healthPoint;

    private double damage;
    protected static Random random = new Random();
    public Group getGroupP(){
        return g;
    }

    public static boolean getRndMove() {
        return rnd_move;
    }
    public void setDamage(String dmg) {
        try {
            damage=Double.parseDouble(dmg);
        }
        catch (Exception e) {
            damage=0.0;
        }
    }
    public String[] getSuggestedNames(){
        return suggestedNames;
    }
    public String getSide() {if(side){return "Radiant";}else {return "Dire";} }
    public void setSide(String n){
        if (n.equals("Radiant")) {side = true;
        }else if (n.equals("Dire")){side=false;}
        else{System.out.println("You`ve entered something wrong!");}

    }
    public Group getGroup() {
        return g;
    }
    public boolean getActive(){
        if(active){return true;}
        else{return false;}
    }
    public void setActive(Boolean b){
        if(b){active=true;}
        else{active=false;}
    }
    public void setStep(int step){
        this.step =step;
    }
    public int getStep(){
        return this.step;
    }

    public String getDamage() {
        return Double.toString(damage);
    }
    public void setHealthPoint(int healthPoint) {
        this.healthPoint = healthPoint;
    }
    public String getHealthPoint(){
        return Integer.toString(healthPoint);
    }
    public void setName(String unitName) {
        name.setText(unitName);
    }
    public String getName() {return name.getText();}

    public String getX() {
        return Double.toString(x);
    }


    public void setX(String sX){
        try {
            x= Double.parseDouble(sX);
        }
        catch(Exception e){
            x=0.0;
        }
    }
    public String getY() {
        return Double.toString(y);
    }

    public void setY(String sY){
        try {
            x= Double.parseDouble(sY);
        }
        catch(Exception e){
            y=0.0;
        }
    }
    public static void SwitchTo_Base() {Spider.to_Base = !Spider.to_Base;}
    public static void SwitchRnd_Move() {Spider.rnd_move = !Spider.rnd_move;}
    public boolean isActive()
    {
        return active;
    }

    private int maxHealth = 100;

    static {
        System.out.println("Static initialization is complete!");
    }

    public Spider(int healthPoint, double damage,String n,String s, double posX, double posY, String[] spiderN){
        this.i = new Image(Main.class.getResource("common.png").toString(),75,75,false,false);
        circle = new Circle(posX+35,posY+60,45);
        circle.setStrokeWidth(2);
        if(active) {circle.setFill(Color.YELLOW);}
        else{circle.setFill(Color.LIGHTBLUE);}
        this.x = posX;
        this.y = posY;
        this.damage = damage;
        this.healthPoint=healthPoint;
        this.active=active;
        if(s.equals("true")){side=true;} else if (s.equals("false")) {side=false;}
        else{
            System.out.println("You`ve entered something wrong!");
        }
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

        life = new Line(x,y+15,x+(healthPoint)/1.55,y+15);
        life.setStrokeWidth(6);
        life.setStroke(Color.GREEN);

        imageView = new ImageView(i);
        imageView.setX(x-3);
        imageView.setY(y+15);

        Image img = new Image(Main.class.getResource("heavy.png").toString(),70,70,false,false);
        if(i.hashCode()==img.hashCode()){
            if(active) {
                circle = new Circle(posX+35,posY+60,45);
                circle.setStrokeWidth(2);
                circle.setFill(Color.YELLOW);
            }
            else{
                circle = new Circle(posX+35,posY+60,45);
                circle.setStrokeWidth(2);
                circle.setFill(Color.BLUE);
            }
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

            life = new Line(x,y+15,x+(healthPoint)/1.55,y+15);
            life.setStrokeWidth(6);
            life.setStroke(Color.GREEN);

            imageView = new ImageView(i);
            imageView.setX(x-3);
            imageView.setY(y+15);
        }


        g= new Group(circle, imageView,name,sideLabel,life);

        this.g.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton().equals(MouseButton.PRIMARY))
                    switchActivation();

            }

        });
        Main.group.getChildren().addAll(g);
        System.out.println("A spider has been created!");
    }
    public Spider(){
        this(100,100,"SPIDER","",0,0,Main.bodya);
        System.out.println("A spider without parameters has been created!");
    }

    public void switchActivation() {
        this.active = !this.active;
        if (this.active)
            this.circle.setFill(Color.YELLOW);
        else
            this.circle.setFill(Color.LIGHTBLUE);
    }
    public void moveLeft() {
        if (!active) return;
       /* if (g.getLayoutX() > 1 )*/{
            double y = g.getLayoutX() - step;
            g.setLayoutX(y);
        }
    }

    public void moveUp() {
        if (!active) return;
       /* if(g.getLayoutY()>1)*/ {
            double y = g.getLayoutY() - step;
            g.setLayoutY(y);
        }
    }

    public void moveRight() {
        if (!active) return;
       /* if(g.getLayoutX()<1920)*/ {
            double x = g.getLayoutX() + step;
            g.setLayoutX(x);
        }
    }

    public void moveDown() {
        if (!active) return;
        if(g.getLayoutY()<1000){
        double y = g.getLayoutY() + step;
        g.setLayoutY(y);
        }
    }
   /* public Point2D newRANDpntCreator() {
        Random rnd = new Random();
        int x = rnd.nextInt( (int)Main.bg.img.getWidth());
        int y = rnd.nextInt( (int)Main.bg.img.getHeight());

        Point2D point = new Point2D(x, y);
        return point;
    }*/
    public void Damage(){
        double loseHealth= Double.parseDouble(getDamage());
        setHealthPoint(healthPoint-(int) loseHealth);
    }
// under construction
 /*   public static void interCheck(){
        for(Spider spider: web){





            if (spider.getGroup().getBoundsInParent().intersects
                    (Main.radBase.g.getBoundsInParent())) {
                if(spider.getSide() == "Radiant") {
                    spider.setHealthPoint(maxHealth);}
                else spider.Damage();
            }
            if(spider.getGroup().getBoundsInParent().intersects
                    (Main.direBase.g.getBoundsInParent())){
                if(spider.getSide() == "Dire") {
                    spider.setHealthPoint(maxHealth);}
                else spider.Damage();

            }
        }

    }*/



 /*   public void rndMove() {

        double deltaX = 2;
        double deltaY = 2;


        while (rnd_move && !to_Base) {
            g.setLayoutX(circle.getLayoutX() + deltaX);
            g.setLayoutY(circle.getLayoutY() + deltaY);
            Scene sc = getGroup().getScene();
            Bounds bounds = Main.group.getBoundsInLocal();
            boolean rightBorder = circle.getLayoutX() >= (bounds.getMaxX() - circle.getRadius());
            boolean leftBorder = circle.getLayoutX() <= (bounds.getMinX() + circle.getRadius());
            boolean bottomBorder = circle.getLayoutY() >= (bounds.getMaxY() - circle.getRadius());
            boolean topBorder = circle.getLayoutY() <= (bounds.getMinY() + circle.getRadius());
            if (rightBorder || leftBorder) {
                deltaX *= -1;
            }
            if (bottomBorder || topBorder) {
                deltaY *= -1;
            }
            if(!rnd_move){break;}
        }

    }*/

   /*     if (Spider.rnd_move && !Spider.to_Base) {
            int x = (int) g.getLayoutX();
            int y = (int) g.getLayoutY();
            if (x == point.getX() && y == point.getY()) {
                point = newRANDpntCreator();
            }
            int go = 1;
            if (x > point.getX()) {
                g.setLayoutX(g.getLayoutX() - go);
            } else if (y > point.getY()) {
                g.setLayoutY(g.getLayoutY() - go);
            } else if (x < point.getX()) {
                g.setLayoutX(g.getLayoutX() + go);
            } else if (y < point.getY()) {
                g.setLayoutY(g.getLayoutY() + go);
            }
        }*/


/*
    public void moveToBase(){
        if (Spider.to_Base == true && Spider.rnd_move == false) {
            if (this.getSide() == true) {
                newX = Main.RadBase.iv.getX()+Main.goodBase.r.getWidth()/2;
                newY = Main.goodBase.iv.getY()+Main.goodBase.r.getHeight()/2;

                double dx = newX - g.getLayoutX();
                dx = (Math.abs(dx) > speed.getSpeed()) ? Math.signum(dx) * speed.getSpeed() : dx;
                double dy = newY - g.getLayoutY();
                dy = (Math.abs(dy) > speed.getSpeed()) ? Math.signum(dy) * speed.getSpeed() : dy;

                g.setLayoutX(g.getLayoutX() + dx);
                g.setLayoutY(g.getLayoutY() + dy);
            }
            else {
                newx = Main.badBase.iv.getX()+Main.badBase.r.getWidth()/2;
                newy = Main.badBase.iv.getY()+Main.badBase.r.getHeight()/2;

                double dx = newx - g.getLayoutX();
                dx = (Math.abs(dx) > speed.getSpeed()) ? Math.signum(dx) * speed.getSpeed() : dx;
                double dy = newy - g.getLayoutY();
                dy = (Math.abs(dy) > speed.getSpeed()) ? Math.signum(dy) * speed.getSpeed() : dy;

                g.setLayoutX(g.getLayoutX() + dx);
                g.setLayoutY(g.getLayoutY() + dy);
            }
        }
    }

*/
    @Override
    public String toString() {
        return "Spider{" +
                "name=" + name.getText() +
                ", damage=" + damage+
                ", health=" + healthPoint +
                ", side=" +side+
                ", x=" + x +
                ", y=" + y +
                ", active="+ active+
                '}';
    }

    @Override
    public int compare(Object o1, Object o2) {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Spider spider = (Spider) obj;
        return damage == spider.damage &&
                side==spider.side &&
                name.equals(spider.name) &&
                healthPoint == spider.healthPoint;
    }

    @Override
    public int compareTo(@NotNull Spider o) {
        return 0;
    }
}




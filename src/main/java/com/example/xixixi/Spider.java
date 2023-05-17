package com.example.xixixi;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.Random;

public class Spider implements Cloneable, Comparable<Spider>, Comparator {
    protected ScrollPane scene;

    protected Label name = new Label();
    protected Line life = new Line();
    protected Label sideLabel = new Label();
    protected Image i;
    protected Group g;

    protected ImageView imageView;
    protected Label survived=new Label();
    protected SurvivedCounter counter;

    protected Circle circle = new Circle();
    protected int step = 40;


    private String[] suggestedNames = new String[3];

    /*public void updateSurvived() {

        this.secondsSurvived += 1;
    }*/

    protected boolean side;
    String sideS;
    String nameS;

    public void setSafe(boolean safe) {
        isSafe = safe;
    }

    protected boolean active = false;
    /*private Point2D point = newRANDpntCreator();*/
    protected static boolean to_Base = false;

    private boolean kicked;
    private boolean isSafe;

    public boolean getSafe() {
        return isSafe;
    }

    protected static boolean rnd_move = false;
    protected double x, y;
/*    static double newX;
    static double newY;*/

    public SurvivedCounter getCounter() {
        return counter;
    }

    private int healthPoint;


    private double damage;
    protected static Random random = new Random();

    public Group getGroupP() {
        return g;
    }

    public static boolean getRndMove() {
        return rnd_move;
    }

    public void setDamage(String dmg) {
        try {
            damage = Double.parseDouble(dmg);
        } catch (Exception e) {
            damage = 0.0;
        }
    }

    public String[] getSuggestedNames() {
        return suggestedNames;
    }

    public String getSide() {
        if (side) {
            return "true";
        } else {
            return "false";
        }
    }

    public void setSide(String n) {
        if (n.equals("true")) {
            side = true;
            if (side) {
                sideLabel = new Label("R:");
            }
        } else if (n.equals("false")) {
            side = false;
            sideLabel = new Label("D: ");
        } else {
            System.out.println("You`ve entered something wrong!");
        }

    }

    public Group getGroup(Spider s) {
        return s.g;
    }

    public boolean getActive() {
        if (active) {
            return true;
        } else {
            return false;
        }
    }

    public void setActive(Boolean b) {
        if (b) {
            active = true;
        } else {
            active = false;
        }
    }

    public void setStep(int step) {
        this.step = step;
    }

    public int getStep() {
        return this.step;
    }

    public String getDamage() {
        return Double.toString(damage);
    }

    public void setHealthPoint(int healthPoint) {
        this.healthPoint = healthPoint;
        this.life = new Line(x, y + 15, x + (Integer.parseInt(getHealthPoint())) / 1.55, y + 15);
        life.setStrokeWidth(6);
        life.setStroke(Color.GREEN);
    }

    public String getHealthPoint() {
        return Integer.toString(healthPoint);
    }

    public void setName(String unitName) {
        name.setText(unitName);
    }

    public String getName() {
        return name.getText();
    }

    public String getX() {
        return Double.toString(x);
    }

    public String getPosX(){
        return Double.toString(this.g.getLayoutX());
    }
    public String getPosY(){
        return Double.toString(this.g.getLayoutY());
    }
    public void setX(String sX) {
        try {
            x = Double.parseDouble(sX);
        } catch (Exception e) {
            x = 0.0;
        }
    }

    public String getY() {
        return Double.toString(y);
    }

    public void setY(String sY) {
        try {
            x = Double.parseDouble(sY);
        } catch (Exception e) {
            y = 0.0;
        }
    }

    public static void SwitchTo_Base() {
        Spider.to_Base = !Spider.to_Base;
    }

    public static void SwitchRnd_Move() {
        Spider.rnd_move = !Spider.rnd_move;
    }

    public boolean isActive() {
        return active;
    }

    private static final int maxHealth = 100;

    static {
        System.out.println("Static initialization is complete!");
    }

    public void setSuggestedNames(String[] suggestedNames) {
        this.suggestedNames = suggestedNames;
    }

    public void init(SurvivedCounter c, double posX,double posY) {
        circle.setRadius(45);
        circle.setCenterX(x + 35);
        circle.setCenterY(y + 60);
        if (active) {
            circle.setFill(Color.YELLOW);
        } else {
            circle.setFill(Color.LIGHTBLUE);
        }

        if (sideS.equals("true")) {
            side = true;
        } else {
            side = false;
        }

        if (side) {
            sideLabel.setText("R:");
            sideLabel.setLayoutX(x - 20);
            sideLabel.setLayoutY(y - 6);
            sideLabel.setTextFill(Color.YELLOW);
            sideLabel.setFont(new Font(15));
        } else {
            sideLabel.setText("D:");
            sideLabel.setLayoutX(x - 20);
            sideLabel.setLayoutY(y - 6);
            sideLabel.setTextFill(Color.RED);
            sideLabel.setFont(new Font(15));
        }
        survived.setLayoutX(x + 58);
        survived.setLayoutY(y - 6);
        survived.setTextFill(Color.WHITE);
        survived.setFont(new Font(15));
        survived.setText(Integer.toString(c.getSecondsSurvived()));


        name.setText(nameS);
        name.setLayoutX(x);
        name.setLayoutY(y - 6);
        name.setTextFill(Color.WHITE);
        name.setFont(new Font(15));

        life.setStrokeWidth(6);
        life.setStroke(Color.GREEN);
        life.setStartX(x);
        life.setStartY(y + 15);
        life.setEndY(y + 15);
        life.setEndX(x + 48);

        g = new Group(circle, imageView, life, name,survived,  sideLabel);
        Main.group.getChildren().addAll(g);
        g.relocate(posX, posY);
    }

    //  set / get and variables
    // constructor
    public Spider(int healthPoint, double damage, String n, String s, double posX, double posY, String[] spiderN) {
        counter = new SurvivedCounter();
        this.x = posX;
        this.y = posY;
        this.damage = damage;
        this.healthPoint = healthPoint;
        this.sideS = s;
        this.nameS = n;
        this.i = new Image(Main.class.getResource("common.png").toString(), 75, 75, false, false);
        imageView = new ImageView(i);
        imageView.setX(x - 3);
        imageView.setY(y + 15);
        init(counter,posX,posY);

        this.g.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY)) switchActivation();
            }

        });

        System.out.println("A spider has been created!");
    }

    public Spider() {
        this(100, 100, "SPIDER", "", 0, 0, Main.bodya);
        System.out.println("A spider without parameters has been created!");
        SurvivedCounter counter = new SurvivedCounter();
        init(counter,0,0);
    }

    // ----------------------------------------------------------------------------------------
    @Override
    protected Object clone() throws CloneNotSupportedException {

        String[] cloned = {"This ", "object ", "was", "cloned"};
        Spider tmp = new Spider(Integer.parseInt(this.getHealthPoint()), Double.parseDouble(this.getDamage()), "Cloned",
                this.getSide(), Double.parseDouble(this.getX()), Double.parseDouble(this.getY()), cloned);
        tmp.counter =(SurvivedCounter)this.counter.clone();
        Rectangle rect = Main.findClonePosition(Double.parseDouble(tmp.getX()),Double.parseDouble(tmp.getY()),75,75);
        if(rect!=null){
            Main.uni.add(tmp);
            tmp.init(counter,Double.parseDouble(this.getX()),Double.parseDouble(this.getY()));
            /*t*//*mp.spawnBack(this);*/
            return tmp;
        }
        else return null;
    }
    protected Object cloneGolem() throws CloneNotSupportedException {


        String[] cloned = {"This ", "object ", "was", "cloned"};
        Golem tmp = new Golem(Integer.parseInt(this.getHealthPoint()), Double.parseDouble(this.getDamage()), "Cloned",
                this.getSide(), Double.parseDouble(this.getX()), Double.parseDouble(this.getY()), cloned);
        tmp.counter =(SurvivedCounter)this.counter.clone();
        Rectangle rect = Main.findClonePosition(Double.parseDouble(tmp.getX()),Double.parseDouble(tmp.getY()),75,75);
        if(rect!=null){
        Main.uni.add(tmp);
        tmp.init(counter,Double.parseDouble(this.getX()),Double.parseDouble(this.getY()));
        /*tmp.spawnBack(this);*/
        return tmp;
    }
        else return null;
    }
    protected Object cloneElem() throws CloneNotSupportedException {

        String[] cloned = {"This ", "object ", "was", "cloned"};
        IceElemental tmp = new IceElemental(Integer.parseInt(this.getHealthPoint()), Double.parseDouble(this.getDamage()), "Cloned",
                this.getSide(), Double.parseDouble(this.getX()), Double.parseDouble(this.getY()), cloned);
        tmp.counter =(SurvivedCounter)this.counter.clone();
        Rectangle rect = Main.findClonePosition(Double.parseDouble(tmp.getX()),Double.parseDouble(tmp.getY()),75,75);
        if(rect!=null){
            Main.uni.add(tmp);
            tmp.init(counter,Double.parseDouble(this.getX()),Double.parseDouble(this.getY()));
            /*tmp.spawnBack(this);*/
            return tmp;
        }
        else return null;
    }

    public void updateSecondsText(SurvivedCounter c){

        this.survived.setText(Integer.toString(c.getSecondsSurvived()/60));
    }


    public void switchActivation() {
        this.active = !this.active;
        if (this.active) this.circle.setFill(Color.YELLOW);
        else this.circle.setFill(Color.LIGHTBLUE);
    }

    public void moveLeft() {
        if (!active) return;
        /*if (g.getLayoutX() > 1)*/ {
            double y = g.getLayoutX() - step;
            g.setLayoutX(y);
        }
    }

    public void moveUp() {
        if (!active) return;
        /*if (g.getLayoutY() > 1)*/ {
            double y = g.getLayoutY() - step;
            g.setLayoutY(y);
        }
    }

    public void moveRight() {
        if (!active) return;
        /*if (g.getLayoutX() < 1480)*/ {
            double x = g.getLayoutX() + step;
            g.setLayoutX(x);
        }
    }

    public void moveDown() {
        if (!active) return;
        /*if (g.getLayoutY() < 690)*/ {
            double y = g.getLayoutY() + step;
            g.setLayoutY(y);
        }
    }

    public Point2D newRandomPointCreator() {
        Random rnd = new Random();
        int x = rnd.nextInt((int) Main.bg.img.getWidth());
        int y = rnd.nextInt((int) Main.bg.img.getHeight());

        Point2D point = new Point2D(x, y);
        return point;
    }

    public void dealDamage( Spider target) {
        target.setHealthPoint((target.healthPoint)-1);

    }

    public static void cleanupCorpses() {
        for (int i = 0; i < Main.uni.size(); i++) {
            Spider sp = Main.uni.get(i);
            if (Integer.parseInt(sp.getHealthPoint()) <= 0) {
                Main.group.getChildren().remove(sp.g);
                Main.uni.remove(i);
            }

        }
    }


    public static void interCheck() {

///----------------------------------------------------RADIANT BASE
        for (Spider spider : Main.uni) {
            if (spider.getGroup(spider).getBoundsInParent().intersects(Main.radBase.RadiantBaseGroup.getBoundsInParent())) {
                if (spider.getSide().equals("true") ) {
                    if(!spider.isSafe){
                        spider.setSafe(true);
                        spider.setHealthPoint(maxHealth);
                        RadBase.radiantBaseList.add(spider);
                        spider.g.setVisible(false);
                        /*spider.hide(spider);*/
                        if (spider.active) {
                            spider.setActive(false);
                        }
                    }
                    } else spider.dealDamage( spider);

            }
///------------------------------------------------------------NorthEAsternTower
            if (spider.getGroup(spider).getBoundsInParent().intersects(Main.northEasternTower.towerGroup.getBoundsInParent())) {
                if (Main.northEasternTower.getState() == Tower.State.NEUTRAL) {
                    if (spider.getSide().equals("true")&& !spider.isSafe&& spider.healthPoint<50) {
                        if (spider.active) {
                            spider.setActive(false);
                        }
                        spider.setHealthPoint(Integer.parseInt(spider.getHealthPoint()) + 20);
                        spider.setSafe(true);
                        spider.g.setVisible(false);
                        Main.northernTowerList.add(spider);
                        Main.northEasternTower.setState(Tower.State.RADIANT);
                        Main.northEasternTower.towerPaint();
                    } else {
                        Main.northEasternTower.setState(Tower.State.DIRE);
                        Main.northEasternTower.towerPaint();
                    }
                }
                if (Main.northEasternTower.getState() == Tower.State.DIRE) {
                    if (spider.getSide().equals("false") && !spider.isSafe && spider.healthPoint<50)  {
                        spider.setSafe(true);
                        spider.setHealthPoint(Integer.parseInt(spider.getHealthPoint()) + 20);
                        Main.northernTowerList.add(spider);
                        spider.g.setVisible(false);
                        /*spider.hide(spider);*/
                        if (spider.active) {
                            spider.setActive(false);
                        }
                    }
                }
                if (Main.northEasternTower.getState() == Tower.State.RADIANT) {
                    if (spider.getSide().equals("true") && !spider.isSafe && spider.healthPoint<50) {
                        spider.setSafe(true);
                        spider.setHealthPoint(Integer.parseInt(spider.getHealthPoint()) + 20);
                        Main.northernTowerList.add(spider);
                        spider.switchActivation();
                        spider.g.setVisible(false);
                        /*spider.hide(spider);*/
                        if (spider.active) {
                            spider.setActive(false);
                        }
                    }
                }
            }
///----------------------------------------------------------------------------SouthWestern Tower
            if (spider.getGroup(spider).getBoundsInParent().intersects(Main.southWesternTower.towerGroup.getBoundsInParent())) {
                if (Main.southWesternTower.getState() == Tower.State.NEUTRAL) {
                    if (spider.getSide().equals("true")&& !spider.isSafe&& spider.healthPoint<50) {
                        Main.southWesternTower.setState(Tower.State.RADIANT);
                        Main.southWesternTower.towerPaint();
                        spider.setSafe(true);
                        spider.g.setVisible(false);
                        spider.setHealthPoint(Integer.parseInt(spider.getHealthPoint()) + 20);
                        Main.southernTowerList.add(spider);
                    } else {
                        Main.southWesternTower.setState(Tower.State.DIRE);
                        Main.southWesternTower.towerPaint();
                    }
                }
                if (Main.southWesternTower.getState() == Tower.State.DIRE) {
                    if (spider.getSide().equals("false") && !spider.isSafe&& spider.healthPoint<50) {
                        spider.setSafe(true);
                        spider.setHealthPoint(Integer.parseInt(spider.getHealthPoint()) + 20);
                        Main.southernTowerList.add(spider);
                        spider.switchActivation();
                        spider.g.setVisible(false);
                        /*spider.hide(spider);*/
                        if (spider.active) {
                            spider.setActive(false);
                        }
                    }
                }
                if (Main.southWesternTower.getState() == Tower.State.RADIANT) {
                    if (spider.getSide().equals("true") && !spider.isSafe&& spider.healthPoint<50) {
                        spider.setSafe(true);
                        spider.setHealthPoint(Integer.parseInt(spider.getHealthPoint()) + 20);
                        Main.southernTowerList.add(spider);
                        spider.switchActivation();
                        spider.g.setVisible(false);
                        /*spider.hide(spider);*/
                        if (spider.active) {
                            spider.setActive(false);
                        }
                    }
                }
            }
            if (spider.getGroup(spider).getBoundsInParent().intersects(Main.middleEarthTower.towerGroup.getBoundsInParent())) {
                if (Main.middleEarthTower.getState() == Tower.State.NEUTRAL) {
                    if (spider.getSide().equals("true")&& !spider.isSafe&& spider.healthPoint<50) {
                        Main.middleEarthTower.setState(Tower.State.RADIANT);
                        Main.middleEarthTower.towerPaint();
                        Main.middleTowerList.add(spider);
                        spider.g.setVisible(false);
                        spider.setHealthPoint(Integer.parseInt(spider.getHealthPoint()) + 20);
                        spider.setSafe(true);
                    } else {
                        Main.middleEarthTower.setState(Tower.State.DIRE);
                        Main.middleEarthTower.towerPaint();
                    }
                }
                if (Main.middleEarthTower.getState() == Tower.State.DIRE) {
                    if (spider.getSide().equals("false") && !spider.isSafe&& spider.healthPoint<50) {
                        spider.setSafe(true);
                        spider.setHealthPoint(Integer.parseInt(spider.getHealthPoint()) + 20);
                        Main.middleTowerList.add(spider);
                        spider.switchActivation();
                        spider.g.setVisible(false);
                        /*spider.hide(spider);*/
                        if (spider.active) {
                            spider.setActive(false);
                        }
                    }
                }
                if (Main.middleEarthTower.getState() == Tower.State.RADIANT) {
                    if (spider.getSide().equals("true") && !spider.isSafe&& spider.healthPoint<50) {
                        spider.setSafe(true);
                        spider.setHealthPoint(Integer.parseInt(spider.getHealthPoint()) + 20);
                        Main.middleTowerList.add(spider);
                        spider.switchActivation();
                        spider.g.setVisible(false);
                        /*spider.hide(spider);*/
                        if (spider.active) {
                            spider.setActive(false);
                        }
                    }
                }
            }
/////-----------------------------------------------------------------------------Dire Base
            if (spider.getGroup(spider).getBoundsInParent().intersects(Main.direBase.direBaseGroup.getBoundsInParent())) {
                if (spider.getSide().equals("false") ) {
                    if (!spider.isSafe){
                        spider.setSafe(true);
                    spider.setHealthPoint(maxHealth);
                    DireBase.direBaseList.add(spider);
                    spider.switchActivation();
                    spider.g.setVisible(false);
                    if (spider.active) {
                        spider.setActive(false);
                    }
                }
                } else spider.dealDamage( spider);
            }
        }
    }


    public void spawnBack(Spider s) {
        Main.group.getChildren().add(s.getGroup(s));
        /*s.getGroup().setVisible(true);*/
    }

    public void hide(Spider s) {
        Main.group.getChildren().remove(s.getGroup(this));
        /*s.getGroup().setVisible(false);*/
    }


    @Override
    public String toString() {
        return "Spider{" + "name=" + name.getText() +
                ", damage=" + damage + ", health=" + healthPoint + ", side="
                + side + ", x=" + getPosX() + ", y=" + getPosY() + ", suggested names" +
                suggestedNames.toString() + ", active=" + active +", safe+"+ isSafe+'}';
    }

    @Override
    public int compare(Object o1, Object o2) {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Spider spider = (Spider) obj;
        return damage == spider.damage && side == spider.side && name.equals(spider.name) && healthPoint == spider.healthPoint;
    }

    @Override
    public int compareTo(@NotNull Spider o) {
        return 0;
    }
}




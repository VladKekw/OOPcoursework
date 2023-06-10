package com.example.POEgame;

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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

interface Predict {
    void predictTheWin();
}

public class Spider implements Predict, Cloneable, Comparable<Spider>, Comparator {
    protected ScrollPane scene;
    public static File logfile = new File("save.txt");
    static Logger logger = new Logger(logfile.getPath());
    protected Circle radius;

    protected Label name = new Label();
    protected Line life = new Line();
    protected Label sideLabel = new Label();
    protected Image i;
    protected Group g;

    protected ImageView imageView;
    protected Label survived = new Label();
    protected SurvivedCounter counter;

    protected Spider target;

    public Spider getTarget() {
        return target;
    }

    public void setTarget(Spider target) {
        this.target = target;
    }

    protected Circle circle = new Circle();
    protected int step = 40;
    protected byte autoMove;


    protected int attackRange = 60;
    protected Point2D point = newRANDpntCreator();

    public Point2D newRANDpntCreator() {
        Random rnd = new Random();
        int x = rnd.nextInt((int) Main.bg.img.getWidth());
        int y = rnd.nextInt((int) Main.bg.img.getHeight());

        Point2D point = new Point2D(x, y);
        return point;
    }


    protected String[] suggestedNames = new String[3];


    protected boolean side;
    protected String sideS;
    protected String nameS;

    public void setSafe(boolean safe) {
        isSafe = safe;
    }

    protected boolean active = false;

    protected static boolean to_Base = false;

    protected boolean isSafe;

    public boolean getSafe() {
        return isSafe;
    }

    static double newx;
    static double newy;

    protected static int northCountDire = 0;
    protected static int northCountRad = 0;

    protected static int southCountDire = 0;
    protected static int southCountRad = 0;
    protected static int middleCountDire = 0;
    protected static int middleCountRad = 0;


    public static int getNorthCountDire() {
        return northCountDire;
    }

    public static void setNorthCountDire(int northCount) {
        northCountDire = northCount;
    }

    public static int getSouthCountDire() {
        return southCountDire;
    }

    public static void setSouthCountDire(int southCount) {
        southCountDire = southCount;
    }

    public static int getMiddleCountDire() {
        return middleCountDire;
    }

    public static void setMiddleCountDire(int middleCount) {
        middleCountDire = middleCount;
    }

    public static int getNorthCount() {
        return northCountRad;
    }

    public static void setNorthCountRad(int northCount) {
        northCountRad = northCount;
    }

    public static int getSouthCount() {
        return southCountRad;
    }

    public static void setSouthCountRad(int southCount) {
        southCountRad = southCount;
    }

    public static int getMiddleCount() {
        return middleCountRad;
    }

    public static void setMiddleCount(int middleCount) {
        middleCountRad = middleCount;
    }

    protected static boolean rnd_move = true;
    protected double x, y;


    public SurvivedCounter getCounter() {
        return counter;
    }

    protected int healthPoint;


    double damage;
    protected static Random random = new Random();

    public Group getGroupP() {
        return g;
    }

    public boolean getRndMove() {
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
                this.sideLabel = new Label("R:");
            }
        } else if (n.equals("false")) {
            this.side = false;
            this.sideLabel = new Label("D: ");
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

    public void setHealthPoint(double healthPoint) {
        this.healthPoint = (int)healthPoint;
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

    public String getPosX() {
        return Double.toString(this.g.getLayoutX());
    }

    public String getPosY() {
        return Double.toString(this.g.getLayoutY());
    }

    public void setX(String sX) {
        try {
            x = Double.parseDouble(sX);
            this.g.setLayoutX(x);
        } catch (Exception e) {
            x = 0.0;
            this.g.setLayoutX(0.0);
        }
    }

    public String getY() {
        return Double.toString(y);
    }

    public void setY(String sY) {
        try {
            y = Double.parseDouble(sY);
            this.g.setLayoutY(y);
        } catch (Exception e) {
            y = 0.0;
            this.g.setLayoutY(0.0);
        }
    }

    public void SwitchTo_Base() {
        Spider.to_Base = !Spider.to_Base;
    }

    public void SwitchRnd_Move() {
        rnd_move = !rnd_move;
    }

    public boolean isActive() {
        return active;
    }

    protected static final int maxHealth = 100;

    static {
        Main.createNewSpider("crawler", "50", "", "false", "0.0", "0.0", Main.NAMES);
        Main.createNewSpider("riftwalker", "70", "70", "false", "0.0", "0.0", Main.NAMES);
        Main.createNewSpider("stalker", "50", "100", "false", "0.0", "0.0", Main.NAMES);

        Main.createNewSpider("hider", "50", "", "true", "470.0", "400.0", Main.NAMES);
        Main.createNewSpider("mindmuck", "50", "", "true", "560.0", "500.0", Main.NAMES);
        Main.createNewSpider("spooker", "50", "", "true", "630.0", "600.0", Main.NAMES);

        Main.createNewGolem("smasher", "50", "", "false", "0.0", "0.0", Main.NAMES);
        Main.createNewGolem("felstomper", "50", "", "true", "860.0", "490.0", Main.NAMES);
        Main.createNewGolem("anicentone", "", "", "", "0.0", "0.0", Main.NAMES);

        Main.createNewIceElemental("iceIncarnate", "50", "", "false", "0.0", "0.0", Main.NAMES);
        Main.createNewIceElemental("stormer", "50", "", "true", "710", "510", Main.NAMES);


        System.out.println("Static initialization is complete!");
    }

    public void setSuggestedNames(String[] suggestedNames) {
        this.suggestedNames = suggestedNames;
    }

    public void init(SurvivedCounter c, double posX, double posY, Boolean aura) {
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
        survived.setLayoutX(x + 78);
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
        radius = new Circle(attackRange);
        radius.setCenterY(y + 60);
        radius.setCenterX(x + 35);
        radius.setFill(Color.TRANSPARENT);

        if (aura) {
            Circle unitAura = new Circle(70);
            unitAura.setFill(Color.TRANSPARENT);
            unitAura.setCenterX(x + 35);
            unitAura.setCenterY(y + 60);
            g = new Group(circle, imageView, life, name, survived, sideLabel, unitAura, radius);
            Main.group.getChildren().addAll(g);
            g.relocate(posX, posY);
        } else {
            g = new Group(circle, imageView, life, name, survived, sideLabel, radius);
            Main.group.getChildren().addAll(g);
            g.relocate(posX, posY);
        }


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
        init(counter, posX, posY, false);
        predictTheWin();
        logger.log(this.toString());
        System.out.println("Customised spider has been created");

        this.g.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY)) switchActivation();
            }
        });
    }

    public Spider(int healthPoint, double damage, String n, String s, double posX, double posY, String[] spiderN, boolean active, double counsec) {
        counter = new SurvivedCounter();
        this.counter.setSecondsSurvived((int) counsec);
        System.out.println(counter.getSecondsSurvived());
        this.active = active;
        this.x = posX;
        this.y = posY;
        this.damage = damage;
        this.healthPoint = healthPoint;
        this.sideS = s;
        this.nameS = n;
        this.i = new Image(Main.class.getResource("common.png").toString(), 75, 75, false, false);
        this.imageView = new ImageView(i);
        imageView.setX(x - 3);
        imageView.setY(y + 15);
        init(counter, posX, posY, false);
        predictTheWin();
        System.out.println("Customised spider has been created");
        logger.log(this.toString());

        this.g.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY)) switchActivation();
            }
        });
    }


    public Spider() {
        this(100, 100, "SPIDER", "", 0, 0, Main.bodya);
        System.out.println("A spider without parameters has been created!");
        SurvivedCounter counter = new SurvivedCounter();
        init(counter, 0, 0, false);
        logger.log(this.toString());
        this.g.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY))
                    for (Spider s : Main.uni)
                        s.switchActivation();
            }

        });

    }

    // ----------------------------------------------------------------------------------------
    @Override
    protected Object clone() throws CloneNotSupportedException {
        String[] cloned = {"This ", "object ", "was", "cloned"};
        Spider tmp = new Spider();
        Main.group.getChildren().remove(tmp.g);

        if (this instanceof IceElemental) {
            tmp.counter = (SurvivedCounter) this.counter.clone();
            tmp = new IceElemental(Integer.parseInt(this.getHealthPoint()), Double.parseDouble(this.getDamage()), "Cloned",
                    this.getSide(), Double.parseDouble(this.getX()), Double.parseDouble(this.getY()), cloned);
            tmp.init(counter, Double.parseDouble(this.getX()), Double.parseDouble(this.getY()), true);
            Main.uni.add(tmp);
            return tmp;
        }
        if (this instanceof Golem) {
            tmp.counter = (SurvivedCounter) this.counter.clone();
            tmp = new Golem(Integer.parseInt(this.getHealthPoint()), Double.parseDouble(this.getDamage()), "Cloned",
                    this.getSide(), Double.parseDouble(this.getX()), Double.parseDouble(this.getY()), cloned);
            tmp.init(counter, Double.parseDouble(this.getX()), Double.parseDouble(this.getY()), false);
            Main.uni.add(tmp);
            return tmp;

        }

        if (this instanceof Spider) {
            tmp.counter = (SurvivedCounter) this.counter.clone();
            tmp = new Spider(Integer.parseInt(this.getHealthPoint()), Double.parseDouble(this.getDamage()), "Cloned",
                    this.getSide(), Double.parseDouble(this.getX()), Double.parseDouble(this.getY()), cloned);
            tmp.init(counter, Double.parseDouble(this.getX()), Double.parseDouble(this.getY()), false);
            Main.uni.add(tmp);
            return tmp;


        }

        Rectangle rect = Main.findClonePosition(Double.parseDouble(tmp.getX()), Double.parseDouble(tmp.getY()), 75, 75);
        if (rect != null) {
            return tmp;
        } else return null;
    }

    public void moveToBase() {
        if (!this.active && to_Base) {
            double desttargetX;
            double desttargetY;
            if (this.side) {
                desttargetX = Main.radBase.r.getX();
                desttargetY = Main.radBase.r.getY();
                if (this.g.getBoundsInParent().intersects(Main.radBase.r.getBoundsInParent()))
                    return;
            } else {
                desttargetX = Main.direBase.r.getX();
                desttargetY = Main.direBase.r.getY();
                if (this.g.getBoundsInParent().intersects(Main.direBase.r.getBoundsInParent()))
                    return;
            }
            if (desttargetX < this.x) {
                this.x -=2;
            }
            if (desttargetX > this.x) {
                this.x +=2;
            }
            if (desttargetY > this.y) {
                this.y +=2;
            }
            if (desttargetY < this.y) {
                this.y -=2;
            }
            logger.log(this + "changed his position");
        }
    }


    public void updateSecondsText(SurvivedCounter c) {

        this.survived.setText(Integer.toString(c.getSecondsSurvived() / 60));
    }


    public void switchActivation() {
        this.active = !this.active;
        if (this.active) {
            this.circle.setFill(Color.YELLOW);

        } else this.circle.setFill(Color.LIGHTBLUE);
    }

    public void autoMove() {
       if(!to_Base)
       {
           if (!this.active) {
               switch (this.autoMove) {
                   case 0:
                       this.y -= (double) (this.step) /10 + 0.001;
                       /*this.imageView.setRotate(180);*/
                       break;
                   case 1:
                       this.y -= (double) (this.step) /10 + 0.001;
                       this.x += (double) (this.step) /10 + 0.001;
                       /*this.imageView.setRotate(225);*/
                       break;
                   case 2:
                       this.x += (double) (this.step) /10 + 0.001;
                       /*this.imageView.setRotate(270);*/
                       break;
                   case 3:
                       this.x += (double) (this.step) /10 + 0.001;
                       this.y += (double) (this.step) /10 + 0.001;
                       /*this.imageView.setRotate(315);*/
                       break;
                   case 4:
                       this.y += (double) (this.step) /10 + 0.001;
                       /*this.imageView.setRotate(0);*/
                       break;
                   case 5:
                       this.y += (double) (this.step) /10 + 0.001;
                       this.x -= (double) (this.step) /10 + 0.001;
                       /*this.imageView.setRotate(45);*/
                       break;
                   case 6:
                       this.x -= (double) (this.step) /10 + 0.001;
                       /*this.imageView.setRotate(90);*/
                       break;
                   case 7:
                       this.x -= (double) (this.step) /10 + 0.001;
                       this.y -= (double) (this.step) /10 + 0.001;
                       /*this.imageView.setRotate(135);*/
                       break;
               }
               logger.log(this + "changed his position");
               if (this.imageView.getX() + this.imageView.getFitWidth() + 100 >= Background.border.getWidth() - this.imageView.getFitWidth()) {
                   this.autoMove = (byte) (Main.random.nextInt(3) + 5);
               } else if (this.imageView.getX() <= 0) {
                   this.autoMove = (byte) (Main.random.nextInt(3) + 1);
               } else if (this.imageView.getY() <= 0) {
                   this.autoMove = (byte) (Main.random.nextInt(3) + 3);
               } else if (this.imageView.getY() + this.imageView.getFitHeight() -100 >= Background.border.getHeight()) {
                   this.autoMove = (byte) (Main.random.nextInt(2));
               }
           }
       }
    }

    public void updatePosition() {
        this.imageView.setY(this.y+10);
        this.imageView.setX(this.x);
        this.circle.setCenterX(this.x + 35);
        this.circle.setCenterY(this.y + 60);
        this.sideLabel.setLayoutX(this.x - 20);
        this.sideLabel.setLayoutY(this.y - 6);
        this.survived.setLayoutX(this.x + 78);
        this.survived.setLayoutY(this.y - 6);
        this.name.setLayoutX(this.x);
        this.name.setLayoutY(this.y - 6);
        this.life.setStartX(this.x);
        this.life.setStartY(this.y + 15);
        this.life.setEndY(this.y + 15);
        this.life.setEndX(this.x + 48);
    }


    public void moveLeft(int gear) {
        if (!active) return;
        {
            if (gear >= 2) {
                double y = this.x - step - step / 2;
                this.x = y;
            } else {
                double y = this.x - step;
                this.x = y;
            }
        }
    }

    public void moveUp(int gear) {
        if (!active) return;
        {
            if (gear >= 2) {
                double y = this.y - step - step / 2;
                this.y = y;
            } else {
                double y = this.y - step;
                this.y = y;
            }
        }

    }

    public void moveRight(int gear) {
        if (!active) return;


        if (gear >= 2) {
            double x = this.x + step + step / 2;
            this.x = x;
        } else {
            double y = this.x + step;
            this.x = y;
        }
    }


    public void moveDown(int gear) {
        if (!active) return;
        {
            if (gear >= 2) {
                double y = this.y + step + step / 2;
                this.y = y;
            } else {
                double y = this.y + step;
                this.y = y;
            }
        }

    }

    public Point2D newRandomPointCreator() {
        Random rnd = new Random();
        int x = rnd.nextInt((int) Main.bg.img.getWidth());
        int y = rnd.nextInt((int) Main.bg.img.getHeight());

        Point2D point = new Point2D(x, y);
        return point;
    }

    public Point2D absoluteRandomPoint() {
        Random rnd = new Random();
        int x = rnd.nextInt(3060);
        int y = rnd.nextInt(2000);
        Point2D point = new Point2D(x, y);
        return point;
    }

    public void receiveDamageFromObjects(Spider target) {
        target.setHealthPoint((target.healthPoint) - 2);

    }


    public static void cleanupCorpses() {
        for (int i = 0; i < Main.uni.size(); i++) {
            Spider sp = Main.uni.get(i);
            if (Integer.parseInt(sp.getHealthPoint()) <= 0) {
                Main.group.getChildren().remove(sp.g);
                logger.log(Main.uni.get(i).toString() + " died ");
                Main.uni.remove(i);
            }

        }
    }

    public static void towerCheck() {
        // north tower
        if (northCountDire == 3 && northCountRad < northCountDire) {
            Main.northEasternTower.setState(Tower.State.DIRE);
            Main.northEasternTower.towerPaint();
        } else if (northCountRad == 3 && northCountDire < northCountRad) {
            Main.northEasternTower.setState(Tower.State.RADIANT);
            Main.northEasternTower.towerPaint();
        }
        // south tower
        if (southCountDire == 3 && southCountRad < southCountDire) {
            Main.southWesternTower.setState(Tower.State.DIRE);
            Main.southWesternTower.towerPaint();
        } else if (southCountRad == 3 && southCountDire < southCountRad) {
            Main.southWesternTower.setState(Tower.State.RADIANT);
            Main.southWesternTower.towerPaint();
        }
        // middle tower
        if (middleCountDire == 3 && middleCountRad < middleCountDire) {
            Main.middleEarthTower.setState(Tower.State.DIRE);
            Main.middleEarthTower.towerPaint();
        } else if (middleCountRad == 3 && middleCountDire < middleCountRad) {
            Main.middleEarthTower.setState(Tower.State.RADIANT);
            Main.middleEarthTower.towerPaint();
        }
    }

    public static void interCheck() {

///----------------------------------------------------RADIANT BASE
        for (Spider spider : Main.uni) {
            if (spider.getGroup(spider).getBoundsInParent().intersects(Main.radBase.RadiantBaseGroup.getBoundsInParent())) {
                if (spider.getSide().equals("true")) {
                    if (!spider.isSafe && spider.healthPoint < 100) {
                        rnd_move=false;
                        to_Base=false;
                        spider.setSafe(true);
                        spider.setHealthPoint(maxHealth);
                        RadBase.radiantBaseList.add(spider);
                        spider.g.setVisible(false);
                        if (spider.active) {
                            spider.setActive(false);
                        }
                    }
                } else spider.receiveDamageFromObjects(spider);

            }
///------------------------------------------------------------NorthEAsternTower
            if (spider.getGroup(spider).getBoundsInParent().intersects(Main.northEasternTower.towerGroup.getBoundsInParent())) {
                if (Main.northEasternTower.getState() == Tower.State.NEUTRAL) {
                    if (!spider.isSafe && spider.healthPoint < 70) {
                        rnd_move=false;
                        to_Base=false;
                        int countdire = Main.northernTowerList.stream().filter(s -> s.getSide() == "false").collect(Collectors.toList()).size();
                        int countrad = Main.northernTowerList.stream().filter(s -> s.getSide() == "true").collect(Collectors.toList()).size();
                        if (spider.active) {
                            spider.setActive(false);
                        }
                        spider.setSafe(true);
                        spider.g.setVisible(false);
                        Main.northernTowerList.add(spider);
                        if (spider.side) {
                            northCountRad += 1;
                        } else northCountDire += 1;
                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                spider.healthPoint += 2;
                                if (spider.healthPoint > 70) {
                                    Main.northernTowerList.remove(spider);
                                    spider.getGroupP().setVisible(true);
                                    if (spider.getSide().equals("false")) {
                                        setNorthCountDire(countdire - 1);
                                    } else {
                                        setNorthCountRad(countrad - 1);
                                    }
                                }
                            }
                        }, 500, 1000);
                    }
                }
                if (Main.northEasternTower.getState() == Tower.State.DIRE) {
                    if (spider.getSide().equals("false")) {
                        if (!spider.isSafe && spider.healthPoint < 70) {
                            rnd_move=false;
                            to_Base=false;
                            int countdire = Main.northernTowerList.stream().filter(s -> s.getSide() == "false").collect(Collectors.toList()).size();
                            int countrad = Main.northernTowerList.stream().filter(s -> s.getSide() == "true").collect(Collectors.toList()).size();
                            if (spider.active) {
                                spider.setActive(false);
                            }
                            spider.setSafe(true);
                            spider.g.setVisible(false);
                            Main.northernTowerList.add(spider);
                            if (spider.side) {
                                northCountRad += 1;
                            } else northCountDire += 1;
                            new Timer().schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    spider.healthPoint += 2;
                                    if (spider.healthPoint > 70) {
                                        Main.northernTowerList.remove(spider);
                                        spider.getGroupP().setVisible(true);
                                        if (spider.getSide().equals("false")) {
                                            setNorthCountDire(countdire - 1);
                                        } else {
                                            setNorthCountRad(countrad - 1);
                                        }
                                    }
                                }
                            }, 500, 1000);
                        }
                    } else {
                        spider.receiveDamageFromObjects(spider);
                    }
                }
                if (Main.northEasternTower.getState() == Tower.State.RADIANT) {
                    if (spider.getSide().equals("true")) {
                        if (!spider.isSafe && spider.healthPoint < 70)
                        {
                            rnd_move=false;
                            to_Base=false;
                            int countdire = Main.northernTowerList.stream().filter(s -> s.getSide() == "false").collect(Collectors.toList()).size();
                            int countrad = Main.northernTowerList.stream().filter(s -> s.getSide() == "true").collect(Collectors.toList()).size();
                            if (spider.active) {
                                spider.setActive(false);
                            }
                            spider.setSafe(true);
                            spider.g.setVisible(false);
                            Main.northernTowerList.add(spider);
                            if (spider.side) {
                                northCountRad += 1;
                            } else northCountDire += 1;
                            new Timer().schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    spider.healthPoint += 2;
                                    if (spider.healthPoint > 70) {
                                        Main.northernTowerList.remove(spider);
                                        spider.getGroupP().setVisible(true);
                                        if (spider.getSide().equals("false")) {
                                            setNorthCountDire(countdire - 1);
                                        } else {
                                            setNorthCountRad(countrad - 1);
                                        }
                                    }
                                }
                            }, 500, 1000);
                        }
                    }
                         else {
                        spider.receiveDamageFromObjects(spider);
                    }
                }
            }
///----------------------------------------------------------------------------SouthWestern Tower
            if (spider.getGroup(spider).getBoundsInParent().intersects(Main.southWesternTower.towerGroup.getBoundsInParent())) {
                if (Main.southWesternTower.getState() == Tower.State.NEUTRAL) {
                    if (!spider.isSafe && spider.healthPoint < 70) {
                        rnd_move=false;
                        to_Base=false;
                        int countdire = Main.southernTowerList.stream().filter(s -> s.getSide() == "false").collect(Collectors.toList()).size();
                        int countrad = Main.southernTowerList.stream().filter(s -> s.getSide() == "true").collect(Collectors.toList()).size();
                        if (spider.active) {
                            spider.setActive(false);
                        }
                        spider.setSafe(true);
                        spider.g.setVisible(false);
                        Main.southernTowerList.add(spider);
                        if (spider.side) {
                            southCountRad += 1;
                        } else southCountDire += 1;
                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                spider.healthPoint += 2;
                                if (spider.healthPoint > 70) {
                                    Main.southernTowerList.remove(spider);
                                    spider.getGroupP().setVisible(true);
                                    if (spider.getSide().equals("false")) {
                                        setSouthCountDire(countdire - 1);
                                    } else {
                                        setSouthCountRad(countrad - 1);
                                    }
                                }
                            }
                        }, 500, 1000);
                    }
                }
                if (Main.southWesternTower.getState() == Tower.State.DIRE) {
                    if (spider.getSide().equals("false")) {
                        if (!spider.isSafe && spider.healthPoint < 70) {
                            rnd_move=false;
                            to_Base=false;
                            int countdire = Main.southernTowerList.stream().filter(s -> s.getSide() == "false").collect(Collectors.toList()).size();
                            int countrad = Main.southernTowerList.stream().filter(s -> s.getSide() == "true").collect(Collectors.toList()).size();
                            if (spider.active) {
                                spider.setActive(false);
                            }
                            spider.setSafe(true);
                            spider.g.setVisible(false);
                            Main.southernTowerList.add(spider);
                            if (spider.side) {
                                southCountRad += 1;
                            } else southCountDire += 1;
                            new Timer().schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    spider.healthPoint += 2;
                                    if (spider.healthPoint > 70) {
                                        Main.southernTowerList.remove(spider);
                                        spider.getGroupP().setVisible(true);
                                        if (spider.getSide().equals("false")) {
                                            setSouthCountDire(countdire - 1);
                                        } else {
                                            setSouthCountRad(countrad - 1);
                                        }
                                    }
                                }
                            }, 500, 1000);
                        }
                    } else {
                        spider.receiveDamageFromObjects(spider);
                    }
                }
                if (Main.southWesternTower.getState() == Tower.State.RADIANT) {
                    if (spider.getSide().equals("true")) {
                        if (!spider.isSafe && spider.healthPoint < 70)
                        {
                            rnd_move=false;
                            to_Base=false;
                            int countdire = Main.southernTowerList.stream().filter(s -> s.getSide() == "false").collect(Collectors.toList()).size();
                            int countrad = Main.southernTowerList.stream().filter(s -> s.getSide() == "true").collect(Collectors.toList()).size();
                            if (spider.active) {
                                spider.setActive(false);
                            }
                            spider.setSafe(true);
                            spider.g.setVisible(false);
                            Main.southernTowerList.add(spider);
                            if (spider.side) {
                                southCountRad += 1;
                            } else southCountDire += 1;
                            new Timer().schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    spider.healthPoint += 2;
                                    if (spider.healthPoint > 70) {
                                        Main.southernTowerList.remove(spider);
                                        spider.getGroupP().setVisible(true);
                                        if (spider.getSide().equals("false")) {
                                            setSouthCountDire(countdire - 1);
                                        } else {
                                            setSouthCountRad(countrad - 1);
                                        }
                                    }
                                }
                            }, 500, 1000);
                        }
                    }
                    else {
                        spider.receiveDamageFromObjects(spider);
                    }
                }
            }
            //// middle
            if (spider.getGroup(spider).getBoundsInParent().intersects(Main.middleEarthTower.towerGroup.getBoundsInParent())) {
                if (Main.middleEarthTower.getState() == Tower.State.NEUTRAL) {
                    if (!spider.isSafe && spider.healthPoint < 70) {
                        rnd_move=false;
                        to_Base=false;
                        int countdire = Main.middleTowerList.stream().filter(s -> s.getSide() == "false").collect(Collectors.toList()).size();
                        int countrad = Main.middleTowerList.stream().filter(s -> s.getSide() == "true").collect(Collectors.toList()).size();
                        if (spider.active) {
                            spider.setActive(false);
                        }
                        spider.setSafe(true);
                        spider.g.setVisible(false);
                        Main.middleTowerList.add(spider);
                        if (spider.side) {
                            middleCountRad += 1;
                        } else middleCountDire += 1;
                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                spider.healthPoint += 2;
                                if (spider.healthPoint > 70) {
                                    Main.middleTowerList.remove(spider);
                                    spider.getGroupP().setVisible(true);
                                    if (spider.getSide().equals("false")) {
                                        setMiddleCountDire(countdire - 1);
                                    } else {
                                        setMiddleCount(countrad - 1);
                                    }
                                }
                            }
                        }, 500, 1000);
                    }
                }
                if (Main.middleEarthTower.getState() == Tower.State.DIRE) {
                    if (spider.getSide().equals("false")) {
                        if (!spider.isSafe && spider.healthPoint < 70) {
                            rnd_move=false;
                            to_Base=false;
                            int countdire = Main.middleTowerList.stream().filter(s -> s.getSide() == "false").collect(Collectors.toList()).size();
                            int countrad = Main.middleTowerList.stream().filter(s -> s.getSide() == "true").collect(Collectors.toList()).size();
                            if (spider.active) {
                                spider.setActive(false);
                            }
                            spider.setSafe(true);
                            spider.g.setVisible(false);
                            Main.middleTowerList.add(spider);
                            if (spider.side) {
                                middleCountRad += 1;
                            } else middleCountDire += 1;
                            new Timer().schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    spider.healthPoint += 2;
                                    if (spider.healthPoint > 70) {
                                        Main.middleTowerList.remove(spider);
                                        spider.getGroupP().setVisible(true);
                                        if (spider.getSide().equals("false")) {
                                            setMiddleCountDire(countdire - 1);
                                        } else {
                                            setMiddleCount(countrad - 1);
                                        }
                                    }
                                }
                            }, 500, 1000);
                        }
                    } else {
                        spider.receiveDamageFromObjects(spider);
                    }
                }

                if (Main.middleEarthTower.getState() == Tower.State.RADIANT) {
                    if (spider.getSide().equals("true")) {
                        if (!spider.isSafe && spider.healthPoint < 70)
                        {
                            rnd_move=false;
                            to_Base=false;
                            int countdire = Main.middleTowerList.stream().filter(s -> s.getSide() == "false").collect(Collectors.toList()).size();
                            int countrad = Main.middleTowerList.stream().filter(s -> s.getSide() == "true").collect(Collectors.toList()).size();
                            if (spider.active) {
                                spider.setActive(false);
                            }
                            spider.setSafe(true);
                            spider.g.setVisible(false);
                            Main.middleTowerList.add(spider);
                            if (spider.side) {
                                middleCountRad += 1;
                            } else middleCountDire += 1;
                            new Timer().schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    spider.healthPoint += 2;
                                    if (spider.healthPoint > 70) {
                                        Main.middleTowerList.remove(spider);
                                        spider.getGroupP().setVisible(true);
                                        if (spider.getSide().equals("false")) {
                                            setMiddleCount(countdire - 1);
                                        } else {
                                            setMiddleCount(countrad - 1);
                                        }
                                    }
                                }
                            }, 500, 1000);
                        }
                    }
                    else {
                        spider.receiveDamageFromObjects(spider);
                    }
                }
            }

/////-----------------------------------------------------------------------------Dire Base
            if (spider.getGroup(spider).getBoundsInParent().intersects(Main.direBase.direBaseGroup.getBoundsInParent())) {
                if (spider.getSide().equals("false")) {
                    if (!spider.isSafe && spider.healthPoint < 100) {
                        rnd_move=false;
                        to_Base=false;
                        spider.setSafe(true);
                        spider.setHealthPoint(maxHealth);
                        RadBase.radiantBaseList.add(spider);
                        spider.g.setVisible(false);
                        if (spider.active) {
                            spider.setActive(false);
                        }
                    }
                } else spider.receiveDamageFromObjects(spider);
            }
            if (spider.getGroupP().getLayoutX() >= 2970 || spider.getGroupP().getLayoutX() <= 15) {
                spider.newx *= (-1.0);
            }
            if (spider.getGroupP().getLayoutY() >= 1940 || spider.getGroupP().getLayoutY() <= 15) {
                spider.newy *= (-1.0);
            }
            spider.lockTarget();
            spider.attackTarget(spider);
            spider.attackTarget(spider, spider.target);
        }
    }


    public double getDamageAsDouble() {
        return this.damage;
    }

    public int getSurvived() {
        return this.counter.getSecondsSurvived() / 60;
    }

    public void lockTarget() {
        if (this.getTarget() != null) {
            if (this.getSide().equals(this.target.getSide())) {
                this.setTarget(null);
            }
        }
        if (this.target == null && Main.uni.size() > 0) {
            Iterator<Spider> spiderIterator = Main.uni.listIterator();
            Spider trgt = spiderIterator.next();
            if (trgt != null && this != trgt) {
                /*if (Math.abs(trgt.x - this.x) < this.attackRange && Math.abs(trgt.y - this.y) < this.attackRange)*/
                {
                    this.setTarget(trgt);
                    if (this.getSide().equals(trgt.getSide())) {
                        if (spiderIterator.hasNext()) {
                            this.setTarget(spiderIterator.next());
                        }
                    } else {
                        this.setTarget(trgt);
                        System.out.println(this + " locked " + trgt);
                    }
                }
            }
        }
    }

    public void attackTarget(Spider attacker) {

        if (this.healthPoint > 1 && attacker.getTarget() != null) {
            if (Math.abs(this.target.x - this.x) < this.attackRange && Math.abs(this.target.y - this.y) < this.attackRange) {
            target.rnd_move = false;
            attacker.rnd_move = false;
            attacker.to_Base = false;
            target.to_Base = false;
                if (attacker.getSide().equals(attacker.getTarget().getSide())) {
                    attacker.setTarget(null);
                    attacker.lockTarget();
                }
                if (attacker.getGroupP().getBoundsInParent().intersects(attacker.getTarget().getGroupP().getBoundsInParent())) {
                    /* System.out.println("FIGHT STARTED");*/
                    target.setTarget(attacker);
                    target.setHealthPoint(Integer.parseInt(target.getHealthPoint()) - (int) (attacker.getDamageAsDouble()) / 60);
                    attacker.setHealthPoint(Integer.parseInt(attacker.getHealthPoint()) - (int) (target.getDamageAsDouble()) / 60);

                    if (attacker.getTarget().healthPoint <= 0 | attacker.healthPoint <= 0) {
                        attacker.setTarget(null);
                        System.out.println("Some of the duelists has been slain!");

                    }
                }
            }
        }
    }

    public void attackTarget(Spider attacker, Spider trgt) {
        if (attacker.healthPoint > 1 && trgt != null) {
            attacker.rnd_move = false;
            trgt.rnd_move = false;
            if (attacker.getSide().equals(trgt.getSide())) {
                attacker.setTarget(null);
                attacker.lockTarget();
                return;
            }
            if (attacker.getGroupP().getLayoutX() < trgt.getGroupP().getLayoutX() && trgt.getGroupP().getLayoutX() < 110) {
                if (attacker.getGroupP().getLayoutY() < trgt.getGroupP().getLayoutY() && trgt.getGroupP().getLayoutY() < 110) {
                    trgt.setTarget(attacker);
                    attacker.setTarget(trgt);
                    trgt.setHealthPoint(Integer.parseInt(trgt.getHealthPoint()) - (int) (attacker.getDamageAsDouble()) / 60);
                    attacker.setHealthPoint(Integer.parseInt(attacker.getHealthPoint()) - (int) (trgt.getDamageAsDouble() / 60));
                    if (attacker.getTarget().healthPoint <= 0 | attacker.healthPoint <= 0) {
                        attacker.setTarget(null);
                        System.out.println("Some of the duelists has been slain!");

                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Spider{" + "name=" + name.getText() +
                ", damage=" + damage + ", health=" + healthPoint + ", side="
                + side + ", x=" + getPosX() + ", y=" + getPosY() + ", survived seconds " +
                getSurvived() + ", active=" + active + ", safe+" + isSafe + '}';
    }

    @Override
    public int compare(Object o1, Object o2) {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Spider spider = (Spider) o;

        if (side != spider.side) return false;
        if (active != spider.active) return false;
        if (isSafe != spider.isSafe) return false;
        if (Double.compare(spider.x, x) != 0) return false;
        if (Double.compare(spider.y, y) != 0) return false;
        if (healthPoint != spider.healthPoint) return false;
        if (Double.compare(spider.damage, damage) != 0) return false;
        if (!counter.equals(spider.counter)) return false;
        if (!target.equals(spider.target)) return false;
        return nameS.equals(spider.nameS);
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public int compareTo(@NotNull Spider o) {
        return nameS.compareTo(o.getName());
    }

    @Override
    public void predictTheWin() {
        System.out.println(name.getText() + " predicts his team will win");
    }

    public void Open(BufferedReader bufferedReader) throws IOException {
        String text;
        text = bufferedReader.readLine();
        if (text.equals("")) {
            text = bufferedReader.readLine();
        }
        if (text.equals("")) {
            text = bufferedReader.readLine();
        }
        String type = text;
        text = bufferedReader.readLine();
        if (text.equals("")) {
            text = bufferedReader.readLine();
        }
        String hp = text;
        text = bufferedReader.readLine();
        if (text.equals("")) {
            text = bufferedReader.readLine();
        }
        String dmg = text;
        text = bufferedReader.readLine();
        if (text.equals("")) {
            text = bufferedReader.readLine();
        }
        String n = text;
        text = bufferedReader.readLine();
        if (text.equals("")) {
            text = bufferedReader.readLine();
        }
        String act = text.equals("true") ? "true" : "false";
        /*setActive(act);*/
        text = bufferedReader.readLine();
        String _s = (text.equals("true")) ? "true" : "false";
        text = bufferedReader.readLine();
        String _x = text;
        text = bufferedReader.readLine();
        String _y = text;
        text = bufferedReader.readLine();
        double _c = Double.parseDouble(text);
        if (type.equals("Spider")) {
            Main.createNewSpider(n, dmg, hp, _s, _x, _y, Main.NAMES, act, _c);
            System.out.println("S");
        }
        if (type.equals("Golem")) {
            Main.createNewGolem(n, dmg, hp, _s, _x, _y, Main.NAMES, act, _c);
            System.out.println("G");
        }
        if (type.equals("IceElemental")) {
            Main.createNewIceElemental(n, dmg, hp, _s, _x, _y, Main.NAMES, act, _c);
            System.out.println("I");
        }
    }

    public void Save(FileWriter fileWriter) throws IOException {
        fileWriter.write("\n");
        if (this instanceof Golem) {
            if (this instanceof IceElemental) {
                fileWriter.write("IceElemental");
            } else {
                fileWriter.write("Golem");
            }
        } else
            fileWriter.write("Spider");
        fileWriter.write("\n");
        fileWriter.write(Integer.toString(this.healthPoint));
        fileWriter.write("\n");
        fileWriter.write(Double.toString(this.damage));
        fileWriter.write("\n");
        fileWriter.write(this.nameS);
        fileWriter.write("\n");
        fileWriter.write(Boolean.toString(isActive()));
        fileWriter.write("\n");
        fileWriter.write(getSide());
        fileWriter.write("\n");
        fileWriter.write(Double.toString(getGroupP().getLayoutX()));
        fileWriter.write("\n");
        fileWriter.write(Double.toString(getGroupP().getLayoutY()));
        fileWriter.write("\n");
        fileWriter.write(Double.toString((counter.getSecondsSurvived() / 60)));
        fileWriter.write("\n");
        fileWriter.write("\n");
    }
}
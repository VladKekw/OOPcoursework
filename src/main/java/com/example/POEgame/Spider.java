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

import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;

interface Predict {
    void predictTheWin();
}

public class Spider implements Predict, Cloneable, Comparable<Spider>, Comparator {
    protected ScrollPane scene;
    static Logger logger = new Logger("D:\\units.txt");
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
    protected int attackRange = 60;


    private String[] suggestedNames = new String[3];


    protected boolean side;
    String sideS;
    String nameS;

    public void setSafe(boolean safe) {
        isSafe = safe;
    }

    protected boolean active = false;

    protected static boolean to_Base = false;

    private boolean isSafe;

    public boolean getSafe() {
        return isSafe;
    }
    private int northCount =0;
    private int southCount =0;
    private int middleCount =0;


    public int getNorthCount() {
        return northCount;
    }

    public void setNorthCount(int northCount) {
        this.northCount = northCount;
    }

    public int getSouthCount() {
        return southCount;
    }

    public void setSouthCount(int southCount) {
        this.southCount = southCount;
    }

    public int getMiddleCount() {
        return middleCount;
    }

    public void setMiddleCount(int middleCount) {
        this.middleCount = middleCount;
    }

    protected boolean rnd_move = false;
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

    public static void SwitchTo_Base() {
        Spider.to_Base = !Spider.to_Base;
    }

    public void SwitchRnd_Move() {
        rnd_move = !rnd_move;
    }

    public boolean isActive() {
        return active;
    }

    private static final int maxHealth = 100;

    static {
        Main.createNewSpider("crawler", "50", "", "false", "1.0", "0.0", Main.NAMES);
        Main.createNewSpider("riftwalker", "70", "70", "false", "50.0", "220.0", Main.NAMES);
        Main.createNewSpider("stalker", "50", "100", "false", "80.0", "330.0", Main.NAMES);

        Main.createNewSpider("hider", "50", "", "true", "470.0", "440.0", Main.NAMES);
        Main.createNewSpider("mindmuck", "50", "", "true", "560.0", "445.0", Main.NAMES);
        Main.createNewSpider("spooker", "50", "", "true", "630.0", "460.0", Main.NAMES);

        Main.createNewGolem("smasher", "50", "", "false", "470.0", "490.0", Main.NAMES);
        Main.createNewGolem("felstomper", "50", "", "true", "560.0", "490.0", Main.NAMES);
        Main.createNewGolem("anicentone", "", "", "", "", "", Main.NAMES);

        Main.createNewIceElemental("iceIncarnate", "50", "", "false", "130.0", "990.0", Main.NAMES);
        Main.createNewIceElemental("stormer", "50", "", "true", "510", "510", Main.NAMES);
        Spider sp = new Spider();
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


        Logger logger = new Logger("D:\\ 1.txt");
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
    }

    // ----------------------------------------------------------------------------------------
    @Override
    protected Object clone() throws CloneNotSupportedException {
        String[] cloned = {"This ", "object ", "was", "cloned"};
        Spider tmp = new Spider();
        tmp.counter = (SurvivedCounter) this.counter.clone();
        if (this instanceof Spider) {
            tmp = new Spider(Integer.parseInt(this.getHealthPoint()), Double.parseDouble(this.getDamage()), "Cloned",
                    this.getSide(), Double.parseDouble(this.getX()), Double.parseDouble(this.getY()), cloned);
            tmp.init(counter, Double.parseDouble(this.getX()), Double.parseDouble(this.getY()), false);

        }
        if (this instanceof Golem) {
            tmp = new Golem(Integer.parseInt(this.getHealthPoint()), Double.parseDouble(this.getDamage()), "Cloned",
                    this.getSide(), Double.parseDouble(this.getX()), Double.parseDouble(this.getY()), cloned);
            tmp.init(counter, Double.parseDouble(this.getX()), Double.parseDouble(this.getY()), false);
        }
        if (this instanceof IceElemental) {
            tmp = new IceElemental(Integer.parseInt(this.getHealthPoint()), Double.parseDouble(this.getDamage()), "Cloned",
                    this.getSide(), Double.parseDouble(this.getX()), Double.parseDouble(this.getY()), cloned);
            tmp.init(counter, Double.parseDouble(this.getX()), Double.parseDouble(this.getY()), true);
        }
        Rectangle rect = Main.findClonePosition(Double.parseDouble(tmp.getX()), Double.parseDouble(tmp.getY()), 75, 75);
        if (rect != null) {
            Main.uni.add(tmp);
            return tmp;
        } else return null;
    }


    public void updateSecondsText(SurvivedCounter c) {

        this.survived.setText(Integer.toString(c.getSecondsSurvived() / 60));
    }


    public void switchActivation() {
        this.active = !this.active;
        if (this.active) {
            this.circle.setFill(Color.YELLOW);

        }
        else this.circle.setFill(Color.LIGHTBLUE);
    }

    public void moveLeft(int gear) {
        if (!active) return;
        double xx = Double.parseDouble(this.getPosX());
         {
            if (gear >= 2) {
                double y = g.getLayoutX() - step - step / 2;
                g.setLayoutX(y);
            } else {
                double y = g.getLayoutX() - step;
                g.setLayoutX(y);
            }
        }
    }

    public void moveUp(int gear) {
        if (!active) return;
        double yy = Double.parseDouble(this.getPosY());
         {
            if (gear >= 2) {
                double y = g.getLayoutY() - step - step / 2;
                g.setLayoutY(y);
            } else {
                double y = g.getLayoutY() - step;
                g.setLayoutY(y);
            }
        }

    }

    public void moveRight(int gear) {
        if (!active) return;
        double xx = Double.parseDouble(this.getPosX());

            if (gear >= 2) {
                double x = g.getLayoutX() + step + step / 2;
                g.setLayoutX(x);
            } else {
                double y = g.getLayoutX() + step;
                g.setLayoutX(y);
            }
    }


    public void moveDown(int gear) {
        if (!active) return;
        double yy = Double.parseDouble(this.getPosY());
         {
            if (gear >= 2) {
                double y = g.getLayoutY() + step + step / 2;
                g.setLayoutY(y);
            } else {
                double y = g.getLayoutY() + step;
                g.setLayoutY(y);
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

    public void receiveDamageFromObjects(Spider target) {
        target.setHealthPoint((target.healthPoint) - 2);

    }

/*    public void dealDamage(Spider target) {
        int d = (int) this.damage;
        int receivedDamage = d / 100;
        target.setHealthPoint(target.healthPoint - receivedDamage);
    }*/

    public static void cleanupCorpses() {
        for (int i = 0; i < Main.uni.size(); i++) {
            Spider sp = Main.uni.get(i);
            if (Integer.parseInt(sp.getHealthPoint()) <= 0) {
                Main.group.getChildren().remove(sp.g);
                logger.log(i + " died ");
                Main.uni.remove(i);
            }

        }
    }


    public static void interCheck() {

///----------------------------------------------------RADIANT BASE
        for (Spider spider : Main.uni) {
            if (spider.getGroup(spider).getBoundsInParent().intersects(Main.radBase.RadiantBaseGroup.getBoundsInParent())) {
                if (spider.getSide().equals("true")) {
                    if (!spider.isSafe) {
                        spider.setSafe(true);
                        spider.setHealthPoint(maxHealth);
                        RadBase.radiantBaseList.add(spider);
                        spider.g.setVisible(false);
                        /*spider.hide(spider);*/
                        if (spider.active) {
                            spider.setActive(false);
                        }
                    }
                } else spider.receiveDamageFromObjects(spider);

            }
///------------------------------------------------------------NorthEAsternTower

            if (spider.getGroup(spider).getBoundsInParent().intersects(Main.northEasternTower.towerGroup.getBoundsInParent())) {
                if (Main.northEasternTower.getState() == Tower.State.NEUTRAL) {
                    if (spider.getSide().equals("true") && !spider.isSafe) {

                            if (spider.active) {
                                spider.setActive(false);
                            }
                            spider.healthPoint+=0.1;
                            Main.northEasternTower.setState(Tower.State.RADIANT);
                            Main.northEasternTower.towerPaint();

                    } else {
                        Main.northEasternTower.setState(Tower.State.DIRE);
                        Main.northEasternTower.towerPaint();
                    }
                }
                if (Main.northEasternTower.getState() == Tower.State.DIRE) {
                    if (spider.getSide().equals("false") && !spider.isSafe && spider.healthPoint < 50) {
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
                    if (spider.getSide().equals("true") && !spider.isSafe && spider.healthPoint < 50) {
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
                    if (spider.getSide().equals("true") && !spider.isSafe) {
                        Main.southWesternTower.setState(Tower.State.RADIANT);
                        Main.southWesternTower.towerPaint();
                       /* spider.setSafe(true);
                        spider.g.setVisible(false);*/
                        spider.setHealthPoint(Integer.parseInt(spider.getHealthPoint()) + 20);
                        /*Main.southernTowerList.add(spider);*/
                    } else {
                        Main.southWesternTower.setState(Tower.State.DIRE);
                        Main.southWesternTower.towerPaint();
                    }
                }
                if (Main.southWesternTower.getState() == Tower.State.DIRE) {
                    if (spider.getSide().equals("false") && !spider.isSafe && spider.healthPoint < 50) {
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
                    if (spider.getSide().equals("true") && !spider.isSafe && spider.healthPoint < 50) {
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
            //// middle
            if (spider.getGroup(spider).getBoundsInParent().intersects(Main.middleEarthTower.towerGroup.getBoundsInParent())) {
                if (Main.middleEarthTower.getState() == Tower.State.NEUTRAL) {
                    if (spider.getSide().equals("true") && !spider.isSafe) {
                        Main.middleEarthTower.setState(Tower.State.RADIANT);
                        Main.middleEarthTower.towerPaint();
                        /*Main.middleTowerList.add(spider);*/
                        /*spider.g.setVisible(false);*/
                        spider.setHealthPoint(Integer.parseInt(spider.getHealthPoint()) + 20);
                        /*spider.setSafe(true);*/
                    } else {
                        Main.middleEarthTower.setState(Tower.State.DIRE);
                        Main.middleEarthTower.towerPaint();
                    }
                }
                if (Main.middleEarthTower.getState() == Tower.State.DIRE) {
                    if (spider.getSide().equals("false") && !spider.isSafe && spider.healthPoint < 50) {
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
                    if (spider.getSide().equals("true") && !spider.isSafe && spider.healthPoint < 50) {
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
                if (spider.getSide().equals("false")) {
                    if (!spider.isSafe) {
                        spider.setSafe(true);
                        spider.setHealthPoint(maxHealth);
                        DireBase.direBaseList.add(spider);
                        spider.switchActivation();
                        spider.g.setVisible(false);
                        if (spider.active) {
                            spider.setActive(false);
                        }
                    }
                } else spider.receiveDamageFromObjects(spider);
            }
            spider.lockTarget();
            spider.attackTarget(spider);
            spider.attackTarget(spider,spider.target);
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
                this.setTarget(trgt);
                if (Math.abs(this.getTarget().x - this.x) < this.attackRange && Math.abs(this.getTarget().y - this.y) < this.attackRange) {
                    if (this.getSide().equals(trgt.getSide())) {
                        if (spiderIterator.hasNext()) {
                            this.setTarget(spiderIterator.next());
                            System.out.println(" NEXT TARGET LOCKED");
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
            target.rnd_move = false;
            attacker.rnd_move = false;
            if (attacker.getSide().equals(attacker.getTarget().getSide())) {
                attacker.setTarget(null);
                attacker.lockTarget();
            }
            if (attacker.getGroupP().getBoundsInParent().intersects(attacker.getTarget().getGroupP().getBoundsInParent())) {
                System.out.println("FIGHT STARTED");
                target.setTarget(attacker);
                target.setHealthPoint(Integer.parseInt(target.getHealthPoint()) - (int) (attacker.getDamageAsDouble())/60);
                attacker.setHealthPoint(Integer.parseInt(attacker.getHealthPoint()) - (int) (target.getDamageAsDouble())/60);

                if (attacker.getTarget().healthPoint <= 0 | attacker.healthPoint <= 0) {
                    attacker.setTarget(null);
                    System.out.println("Some of the duelists has been slain!");

                }
            }
        }
    }

    public void attackTarget(Spider attacker, Spider trgt) {
        if (attacker.healthPoint > 1 && trgt!=null) {
            attacker.rnd_move=false;
            trgt.rnd_move=false;
            if (attacker.getSide().equals(trgt.getSide())) {
                attacker.setTarget(null);
                attacker.lockTarget();
                return;
            }
            if(attacker.getGroupP().getLayoutX()<trgt.getGroupP().getLayoutX() &&trgt.getGroupP().getLayoutX()<110 )
            {
               if(attacker.getGroupP().getLayoutY()<trgt.getGroupP().getLayoutY() && trgt.getGroupP().getLayoutY()<110)
               {
                   trgt.setTarget(attacker);
                   attacker.setTarget(trgt);
                   trgt.setHealthPoint(Integer.parseInt(trgt.getHealthPoint())-(int)(attacker.getDamageAsDouble())/60);
                   attacker.setHealthPoint(Integer.parseInt(attacker.getHealthPoint())-(int)(trgt.getDamageAsDouble()/60));
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

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Spider spider = (Spider) obj;
        return damage == spider.damage && side == spider.side && name.equals(spider.name) && healthPoint == spider.healthPoint;
    }

    @Override
    public int compareTo(@NotNull Spider o) {
        return nameS.compareTo(o.getName());
    }


    @Override
    public void predictTheWin() {
        System.out.println(name.getText() + " predicts his team will win");
    }
}





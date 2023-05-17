package com.example.xixixi;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
// Пункти 4,5,6,8,,11,12 було додано в ЛР №2
// deep clone, comparator, console , stream api nested class, etc


public class Main extends Application {
    public static String[] bodya = {"Bodya", "Boss", "vsih", "pavukiv"};

    public static Background bg;
    static Random random = new Random();
    public static DireBase direBase;
    public static Tower northEasternTower;
    public static Tower southWesternTower;
    public static Tower middleEarthTower;

    public static RadBase radBase;

    public static AnchorPane group = new AnchorPane();
    public static List<Spider> uni = new CopyOnWriteArrayList<>();
    public static ArrayList<Spider> web = new ArrayList<>();
    public static ArrayList<Golem> cave = new ArrayList<>();
    public static ArrayList<IceElemental> river = new ArrayList<>();
    public static ArrayList<Spider> northernTowerList = new ArrayList<>();
    public static ArrayList<Spider> southernTowerList = new ArrayList<>();
    public static ArrayList<Spider> middleTowerList = new ArrayList<>();

    public static final String[] NAMES = {"DarienGray", "Anufrij", "Freddy"
            , "Purdie", "Wilton", "Godfrey",
            "Roswell", "Chelsey", "Adrian", "Bernie"};


    public static String[] createSuggestedNames() {
        String[] names = new String[3];
        for (int i = 0; i < 3; i++) {
            names[i] = NAMES[random.nextInt(10)];
        }
        return names;
    }
    public static Rectangle findClonePosition(double x, double y, double wx, double wy ){
        Rectangle r = new Rectangle();
        r.setX(x);
        r.setY(y);
        r.setWidth(wx);
        r.setHeight(wy);
        double [][] deltas = new double[][]{ {1.0,0.0},{1.0, 1.0},{0.0, 1.0},{-1.0, 1.0},
                {-1.0, 0.0},{-1.0, -1.0},{0.0, -1.0},{1.0, -1.0} };
        for( int i=1; i<=10; ++i ){
            for( int j=0; j<8; ++j ){
                double newx = x + wx*1.1*deltas[j][0]*i;
                double newy = y + wy*1.1*deltas[j][1]*i;
                if( newx<0.0 )continue;
                if( newy<0.0 )continue;
                if( !( (newx+wx)<1531 ))continue;
                if( !( (newy+wy)<800) )continue;
                r.setX(newx);
                r.setY(newy);
                boolean found=false;
                //int count=0;
                for (Spider s : uni) {

                    if( s.getGroupP().getBoundsInParent().intersects( r.getBoundsInParent() )  ){
                        found=true;
                        break;
                    }
                }
                if( !found ){
                    r.setX(newx-1532);
                    r.setY(newy-800);

                    //System.out.println("Result of findClonePosition="+r);
                    return r;

                }
            }
        }
        return null;
    }

    public static ArrayList<String> getSpiderNames() {
        ArrayList<String> spiderNames = new ArrayList<>();

        for (Spider s : web) {
            spiderNames.add(s.toString());
        }

        return spiderNames;
    }

    public static ArrayList<String> getGolemNames() {
        ArrayList<String> golemNames = new ArrayList<>();

        for (Golem g : cave) {
            golemNames.add(g.toString());
        }

        return golemNames;
    }

    public static ArrayList<String> getElemNames() {
        ArrayList<String> elemNames = new ArrayList<>();

        for (IceElemental i : river) {
            elemNames.add(i.toString());
        }
        return elemNames;
    }

    public static ArrayList<String> SpiderGetParamsToChange(int index) {
        Spider s = web.get(index - 1);
        ArrayList<String> array = new ArrayList<>();
        array.add(s.getName());
        array.add(s.getHealthPoint());
        array.add(s.getDamage());
        array.add(s.getSide());
        array.add(s.getX());
        array.add(s.getY());
        return array;
    }

    public static ArrayList<String> GolemGetParamsToChange2(int index) {
        Golem g = cave.get(index - 1);

        ArrayList<String> array = new ArrayList<>();
        array.add(g.getName());
        array.add(g.getHealthPoint());
        array.add(g.getDamage());
        array.add(g.getSide());
        array.add(g.getX());
        array.add(g.getY());
        return array;
    }

    public static ArrayList<String> IceElementalGetParamsToChange3(int index) {
        IceElemental ice = river.get(index - 1);

        ArrayList<String> array = new ArrayList<>();
        array.add(ice.getName());
        array.add(ice.getHealthPoint());
        array.add(ice.getDamage());
        array.add(ice.getSide());
        array.add(ice.getX());
        array.add(ice.getY());
        return array;
    }



    public void SpawnBackground() throws FileNotFoundException {
        bg = new Background();
        group.getChildren().add(bg.getBackGrp());
    }

    ;

    public void lifeCycle() {
        Spider.interCheck();
        Spider.cleanupCorpses();
        for(Spider s: uni){
            s.getCounter().updateSeconds();
            s.updateSecondsText(s.getCounter());
        }
        if(northEasternTower.getState()== Tower.State.DIRE && southWesternTower.getState()== Tower.State.DIRE
                &&middleEarthTower.getState()== Tower.State.DIRE){

        }
        if(northEasternTower.getState()== Tower.State.RADIANT && southWesternTower.getState()== Tower.State.RADIANT
                &&middleEarthTower.getState()== Tower.State.RADIANT){

        }


    }


    public static void createNewSpider(String sName, String sDamage, String sHealth, String sSide, String sX, String sY, String[] sP) {

        System.out.printf("sName=%s sHealth=%s sDamage=%s sSide = %S sX=%s sY=%s \n", sName, sHealth, sDamage, sSide, sX, sY);

        if (sName.equals("")) sName = "Spider";

        int h;
        try {
            h = Integer.parseInt(sHealth);
        } catch (Exception e) {
            h = 100;
        }
        double d;
        try {
            d = Double.parseDouble(sDamage);
        } catch (Exception e) {
            d = 50.0;
        }
        double x;
        try {
            x = Double.parseDouble(sX);
            if(x==0.0){
                x= random.nextDouble(2600);
            }
        } catch (Exception e) {
            x = 5.0;
        }
        double y;
        try {
            y = Double.parseDouble(sY);
            if(y==0.0){
                y= random.nextDouble(2000);
            }
        } catch (Exception e) {
            y = 5.0;
        }
        Spider spider = new Spider(h, d, sName, sSide, x, y, sP);
        Main.web.add(spider);
        Main.uni.add(spider);
    }

    public static void createNewGolem(String sName, String sDamage, String sHealth, String sSide, String sX, String sY, String[] sP) {

        System.out.printf("gName=%s gHealth=%s gDamage=%s gSide = %S gX=%s gY=%s \n", sName, sHealth, sDamage, sSide, sX, sY);

        if (sName.equals("")) sName = "Golem";

        int h;
        try {
            h = Integer.parseInt(sHealth);
        } catch (Exception e) {
            h = 100;
        }
        double d;
        try {
            d = Double.parseDouble(sDamage);
        } catch (Exception e) {
            d = 100.0;
        }

        double x;
        try {
            x = Double.parseDouble(sX);
            if(x==0.0){
                x= random.nextDouble(2600);
            }
        } catch (Exception e) {
            x = 0.0;
        }
        double y;
        try {
            y = Double.parseDouble(sY);
            if(y==0.0){
                y= random.nextDouble(2000);
            }
        } catch (Exception e) {
            y = 0.0;
        }
        Golem golem = new Golem(h, d, sName, sSide, x, y, sP);
        Main.cave.add(golem);
        Main.uni.add(golem);

    }
        public  void  copyActive()throws CloneNotSupportedException{
                        for(Spider s: uni){
                            if(s.isActive()){
                                Rectangle rect = findClonePosition(Double.parseDouble(s.getX()),Double.parseDouble(s.getY()),75,75);
                                if (rect != null){


                                if(s instanceof  Spider){
                                    if(s instanceof Golem){
                                        if(s instanceof IceElemental){
                                            try {
                                                s.cloneElem();

                                            } catch (CloneNotSupportedException e) {
                                                throw new RuntimeException(e);
                                            }
                                            break;
                                        }
                                        try {
                                            s.cloneGolem();

                                        } catch (CloneNotSupportedException e) {
                                            throw new RuntimeException(e);
                                        }
                                        break;
                                    }
                                    try {
                                        s.clone();

                                    } catch (CloneNotSupportedException e) {
                                        throw new RuntimeException(e);
                                    }
                                    s.setY(Double.toString(rect.getY()));
                                    s.setX(Double.toString(rect.getX()));
                                }

                            }
                            }
                        }
        }

    public static void createNewIceElemental(String sName, String sDamage, String sHealth, String sSide, String sX, String sY, String[] sP) {

        System.out.printf("gName=%s gHealth=%s gDamage=%s gSide = %S gX=%s gY=%s \n", sName, sHealth, sDamage, sSide, sX, sY);

        if (sName.equals("")) sName = "Ice Elemental";

        int h;
        try {
            h = Integer.parseInt(sHealth);
        } catch (Exception e) {
            h = 100;
        }
        double d;
        try {
            d = Double.parseDouble(sDamage);
        } catch (Exception e) {
            d = 100.0;
        }

        double x;
        try {
            x = Double.parseDouble(sX);
            if(x==0.0){
                x= random.nextDouble(2600);
            }
        } catch (Exception e) {
            x = 0.0;
        }
        double y;
        try {
            y = Double.parseDouble(sY);
            if(y==0.0){
                y= random.nextDouble(2000);
            }
        } catch (Exception e) {
            y = 0.0;
        }
        IceElemental ice = new IceElemental(h, d, sName, sSide, x, y, sP);
        Main.river.add(ice);
        Main.uni.add(ice);
    }
    public static void changeSpider(String sN, String sD, String sH, String sS, String sX, String sY, String[] sP, int index){
        Spider s=uni.get(index);
        s.setName(sN);
        s.setDamage(sD);
        s.setHealthPoint(Integer.parseInt(sH));
        s.setSide(sS);
        s.setX(sX);
        s.setY(sY);
        s.setSuggestedNames(sP);
    }
    public static void changeGolem(String sN, String sD, String sH, String sS, String sX, String sY, String[] sP, int index){
        Spider s=uni.get(index);
        s.setName(sN);
        s.setDamage(sD);
        s.setHealthPoint(Integer.parseInt(sH));
        s.setSide(sS);
        s.setX(sX);
        s.setY(sY);
        s.setSuggestedNames(sP);
    }
    public static void changeIceElemental(String sN, String sD, String sH, String sS, String sX, String sY, String[] sP, int index){
        Spider s=uni.get(index);
        s.setName(sN);
        s.setDamage(sD);
        s.setHealthPoint(Integer.parseInt(sH));
        s.setSide(sS);
        s.setX(sX);
        s.setY(sY);
        s.setSuggestedNames(sP);
    }

    @Override
    public void start(Stage stage) throws Exception {
        SpawnBackground();

        ScrollPane scrollPane = new ScrollPane(group);
        scrollPane.setMaxHeight(Background.border.getHeight());
        scrollPane.setMaxWidth(Background.border.getWidth());
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        BorderPane layout = new BorderPane();
        layout.setCenter(scrollPane);

        northEasternTower = new Tower(1550, 550, "NorthEastern Tower",northernTowerList);
        southWesternTower = new Tower(750, 1100, "SouthWestern Tower",southernTowerList);
        middleEarthTower = new Tower(1130, 800, "MiddleEarth Tower",middleTowerList);
        group.getChildren().addAll(northEasternTower.towerGroup, southWesternTower.towerGroup, middleEarthTower.towerGroup);

        direBase = new DireBase(Background.border.getWidth()-700, 1510);
        group.getChildren().add(direBase.direBaseGroup);

        radBase = new RadBase(550, 310);
        group.getChildren().add(radBase.RadiantBaseGroup);

        Scene scene = new Scene(layout, 1532, 800);
        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
                    ChooseUnit.choose();

                    for (int i = web.size() - 1; i >= 0; --i) {
                        Spider spider = web.get(i);
                        if (spider.isActive()) {
                            group.getChildren().remove(spider);
                            web.remove(i);
                            spider.getGroup(spider).setVisible(false);
                        }

                    }
                }
            }
        });
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                lifeCycle();
            }
        };

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.DELETE) {

                    for (int i = uni.size() - 1; i >= 0; --i) {
                        Spider spider = uni.get(i);
                        if (spider.isActive()) {
                            group.getChildren().remove(spider);
                            uni.remove(i);
                            spider.getGroup(spider).setVisible(false);
                        }
                    }
                }
                if (keyEvent.getCode() == KeyCode.ESCAPE) {
                    for (Spider spider : uni) {
                        if (spider.isActive()) {
                            spider.switchActivation();
                        }
                    }
                }
                if(keyEvent.isControlDown())
                {
                    if(keyEvent.getCode() == KeyCode.V){

                       try{
                           copyActive();
                       }catch (CloneNotSupportedException e){
                           throw new RuntimeException();
                       }
                    }
                }
                if (keyEvent.getCode() == KeyCode.INSERT) {
                    UnitParams.displayUnit(group.getLayoutX(), group.getLayoutY());
                }
                if (keyEvent.getCode() == KeyCode.W) {
                    for (Spider spider : uni) {
                        if (spider.isActive()) {
                            spider.moveUp();
                        }
                    }
                }
                if (keyEvent.getCode() == KeyCode.S) {
                    for (Spider spider : uni) {
                        if (spider.isActive()) {
                            spider.moveDown();
                        }
                    }
                }
                if (keyEvent.getCode() == KeyCode.D) {
                    for (Spider spider : uni) {
                        if (spider.isActive()) {
                            spider.moveRight();
                        }
                    }
                }
                if (keyEvent.getCode() == KeyCode.A) {

                    for (Spider spider : uni) {
                        if (spider.isActive()) {
                            spider.moveLeft();
                        }
                    }
                }
                if (keyEvent.getCode() == KeyCode.R) {
/*                    Spider.SwitchRnd_Move();
                    if(Spider.getRndMove()){
                        for (Spider spider:web){
                         spider.rndMove();}
                    }
                    */
                }

            }
        });
        stage.setTitle("PoE game");
        timer.start();
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

package com.example.xixixi;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
// Пункти 4,5,6,8,,11,12 було додано в ЛР №2
// deep clone, comparator, console , stream api nested class, etc
// Пункти 1,2,4,7,9,10,14


public class Main extends Application {
    public static String[] bodya = {"Bodya", "Boss", "vsih", "pavukiv"};

    Logger logger = new Logger("D:\\buildings.txt");

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

    public static Rectangle findClonePosition(double x, double y, double wx, double wy) {
        Rectangle r = new Rectangle();
        r.setX(x);
        r.setY(y);
        r.setWidth(wx);
        r.setHeight(wy);
        double[][] deltas = new double[][]{{1.0, 0.0}, {1.0, 1.0}, {0.0, 1.0}, {-1.0, 1.0},
                {-1.0, 0.0}, {-1.0, -1.0}, {0.0, -1.0}, {1.0, -1.0}};
        for (int i = 1; i <= 10; ++i) {
            for (int j = 0; j < 8; ++j) {
                double newx = x + wx * 1.1 * deltas[j][0] * i;
                double newy = y + wy * 1.1 * deltas[j][1] * i;
                if (newx < 0.0) continue;
                if (newy < 0.0) continue;
                if (!((newx + wx) < 1531)) continue;
                if (!((newy + wy) < 800)) continue;
                r.setX(newx);
                r.setY(newy);
                boolean found = false;
                //int count=0;
                for (Spider s : uni) {

                    if (s.getGroupP().getBoundsInParent().intersects(r.getBoundsInParent())) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    r.setX(newx - 1532);
                    r.setY(newy - 800);
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



    public void lifeCycle() {
        Spider.interCheck();
        Spider.cleanupCorpses();
        for (Spider s : uni) {
            s.getCounter().updateSeconds();
            s.updateSecondsText(s.getCounter());
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Congratulations!");
        if (northEasternTower.getState() == Tower.State.DIRE && southWesternTower.getState() == Tower.State.DIRE
                && middleEarthTower.getState() == Tower.State.DIRE) {
            alert.setContentText("The Dire have won!");
            Platform.runLater(alert :: showAndWait);
            alert.setOnCloseRequest(e -> Platform.exit());

        }
        if (northEasternTower.getState() == Tower.State.RADIANT && southWesternTower.getState() == Tower.State.RADIANT
                && middleEarthTower.getState() == Tower.State.RADIANT) {
            alert.setContentText("Radiant have won!");
            Platform.runLater(alert :: showAndWait);
            alert.setOnCloseRequest(e -> Platform.exit());
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
            if (x == 0.0) {
                x = random.nextDouble(2600);
            }
        } catch (Exception e) {
            x = 5.0;
        }
        double y;
        try {
            y = Double.parseDouble(sY);
            if (y == 0.0) {
                y = random.nextDouble(2000);
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
            if (x == 0.0) {
                x = random.nextDouble(2600);
            }
        } catch (Exception e) {
            x = 0.0;
        }
        double y;
        try {
            y = Double.parseDouble(sY);
            if (y == 0.0) {
                y = random.nextDouble(2000);
            }
        } catch (Exception e) {
            y = 0.0;
        }
        Golem golem = new Golem(h, d, sName, sSide, x, y, sP);
        Main.cave.add(golem);
        Main.uni.add(golem);

    }

    public void copyActive() throws CloneNotSupportedException {
        for (Spider s : uni) {
            if (s.isActive()) {
                Rectangle rect = findClonePosition(Double.parseDouble(s.getX()), Double.parseDouble(s.getY()), 75, 75);
                if (rect != null) {


                    if (s instanceof Spider) {
                        if (s instanceof Golem) {
                            if (s instanceof IceElemental) {
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
            if (x == 0.0) {
                x = random.nextDouble(2600);
            }
        } catch (Exception e) {
            x = 0.0;
        }
        double y;
        try {
            y = Double.parseDouble(sY);
            if (y == 0.0) {
                y = random.nextDouble(2000);
            }
        } catch (Exception e) {
            y = 0.0;
        }
        IceElemental ice = new IceElemental(h, d, sName, sSide, x, y, sP);
        Main.river.add(ice);
        Main.uni.add(ice);
    }

    public static void changeSpider(String sN, String sD, String sH, String sS, String sX, String sY, String[] sP, int index) {
        Spider s = uni.get(index);
        s.setName(sN);
        s.setDamage(sD);
        s.setHealthPoint(Integer.parseInt(sH));
        s.setSide(sS);
        s.setX(sX);
        s.setY(sY);
        s.setSuggestedNames(sP);
    }

    public static void changeGolem(String sN, String sD, String sH, String sS, String sX, String sY, String[] sP, int index) {
        Spider s = uni.get(index);
        s.setName(sN);
        s.setDamage(sD);
        s.setHealthPoint(Integer.parseInt(sH));
        s.setSide(sS);
        s.setX(sX);
        s.setY(sY);
        s.setSuggestedNames(sP);
    }

    public static void changeIceElemental(String sN, String sD, String sH, String sS, String sX, String sY, String[] sP, int index) {
        Spider s = uni.get(index);
        s.setName(sN);
        s.setDamage(sD);
        s.setHealthPoint(Integer.parseInt(sH));
        s.setSide(sS);
        s.setX(sX);
        s.setY(sY);
        s.setSuggestedNames(sP);
    }

    public void drawInfo() {
        Stage window = new Stage();
        window.initModality(Modality.NONE);
        window.setTitle("Sorting | Finding units");
        window.setMinWidth(1560);
        window.setMinHeight(1080);
        AnchorPane anchorPane = new AnchorPane();
        VBox layout = new VBox(10);
        Button sortButton = new Button("Sort");
        Button searchButton = new Button("Search");
        searchButton.setAlignment(Pos.CENTER);
        searchButton.setPrefWidth(300);
        Button displayButton = new Button("Display");
        Label healthLabel = new Label("Please enter amount of health the unit has");
        Label damageLabel = new Label("Please enter amount of damage the unit has");
        Label nameLabel = new Label("Please enter unit`s the name");
        Label sideLabel = new Label("Please enter the fraction to which unit belongs to");
        TextField healthField = new TextField();
        TextField damageField = new TextField();
        TextField nameField = new TextField();
        TextField sideField = new TextField();
        ComboBox macroObjectsBox = new ComboBox();
        macroObjectsBox.setPrefWidth(200);
        macroObjectsBox.getItems().add("Dire base");
        macroObjectsBox.getItems().add("Radiant base");
        macroObjectsBox.getItems().add("North-Eastern tower");
        macroObjectsBox.getItems().add("MiddleEarth tower");
        macroObjectsBox.getItems().add("South-Western tower");
        ComboBox sortOptionsBox = new ComboBox();
        sortOptionsBox.getItems().add("Sort by the units name ");
        sortOptionsBox.getItems().add("Sort by the highest damage ");
        sortOptionsBox.getItems().add("Sort by the highest survival time ");
        /*Button homelessButton = new Button("Search for homeless units");
        homelessButton.setPrefWidth(270);*/
        Button countDire = new Button("Count dire units");
        countDire.setPrefWidth(270);
        Button countRadiant = new Button("Count radiant units");
        countRadiant.setPrefWidth(270);
        Button countHomeless = new Button("Count homeless units");
        countHomeless.setPrefWidth(270);
        Label resultLabel = new Label("YOur result will be here");
        resultLabel.setPrefWidth(270);
        resultLabel.setAlignment(Pos.CENTER);


        HBox macroObjects = new HBox(10);
        AnchorPane.setTopAnchor(macroObjects, 25.0);
        AnchorPane.setRightAnchor(macroObjects, 25.0);
        macroObjects.getChildren().addAll(macroObjectsBox, displayButton);

        HBox sortObjects = new HBox(10);
        AnchorPane.setTopAnchor(sortObjects, 85.0);
        AnchorPane.setRightAnchor(sortObjects, 25.0);
        sortObjects.getChildren().addAll(sortOptionsBox, sortButton);

        /*HBox homeless = new HBox(10);
        AnchorPane.setTopAnchor(homeless,145.0);
        AnchorPane.setRightAnchor(homeless,25.0);
        homeless.getChildren().add(homelessButton);*/

        VBox searchObjects = new VBox(10);
        searchObjects.setPrefWidth(300);
        ListView<Spider> searchList = new ListView<>();
        searchList.setPrefHeight(150);
        AnchorPane.setTopAnchor(searchObjects, 25.0);
        AnchorPane.setLeftAnchor(searchObjects, 25.0);
        searchObjects.getChildren().addAll(healthLabel, healthField, damageLabel,
                damageField, nameLabel, nameField, sideLabel, sideField, searchButton, searchList);


        ListView<Spider> sortingList = new ListView<>();
        sortingList.setLayoutY(150);
        sortingList.setPrefWidth(280);
        AnchorPane.setTopAnchor(sortingList, 25.0);
        AnchorPane.setLeftAnchor(sortingList, 370.0);

        AnchorPane.setTopAnchor(countDire, 205.0);
        AnchorPane.setRightAnchor(countDire, 25.0);
        AnchorPane.setTopAnchor(countRadiant, 265.0);
        AnchorPane.setRightAnchor(countRadiant, 25.0);
        AnchorPane.setTopAnchor(countHomeless, 325.0);
        AnchorPane.setRightAnchor(countHomeless, 25.0);
        AnchorPane.setTopAnchor(resultLabel, 385.0);
        AnchorPane.setRightAnchor(resultLabel, 25.0);
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String health = healthField.getText();
               /* if (health == null) {
                    health = "100";
                }*/
                String damage = damageField.getText();

                /*if (damage == null) {
                    damage = "50";
                }*/
                String name = nameField.getText();


                /*if (name == null) {
                    name = "unit404";
                }*/
                String side = sideField.getText();
                if (side.toLowerCase().equals("radiant")) {
                    side = "true";
                } else if (side.toLowerCase().equals("dire")) {
                    side = "false";
                }
                /*if (side == null) {
                    side = "false";
                }*/

                for (Spider s : uni) {
                    List<Spider> updated = new ArrayList<>();
                    if (s.getName().equals(name)) {
                        updated.add(s);
                        for (Spider sp : updated) {
                            List<Spider> updated2 = new ArrayList<>();
                            if (sp.getHealthPoint().equals(health)) {
                                updated2.add(sp);
                                for (Spider spi : updated2) {
                                    List<Spider> updated3 = new ArrayList<>();
                                    if (spi.getDamage().equals(damage)) {
                                        updated3.add(spi);
                                        for (Spider spid : updated3) {
                                            List<Spider> updated4 = new ArrayList<>();
                                            if (spid.getSide().equals(side)) {
                                                updated4.add(spid);
                                                if (searchList.getItems() != null) {
                                                    searchList.getItems().clear();
                                                }
                                                for (Spider pavuk : updated4) {
                                                    searchList.getItems().add(pavuk);
                                                }
                                                break;
                                            }
                                        }
                                    }
                                    if (searchList.getItems() != null) {
                                        searchList.getItems().clear();
                                    }
                                    for (Spider pavuk2 : updated2) {
                                        searchList.getItems().add(pavuk2);
                                    }
                                    break;
                                }
                            }
                            if (searchList.getItems() != null) {
                                searchList.getItems().clear();
                            }
                            for (Spider pavuk3 : updated2) {
                                searchList.getItems().add(pavuk3);
                            }
                            break;
                        }
                    }

                }


            }
        });
        countDire.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int countdire = 0;
                List<Spider> counted = uni.stream().filter(spider -> spider.getSide() == "false").collect(Collectors.toList());
                countdire = counted.size();
                resultLabel.setText(Integer.toString(countdire));

            }
        });
        countRadiant.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int countradiant = 0;
                List<Spider> counted = uni.stream().filter(spider -> spider.getSide() == "true").collect(Collectors.toList());
                countradiant = counted.size();
                resultLabel.setText(Integer.toString(countradiant));
            }
        });
        countHomeless.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int counthomeless = 0;
                List<Spider> counted = uni.stream().filter(spider -> !spider.getSafe()).collect(Collectors.toList());
                counthomeless = counted.size();
                resultLabel.setText(Integer.toString(counthomeless));
            }
        });
        displayButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (macroObjectsBox.getValue().toString().contains("North-Eastern tower")) {
                    if (sortingList.getItems() != null) {
                        sortingList.getItems().clear();
                        for (Spider s : northernTowerList) {
                            sortingList.getItems().add(s);
                        }
                    }
                }
                if (macroObjectsBox.getValue().toString().contains("South-Western tower")) {
                    if (sortingList.getItems() != null) {
                        sortingList.getItems().clear();
                        for (Spider s : southernTowerList) {
                            sortingList.getItems().add(s);
                        }
                    }
                }
                if (macroObjectsBox.getValue().toString().contains("MiddleEarth tower")) {
                    if (sortingList.getItems() != null) {
                        sortingList.getItems().clear();
                        for (Spider s : middleTowerList) {
                            sortingList.getItems().add(s);
                        }
                    }
                }
                if (macroObjectsBox.getValue().toString().contains("Dire base")) {
                    if (sortingList.getItems() != null) {
                        sortingList.getItems().clear();
                        for (Spider s : DireBase.direBaseList) {
                            sortingList.getItems().add(s);
                        }
                    }
                }
                if (macroObjectsBox.getValue().toString().contains("Radiant base")) {
                    if (sortingList.getItems() != null) {
                        sortingList.getItems().clear();
                        for (Spider s : radBase.radiantBaseList) {
                            sortingList.getItems().add(s);
                        }
                    }
                }
            }
        });


        sortButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (sortOptionsBox.getValue().toString().contains("name")) {
                    List<Spider> sorted = uni.stream().sorted().collect(Collectors.toList());
                    if (sortingList.getItems() != null) {
                        sortingList.getItems().clear();
                        for (Spider s : sorted) {
                            sortingList.getItems().add(s);
                        }
                    } else {
                        for (Spider s : sorted) {
                            sortingList.getItems().add(s);
                        }
                    }
                }
                if (sortOptionsBox.getValue().toString().contains("damage")) {
                    List<Spider> sorted = uni.stream().sorted(Comparator.comparingDouble(Spider::getDamageAsDouble).reversed()).collect(Collectors.toList());
                    if (sortingList.getItems() != null) {
                        sortingList.getItems().clear();
                        for (Spider s : sorted) {
                            sortingList.getItems().add(s);
                        }
                    } else {
                        for (Spider s : sorted) {
                            sortingList.getItems().add(s);
                        }
                    }
                }
                if (sortOptionsBox.getValue().toString().contains("time")) {
                    List<Spider> sorted = uni.stream().sorted(Comparator.comparingInt(Spider::getSurvived).reversed()).collect(Collectors.toList());
                    if (sortingList.getItems() != null) {
                        sortingList.getItems().clear();
                        for (Spider s : sorted) {
                            sortingList.getItems().add(s);
                        }
                    } else {
                        for (Spider s : sorted) {
                            sortingList.getItems().add(s);
                        }
                    }
                }
            }
        });


        anchorPane.getChildren().addAll(layout, macroObjects, sortObjects, searchObjects,
                sortingList,/*homeless,*/countRadiant, countDire, countHomeless, resultLabel);
        Scene scene = new Scene(anchorPane, 1000, 500);
        window.setScene(scene);
        window.showAndWait();
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


        direBase = new DireBase(Background.border.getWidth() - 700, 1510);
        logger.log("An ancient building was here since the dawn of the world...");
        group.getChildren().add(direBase.direBaseGroup);

        radBase = new RadBase(550, 310);
        logger.log("The world has never seen a castle that beautiful...");
        group.getChildren().add(radBase.RadiantBaseGroup);

        northEasternTower = new Tower(1550, 550, "NorthEastern Tower", northernTowerList);
        logger.log("NOrtheastern tower was created on x=1550, y=550");
        southWesternTower = new Tower(750, 1100, "SouthWestern Tower", southernTowerList);
        logger.log("SouthWestern tower was created on x=750, y=1100");
        middleEarthTower = new Tower(1130, 800, "MiddleEarth Tower", middleTowerList);
        logger.log("MiddleEarth tower was created on x=1130, y=800");
        group.getChildren().addAll(northEasternTower.towerGroup, southWesternTower.towerGroup, middleEarthTower.towerGroup);

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
                            logger.log(spider + "has been deleted ");
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
                if (keyEvent.isControlDown()) {
                    if (keyEvent.getCode() == KeyCode.V) {

                        try {
                            copyActive();
                        } catch (CloneNotSupportedException e) {
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
                            spider.moveUp(1);
                            logger.log(spider + " had changed his postion");
                        }
                    }
                }
                if (keyEvent.getCode() == KeyCode.S) {
                    for (Spider spider : uni) {
                        if (spider.isActive()) {
                            spider.moveDown(1);
                            logger.log(spider + " had changed his postion");
                        }
                    }
                }
                if (keyEvent.getCode() == KeyCode.D) {
                    for (Spider spider : uni) {
                        if (spider.isActive()) {
                            spider.moveRight(1);
                            logger.log(spider + " had changed his postion");
                        }
                    }
                }
                if (keyEvent.getCode() == KeyCode.A) {

                    for (Spider spider : uni) {
                        if (spider.isActive()) {
                            spider.moveLeft(1);
                            logger.log(spider + " had changed his postion");
                        }
                    }
                }
                if (keyEvent.isShiftDown()) {
                    if (keyEvent.isShiftDown()) {
                        if (keyEvent.getCode() == KeyCode.W) {
                            for (Spider spider : uni) {
                                if (spider.isActive()) {
                                    spider.moveUp(2);
                                    logger.log(spider + " is rapidly aproaching ");
                                }
                            }
                        }
                        if (keyEvent.getCode() == KeyCode.S) {
                            for (Spider spider : uni) {
                                if (spider.isActive()) {
                                    spider.moveDown(2);
                                    logger.log(spider + " is rapidly aproaching ");
                                }
                            }
                        }
                        if (keyEvent.getCode() == KeyCode.D) {
                            for (Spider spider : uni) {
                                if (spider.isActive()) {
                                    spider.moveRight(2);
                                    logger.log(spider + " is rapidly aproaching ");
                                }
                            }
                        }
                        if (keyEvent.getCode() == KeyCode.A) {

                            for (Spider spider : uni) {
                                if (spider.isActive()) {
                                    spider.moveLeft(2);
                                    logger.log(spider + " is rapidly aproaching ");
                                }
                            }
                        }
                    }
                    if (keyEvent.getCode() == KeyCode.I) {
                        drawInfo();
                    }
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

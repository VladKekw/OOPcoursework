package com.example.xixixi;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.util.Random;

import java.io.FileNotFoundException;
import java.util.ArrayList;
// Пункти 4,5,6,8,,11,12 було додано в ЛР №2



public class Main extends Application {
    public static String [] bodya = {"Bodya","Boss", "vsih", "pavukiv"};

    public static Background bg;
    static Random random = new Random();
    public static DireBase direBase;
    public static RadBase radBase;

    public static AnchorPane group = new AnchorPane();

    public static ArrayList<Spider> web = new ArrayList<>();
    public static ArrayList<Object> uni = new ArrayList<>();
    public static ArrayList<Golem> cave = new ArrayList<>();
/*    public static ArrayList<IceElemental> river = new ArrayList<>();*/

    public static final String [] NAMES = {"Darien","Dewayne","Freddy"
            ,"Purdie","Wilton","Godfrey",
            "Roswell","Chesley","Adrian","Bernie"};



    public static String[] createSuggestedNames(){
        String[] names = new String[3];
        for(int i=0; i<3; i++){
            names[i]= NAMES[random.nextInt(9)];
        }
        return names;
    }

    public static ArrayList<String> getSpiderNames(){
        ArrayList<String> spiderNames = new ArrayList<>();

        for( Spider s:web ){
            spiderNames.add(s.toString() );
        }

        return spiderNames;
    }
    public static ArrayList<String> getGolemNames(){
        ArrayList<String> golemNames = new ArrayList<>();

        for( Golem g:cave ){
            golemNames.add(g.toString() );
        }

        return golemNames;
    }
    public static ArrayList<String> SpiderGetParamsToChange(int index ){
        Spider s= web.get(index-1);
        ArrayList<String> array= new ArrayList<String>();
        array.add( s.getName() );
        array.add( s.getHealthPoint() );
        array.add(s.getDamage());
        array.add(s.getSide());
        array.add( s.getX() );
        array.add( s.getY() );
        return array;
    }
    public static ArrayList<String> GolemGetParamsToChange2(int index ){
        Golem g= cave.get(index-1);

        ArrayList<String> array= new ArrayList<String>();
        array.add( g.getName() );
        array.add( g.getHealthPoint() );
        array.add(g.getDamage());
        array.add(g.getSide());
        array.add( g.getX() );
        array.add( g.getY() );
        return array;
    }


    public void SpawnBackground() throws FileNotFoundException {
        bg = new Background();
        group.getChildren().add(bg.getBackGrp());
    };


    public static void createNewSpider(String sName,String sDamage, String sHealth,String sSide, String sX, String sY, String[] sP ){

        System.out.printf("sName=%s sHealth=%s sDamage=%s sSide = %S sX=%s sY=%s \n", sName, sHealth,sDamage,sSide, sX, sY );

        if( sName.equals("") ) sName="Spider";

        int h;
        try {
            h= Integer.parseInt(sHealth);
        }
        catch(Exception e){
            h=100;
        }
        double d;
        try {
            d= Double.parseDouble(sDamage);
        }
        catch(Exception e){
            d=100.0;
        }

        double x;
        try {
            x= Double.parseDouble(sX);
        }
        catch(Exception e){
            x=0.0;
        }
        double y;
        try {
            y= Double.parseDouble(sY);
        }
        catch(Exception e){
            y=0.0;
        }
        boolean a;
        try{
            /*a=Boolean.parseBoolean(sActive);*/
        }
        catch(Exception e) {
            a=false;
        }

        Main.web.add(new Spider(h,d, sName,sSide,x,y,sP) );
    }
    public static void createNewGolem(String sName,String sDamage, String sHealth,String sSide, String sX, String sY, String[] sP ){

        System.out.printf("gName=%s gHealth=%s gDamage=%s gSide = %S gX=%s gY=%s \n", sName, sHealth,sDamage,sSide, sX, sY );

        if( sName.equals("") ) sName="Golem";

        int h;
        try {
            h= Integer.parseInt(sHealth);
        }
        catch(Exception e){
            h=100;
        }
        double d;
        try {
            d= Double.parseDouble(sDamage);
        }
        catch(Exception e){
            d=100.0;
        }

        double x;
        try {
            x= Double.parseDouble(sX);
        }
        catch(Exception e){
            x=0.0;
        }
        double y;
        try {
            y= Double.parseDouble(sY);
        }
        catch(Exception e){
            y=0.0;
        }
        boolean a;
        try{
            /*a=Boolean.parseBoolean(sActive);*/
        }
        catch(Exception e) {
            a=false;
        }

        Main.cave.add(new Golem(h,d, sName,sSide,x,y,sP) );
    }

    @Override
    public void start(Stage stage) throws Exception {
       SpawnBackground();

       direBase = new DireBase(1100,300);
       group.getChildren().add(direBase.g);

        radBase = new RadBase(480,260);
        group.getChildren().add(radBase.g);

        Scene scene = new Scene(group, 1560, 800);
        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
             if( mouseEvent.getButton().equals(MouseButton.SECONDARY) ){
                    ChooseUnit.choose();

                    for(int i=web.size()-1; i>=0; --i )
                    {
                        Spider spider= web.get(i);
                        if( spider.isActive() )

                        {
                            group.getChildren().remove(spider);
                            web.remove(i);
                            spider.getGroup().setVisible(false);
                        }

                    }
                    ;}
            }});

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(keyEvent.getCode()==KeyCode.DELETE){

                    for(int i=web.size()-1; i>=0; --i )
                    {
                        Spider spider= web.get(i);
                        if( spider.isActive() )
                        {
                            group.getChildren().remove(spider);
                            web.remove(i);
                            spider.getGroup().setVisible(false);
                        }

                }
                }
                if(keyEvent.getCode()==KeyCode.ESCAPE){
                    for(Spider spider : web){
                        if(spider.isActive()){
                            spider.switchActivation();

                        }
                    }

                }
                if(keyEvent.getCode()==KeyCode.INSERT){
                    UnitParams.displayUnit(group.getLayoutX(),group.getLayoutY() );
                }
                if (keyEvent.getCode()==KeyCode.UP) {
                    for(Spider spider:web){
                        if(spider.isActive())
                        spider.moveUp();
                    }
                    for(Golem golem: cave){
                        if(golem.isActive())
                        golem.moveUp();
                    }
                }  if (keyEvent.getCode()==KeyCode.DOWN) {
                    for (Spider spider:web){
                        if(spider.isActive())
                        spider.moveDown();
                    }
                    for(Golem golem: cave){
                        if(golem.isActive())
                        golem.moveDown();
                    }
                }
                if(keyEvent.getCode()==KeyCode.RIGHT){
                    for (Spider spider:web){
                        if(spider.isActive())
                        spider.moveRight();
                    }
                    for(Golem golem: cave){
                        if(golem.isActive())
                        golem.moveRight();
                    }
                       }
                if(keyEvent.getCode()==KeyCode.LEFT){
                    for (Spider spider:web){
                        if(spider.isActive())
                        spider.moveLeft();
                    }
                    for(Golem golem: cave){
                        if(golem.isActive())
                        golem.moveLeft();
                    }
                }
                if(keyEvent.getCode()==KeyCode.R){
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
        stage.setScene(scene);
        stage.show();
        }

    public static void main(String[] args) {
        launch();
    }
}

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

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main extends Application {

    public static Background bg;
    public static DireBase direBase;
    public static RadBase radBase;

    public static AnchorPane group = new AnchorPane();
    public static Image imageSpider;
    public static ImageView imgvSpider;

    public static ArrayList<Spider> web = new ArrayList<>();
    public static ArrayList<String> getNames(){
        ArrayList<String> spiderNames = new ArrayList<>();

        for( Spider s:web ){
            spiderNames.add(s.toString() );
        }

        return spiderNames;
    }
    public static ArrayList<String> getParamsToChange(int index ){
        Spider s= web.get(index);

        ArrayList<String> array= new ArrayList<String>();
        array.add( s.getName() );
        array.add( s.getHealthPoint() );
        array.add(s.getDamage());
        array.add(s.getSide());
        array.add( s.getX() );
        array.add( s.getY() );
        return array;
    }
    public void SpawnBackground() throws FileNotFoundException {
        bg = new Background();
        group.getChildren().add(bg.getBackGrp());
    };


    public static void createNewSpider(String sName,String sDamage, String sHealth,String sSide, String sX, String sY ){

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

        Main.web.add(new Spider(h,d, sName,sSide,x,y) );
    }

    @Override
    public void start(Stage stage) throws Exception {
       SpawnBackground();

       direBase = new DireBase(1100,300);
       group.getChildren().add(direBase.g);

        radBase = new RadBase(480,260);
        group.getChildren().add(radBase.g);

        Scene scene = new Scene(group, 1920, 1000);
        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if( mouseEvent.getButton().equals(MouseButton.PRIMARY) ){
                    UnitParams.displayUnit(mouseEvent.getX(), mouseEvent.getY() );
                }
                else if( mouseEvent.getButton().equals(MouseButton.SECONDARY) ){
                    ChooseUnit.choose(mouseEvent.getX(), mouseEvent.getY());

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
                if (keyEvent.getCode()==KeyCode.W) {
                    for(Spider spider:web){
                        spider.moveUp();
                    }
                }  if (keyEvent.getCode()==KeyCode.S) {
                    for (Spider spider:web){
                        spider.moveDown();
                    }
                }
                if(keyEvent.getCode()==KeyCode.D){
                    for (Spider spider:web){
                        spider.moveRight();
                    }
                }
                if(keyEvent.getCode()==KeyCode.A){
                    for (Spider spider:web){
                        spider.moveLeft();
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

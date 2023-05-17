package com.example.xixixi;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class RadBase {
    public static ArrayList<Spider> radiantBaseList = new ArrayList<>();

    private Image i;
    private ImageView iv;
    private Rectangle r;
    public Group RadiantBaseGroup;

    private Label l;

    RadBase(double x, double y) throws FileNotFoundException {

        i = new Image(Main.class.getResource("radiant.png").toString(), 100, 100, false, false);
        ImageView imageView = new ImageView(i);
        imageView.setX(x);
        imageView.setY(y);
        imageView.setPreserveRatio(true); //Setting the preserve ratio of the image view
        this.iv = imageView;

        this.l = new Label("Radiant");
        this.l.setFont(new Font("Arial", 20));
        this.l.setTextFill(Color.WHITE);
        this.l.setTranslateX(x + 15);
        this.l.setTranslateY(y + 100);

        this.r = new Rectangle(x - 28, y - 30, 150, 150);
        this.r.setFill(Color.TRANSPARENT);
        this.r.setStroke(Color.WHITE);

        this.RadiantBaseGroup = new Group();
        this.RadiantBaseGroup.getChildren().addAll(this.r, this.iv, this.l);

        this.RadiantBaseGroup.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Stage window = new Stage();
                window.initModality(Modality.APPLICATION_MODAL);
                window.setTitle("List of units in radiant base");
                window.setMinWidth(300);
                window.setMinHeight(150);
                VBox layout = new VBox(15);
                layout.setAlignment(Pos.CENTER);
                ComboBox cBox = new ComboBox();
                int count = 1;
                for (Spider s : radiantBaseList) {
                    cBox.getItems().add(count + " " + s);
                    count++;
                }
                Button okButton = new Button("OK");
                okButton.setOnAction(e -> {
                    String[] str = cBox.getValue().toString().split(" ");
                    System.out.println(str[0]);
                    for (int i = 0; i < radiantBaseList.size(); i++) {
                        Spider spider = radiantBaseList.get(Integer.parseInt(str[0]) - 1);
                        radiantBaseList.remove(Integer.parseInt(str[0]) - 1);
                        spider.getGroup(spider).setLayoutX(Double.parseDouble(spider.getPosX())+100);
                        spider.getGroup(spider).setLayoutY(Double.parseDouble(spider.getPosY())+100);
                        spider.setSafe(false);
                        spider.getGroup(spider).setVisible(true);


                    }
                    window.close();
                });
                layout.getChildren().addAll(cBox, okButton);
                Scene scene = new Scene(layout, 300, 150);
                window.setScene(scene);
                window.showAndWait();
            }
        });
    }
}
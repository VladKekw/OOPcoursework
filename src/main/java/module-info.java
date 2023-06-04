module com.example.xixixi {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
    requires annotations;
    requires javafx.graphics;

    opens com.example.POEgame to javafx.fxml;
    exports com.example.POEgame;
}
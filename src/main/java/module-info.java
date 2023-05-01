module com.example.xixixi {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
    requires annotations;

    opens com.example.xixixi to javafx.fxml;
    exports com.example.xixixi;
}
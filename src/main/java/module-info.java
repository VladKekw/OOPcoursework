module com.example.xixixi {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;

    opens com.example.xixixi to javafx.fxml;
    exports com.example.xixixi;
}
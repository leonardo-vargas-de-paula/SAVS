module com.example.sisapsoo {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens com.example.sisapsoo to javafx.fxml;
    exports com.example.sisapsoo;
    exports com.example.sisapsoo.controller;
    opens com.example.sisapsoo.controller to javafx.fxml;
}
module com.example.sisapsoo {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.desktop;

    opens com.example.sisapsoo.model to javafx.base, org.hibernate.orm.core;

    opens com.example.sisapsoo.controller to javafx.fxml;

    opens com.example.sisapsoo to javafx.fxml;
    exports com.example.sisapsoo;
}
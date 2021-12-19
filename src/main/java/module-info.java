module com.example.map_hsg6 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.graphics;
    requires org.jetbrains.annotations;
    requires java.sql;

    opens com.example.map_hsg6 to javafx.fxml;
    exports com.example.map_hsg6;
}
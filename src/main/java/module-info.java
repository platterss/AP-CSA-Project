module com.example.apcsaproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    // requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens com.example.apcsaproject to javafx.fxml;
    exports com.example.apcsaproject;
    exports com.example.apcsaproject.controller;
    opens com.example.apcsaproject.controller to javafx.fxml;
}
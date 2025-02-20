module com.system {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires java.sql;
    requires annotations;
    requires java.desktop;
    requires c3p0;
    requires javafx.media;
    requires java.naming;
    requires org.yaml.snakeyaml;

    exports com.system.DAO.entity;
    exports com.system.JavaFX.view;
    opens com.system.JavaFX.view to javafx.fxml;
    exports com.system.JavaFX.controller;
    opens com.system.JavaFX.controller to javafx.fxml;
    exports com.system.DAO.Impl;
    exports com.system;
    opens com.system to javafx.fxml;
    exports com.system.JavaFX.pagemodule;
    opens com.system.JavaFX.pagemodule to javafx.fxml;
}
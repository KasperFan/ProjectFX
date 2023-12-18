module com.example.projectsystemboot {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires java.sql;
    requires annotations;
    requires java.desktop;
    requires c3p0;
    requires java.naming;


    exports com.system.boot;
    exports com.system.test;
    opens com.system.boot to javafx.fxml;
    exports com.system.gui;
    opens com.system.gui to javafx.fxml;
}
module com.example.projectsystemboot {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires annotations;
    requires java.desktop;
    requires c3p0;
    requires java.naming;


    opens com.example.projectfx to javafx.fxml;
    exports com.example.projectfx;
    exports com.example.boot;
    opens com.example.boot to javafx.fxml;
}
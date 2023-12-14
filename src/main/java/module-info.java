module com.example.projectfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.projectfx to javafx.fxml;
    exports com.example.projectfx;
}
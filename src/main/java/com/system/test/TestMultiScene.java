package com.system.test;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TestMultiScene extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        HBox hBox = new HBox();
        Button clickForNextStage = new Button("Click for next stage");
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().add(clickForNextStage);
        StackPane pane = new StackPane();
        pane.getChildren().add(new TextField());

        primaryStage.setTitle("Test Multi Scene");
        primaryStage.setScene(new Scene(hBox, 300, 250));
        primaryStage.show(); // (1)

        Stage stage = new Stage();
        stage.setTitle("Second Stage");
        stage.setScene(new Scene(pane, 200, 300));

        primaryStage.setOnCloseRequest(event -> {
            System.out.println("Stage is closing");
        });

        clickForNextStage.setOnAction(event -> {
            stage.show();
        });
    }
}

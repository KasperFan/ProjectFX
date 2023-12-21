package com.system.JavaFX.view;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class HelloFX extends Application {

    public static void main(String[] args) {
        launch(args);
        // 归并排序

    }

    @Override
    public void start(Stage primaryStage) {
        // 在Pane对象中放入一个Label对象
        Pane pane = new Pane();
        // 在pane对象中放入一个Label对象，并实现Label对象中的文字逐个显示
        pane.getChildren().add(new Label("Hello JavaFX!"));
        // 在pane对象中放入一个Button对象
        pane.getChildren().add(new Button("OK"));

    }
}

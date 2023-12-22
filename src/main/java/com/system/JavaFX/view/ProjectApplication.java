package com.system.JavaFX.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class ProjectApplication extends Application {
    public static boolean isLogin = false;
    @Override
    public void start(@NotNull Stage stage) throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(ProjectApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader1.load(), 600, 400);
        stage.setTitle("中国航空航天展示系统");
        stage.setScene(scene);
        stage.show();

        FXMLLoader fxmlLoader2 = new FXMLLoader(ProjectApplication.class.getResource("menu-view.fxml"));
        Scene scene2 = new Scene(fxmlLoader2.load(), 1000, 680);
        Stage stage1 = new Stage();
        stage1.setTitle("中国航空航天展示系统面板");
        stage1.setScene(scene2);

        stage.setOnHidden(event -> {
            stage.close();
            if (isLogin) {
                stage1.show();
            }
        });

        stage1.setOnCloseRequest(event -> {
            isLogin = false;
            stage1.close();
            stage.show();
        });

    }

    public static void launch(String[] args) {
        launch();
    }
}
package com.system.JavaFX.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class ProjectApplication extends Application {
    public static boolean isLogin = false;
    public static boolean isAdmin = false;
    @Override
    public void start(@NotNull Stage stage) throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(ProjectApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader1.load(), 600, 400);
        stage.setResizable(false);
        stage.setTitle("中国航空航天展示系统");
        stage.setScene(scene);
        stage.show();

        Stage stage1 = new Stage();

        stage.setOnHidden(event -> {
            stage.close();
            if (isLogin) {
                try {
                    loadScene(stage1);
                } catch (IOException ignored) {}
                stage1.show();
            }
        });

        stage1.setOnHidden(event -> {
            isLogin = false;
            stage1.close();
            stage.show();
        });
    }

    public static void loadScene(Stage stage1) throws IOException {
        FXMLLoader fxmlLoader2 = new FXMLLoader(ProjectApplication.class.getResource("menu-view.fxml"));
        Scene scene2 = new Scene(fxmlLoader2.load(), 1000, 680);
        stage1.setResizable(false);
        stage1.setTitle("中国航空航天展示系统面板");
        stage1.setScene(scene2);
    }
    public static void launch(String[] args) {
        launch();
    }
}
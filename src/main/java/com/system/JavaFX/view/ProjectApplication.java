package com.system.JavaFX.view;

import com.system.JavaFX.pagemodule.SceneLoader;
import javafx.application.Application;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class ProjectApplication extends Application {
    public static boolean isLogin = false;
    public static boolean isAdmin = false;
    public static boolean isRoot = false;
    public static Map<String, Object> configData;
    static {
        try (InputStream inputStream = ProjectApplication.class.getClassLoader().getResourceAsStream("config.yaml")) {
            Yaml yaml = new Yaml();
            // 读取 YAML 文件并转换为 Map
            configData = yaml.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }
    @Override
    public void start(@NotNull Stage stage) throws IOException {
        SceneLoader.loadScene(stage, "hello-view.fxml", 600, 400, "中国航空航天展示系统");
        stage.show();

        Stage stage1 = new Stage();

        stage.setOnHidden(event -> {
            stage.close();
            if (isLogin) {
                try {
                    SceneLoader.loadScene(
                            stage1,
                            "menu-view.fxml",
                            1000, 680,
                            "中国航空航天展示系统面板"
                    );
                    stage1.show();
                } catch (IOException ignored) {}
            }
        });

        stage1.setOnHidden(event -> {
            isLogin = false;
            stage1.close();
            stage.show();
        });
    }

    public static void launch(String ... args) {
        Application.launch(args);
    }
}
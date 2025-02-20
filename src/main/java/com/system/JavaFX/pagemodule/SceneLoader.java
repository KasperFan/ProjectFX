package com.system.JavaFX.pagemodule;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class SceneLoader {
    private static final String fxmlBasePath = "/com/system/JavaFX/view/%s";
    public static Object object = null;
    /**
     * 当初在 ProjectApplication 类中参考了自带的 start(Stage stage) throws IOException 写的loadScene如下：<blockquote><pre>{@code
     * public static void loadScene(@NotNull Stage stage1) throws IOException {
     *         FXMLLoader fxmlLoader2 = new FXMLLoader(ProjectApplication.class.getResource("menu-view.fxml"));
     *         Scene scene2 = new Scene(fxmlLoader2.load(), 1000, 680);
     *         stage1.setResizable(false);
     *         stage1.setTitle("中国航空航天展示系统面板");
     *         stage1.setScene(scene2);
     *     }
     * }</pre></blockquote>
     * 后由于多界面的需要，遂将其封装好放在 SceneLoader 类中供使用，同时重写 start()
     * */
    public static void loadScene(@NotNull Stage stage, String pageFxmlName,
                                 double width, double height, String sceneTitle,
                                 @NotNull Object... objects) throws IOException {
        object = objects.length == 1 ? objects[0] : object;
        FXMLLoader fxmlLoader = new FXMLLoader(SceneLoader.class.getResource(String.format(fxmlBasePath, pageFxmlName)));
        Scene scene = new Scene(fxmlLoader.load(), width, height);
        stage.setResizable(false);
        stage.setTitle(sceneTitle);
        stage.setScene(scene);
    }
}

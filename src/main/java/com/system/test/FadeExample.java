package com.system.test;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FadeExample extends Application {

    @Override
    public void start(Stage stage) {
        // 创建一个圆形节点，设置半径和颜色
        Circle circle = new Circle(100, Color.RED);
        // 创建一个面板，将圆形添加到面板中
        Pane pane = new Pane(circle);
        // 创建一个场景，将面板添加到场景中
        Scene scene = new Scene(pane, 300, 200);
        // 将场景设置到舞台中
        stage.setScene(scene);
        // 显示舞台
        stage.show();

        // 创建一个淡入淡出过渡，指定节点，持续时间，起始不透明度和终止不透明度
        FadeTransition fade = new FadeTransition(Duration.seconds(2), circle);
        fade.setFromValue(1); // 起始不透明度为1，即完全不透明
        fade.setToValue(0); // 终止不透明度为0，即完全透明
        fade.setCycleCount(FadeTransition.INDEFINITE); // 无限循环
        fade.setAutoReverse(true); // 自动反转
        // 开始播放淡入淡出效果
        fade.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

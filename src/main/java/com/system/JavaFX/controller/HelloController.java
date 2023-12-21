package com.system.JavaFX.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.util.Optional;

public class HelloController {
    public TextField nameInput;
    public CheckBox ifAdminMode;
    public PasswordField passwordInout;
    public Button loginButton;
    public Button signButton;
    @FXML
    private Label welcomeText;

    /**
     * 点击登录按钮事件
     * 在fxml标签的onAction属性上使用#方法名使用，如onAction="#onHelloButtonClick"
     */
    @FXML
    protected void onHelloButtonClick() {
//        welcomeText.setText("Welcome to JavaFX Application!");
        // 创建一个确认框
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("确认框");
        alert.setHeaderText("你要注册管理员用户吗？");
        Optional<ButtonType> buttonType = alert.showAndWait();
        // 点击确定和取消的操作
        if (buttonType.get() == ButtonType.OK) {
            signButton.setText("admin"+nameInput.getText());
            System.out.println("确认");
        }else{
            System.out.println("取消");
            signButton.setText(nameInput.getText());
        }
    }
}
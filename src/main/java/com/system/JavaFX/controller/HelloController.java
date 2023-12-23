package com.system.JavaFX.controller;

import com.system.DAO.polo.User;
import com.system.DAO.utils.SHA256;
import com.system.Service.UserDaoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import static com.system.JavaFX.view.ProjectApplication.isLogin;
import static com.system.JavaFX.view.ProjectApplication.isAdmin;

import java.util.Optional;

public class HelloController {
    @FXML
    public AnchorPane rootHelloPane;
    private boolean signCheck = false;
    private boolean loginCheck = false;
    @FXML
    public Button backButton;
    @FXML
    private TextField nameInput;
    @FXML
    public CheckBox ifAdminMode;
    @FXML
    public PasswordField passwordInout;
    @FXML
    public Button loginButton;
    @FXML
    public Button signButton;
    @FXML
    public PasswordField adminToken;

    /**
     * 点击登录按钮事件
     * <br>
     * 在fxml标签的onAction属性上使用#方法名使用，如onAction="#onHelloButtonClick"
     */
    @FXML
    protected void onLoginButtonClick() {
        if (!loginCheck) {
            loginCheck = true;
            loginButton.setLayoutX(244.0);
            backButton.setLayoutX(159.0);
            backButton.setVisible(true);
            signButton.setVisible(false);
            nameInput.setVisible(true);
            passwordInout.setVisible(true);
        } else {
            try (UserDaoImpl userDao = new UserDaoImpl("user", "uid", "name", "pswd_sha", "is_admin")) {
                var loginUser = userDao.getUserByName(nameInput.getText());
                if (loginUser != null && loginUser.getPassword().equals(SHA256.getSHA256(passwordInout.getText()))) {
                    isAdmin = loginUser.isAdmin();
                    isLogin = true;
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "登录成功");
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.get() == ButtonType.OK) {
                        rootHelloPane.getScene().getWindow().hide();
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "密码错误，或用户不存在！").showAndWait();
                }
            } catch (Exception any) {
                any.printStackTrace();
            }
        }
    }

    @FXML
    public void onSignButtonClick() {
        if (!signCheck) {
            signCheck = true;
            backButton.setLayoutX(396.0);
            backButton.setVisible(true);
            loginButton.setVisible(false);
            nameInput.setVisible(true);
            passwordInout.setVisible(true);
            signButton.setLayoutX(244.0);
            ifAdminMode.setVisible(true);
        } else {
            try (UserDaoImpl userDao = new UserDaoImpl("user", "uid", "name", "pswd_sha", "is_admin")) {
                if (ifAdminMode.isSelected()) {
                    // 创建一个确认框
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("确认框");
                    alert.setHeaderText("你要注册管理员用户吗？");
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.get() == ButtonType.OK) {
                        if (adminToken.getText().equals("Admin")) {
                            var result = userDao.addUser(new User(nameInput.getText(), passwordInout.getText(), true));
                            if (!result) {
                                new Alert(Alert.AlertType.ERROR, "注册失败，已存在该用户").showAndWait();
                            } else {
                                new Alert(Alert.AlertType.INFORMATION, "注册成功").showAndWait();
                                loginCheck = !loginCheck;
                                signCheck = !signCheck;
                                backButton.setLayoutX(279.0);
                                backButton.setVisible(false);
                                signButton.setLayoutX(367.0);
                                signButton.setVisible(false);
                                loginButton.setVisible(true);
                                loginButton.setLayoutX(244.0);
                                adminToken.setVisible(false);
                                ifAdminMode.setVisible(false);
                            }
                        } else {
                            new Alert(Alert.AlertType.ERROR, "管理员令牌错误").showAndWait();
                        }
                        System.out.println("确认");
                    } else {
                        signCheck = !signCheck;
                        backButton.setLayoutX(279.0);
                        loginButton.setVisible(true);
                        nameInput.setVisible(false);
                        passwordInout.setVisible(false);
                        backButton.setVisible(false);
                        adminToken.setVisible(false);
                        ifAdminMode.setVisible(false);
                        signButton.setLayoutX(367.0);
                    }
                } else {
                    var result = userDao.addUser(new User(nameInput.getText(), passwordInout.getText(), false));
                    if (result) {
                        new Alert(Alert.AlertType.INFORMATION, "注册成功").showAndWait();
                        loginCheck = !loginCheck;
                        signCheck = !signCheck;
                        backButton.setLayoutX(279.0);
                        backButton.setVisible(false);
                        signButton.setLayoutX(367.0);
                        signButton.setVisible(false);
                        loginButton.setVisible(true);
                        loginButton.setLayoutX(244.0);
                        adminToken.setVisible(false);
                        ifAdminMode.setVisible(false);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace(System.out);
                new Alert(Alert.AlertType.ERROR, "注册失败，已存在该用户").showAndWait();
            }
            // 点击确定和取消的操作
//            if (buttonType.get() == ButtonType.OK) {
//                signButton.setText("admin"+nameInput.getText());
//                System.out.println("确认");
//            }else{
//                System.out.println("取消");
//                signButton.setText(nameInput.getText());
//            }
        }
    }

    @FXML
    public void onAdminModeClick(ActionEvent actionEvent) {
        adminToken.setVisible(ifAdminMode.isSelected());
    }

    @FXML
    public void onBackButtonClick(ActionEvent actionEvent) {
        if (signCheck) {
            signCheck = false;
            backButton.setLayoutX(279.0);
            loginButton.setVisible(true);
            nameInput.setVisible(false);
            passwordInout.setVisible(false);
            backButton.setVisible(false);
            adminToken.setVisible(false);
            ifAdminMode.setVisible(false);
            signButton.setLayoutX(367.0);
        } else if (loginCheck) {
            loginCheck = false;
            backButton.setLayoutX(159.0);
            signButton.setVisible(true);
            backButton.setVisible(false);
            nameInput.setVisible(false);
            passwordInout.setVisible(false);
            loginButton.setLayoutX(130.0);
        }
    }

    public void enterKey(KeyEvent keyEvent) {
        if (keyEvent.getCode().toString().equals("ENTER")) {
            if (signCheck) {
                onSignButtonClick();
            } else if (loginCheck) {
                onLoginButtonClick();
            }
        }
        keyEvent.consume(); // 消费事件，不再向下传递，也就是不再执行onKeyPressed方法，这样就不会影响到其他按键的监听事件。
        System.out.println(keyEvent.getCode().toString()); // 打印按键代码，例如ENTER、SPACE等。
    }
}
package com.system.JavaFX.controller;

import com.system.DAO.dao.AstronautDao;
import com.system.DAO.dao.EventDao;
import com.system.DAO.dao.UserDao;
import com.system.DAO.entity.Astronaut;
import com.system.DAO.entity.Rocket;
import com.system.DAO.entity.User;
import com.system.DAO.Impl.AstronautDaoImpl;
import com.system.DAO.Impl.EventDaoImpl;
import com.system.DAO.Impl.RocketDaoImpl;
import com.system.DAO.Impl.UserDaoImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.util.List;
import java.util.Optional;

import com.system.DAO.entity.Event;
import javafx.stage.Stage;

import static com.system.JavaFX.view.ProjectApplication.isAdmin;
import static com.system.JavaFX.view.ProjectApplication.isRoot;

public class MenuController {
    @FXML
    public TableView<User> userTable;
    @FXML
    public TableColumn<User, Integer> uid;
    @FXML
    public TableColumn<User, String> uName;
    @FXML
    public TableColumn<User, String> uPswd;
    @FXML
    public TableColumn<User, Boolean> uIsAdmin;
    @FXML
    public AnchorPane userBlock;
    @FXML
    public TabPane tabPane;
    private boolean isHistory = false;
    private boolean isPaused = false;
    private boolean isList = false;
    @FXML
    public Menu adminButton;
    @FXML
    public ObservableList<User> users;
    @FXML
    public ObservableList<Event> eventList;
    @FXML
    public TableColumn<Event, Integer> eID;
    @FXML
    public TableColumn<Event, String> eName;
    @FXML
    public TableColumn<Event, String> eRoc;
    @FXML
    public TableColumn<Event, String> eTime;
    @FXML
    public TableColumn<Event, String> eAstn;
    @FXML
    public TableColumn<Event, String> eMean;
    @FXML
    public ObservableList<Rocket> rocketsList;
    @FXML
    public TableColumn<Rocket, Integer> rID;
    @FXML
    public TableColumn<Rocket, String> rName;
    @FXML
    public TableColumn<Rocket, String> rTime;
    @FXML
    public TableColumn<Rocket, Integer> rOrbit;
    @FXML
    public ObservableList<Astronaut> astronautsList;
    @FXML
    public TableColumn<Astronaut, Integer> aID;
    @FXML
    public TableColumn<Astronaut, String> aName;
    @FXML
    public TableColumn<Astronaut, String> aAge;
    @FXML
    public TableColumn<Astronaut, String> aSex;
    @FXML
    public Pane table;
    @FXML
    public TableView<Event> eventTable;
    @FXML
    public TableView<Rocket> rocketTable;
    @FXML
    public TableView<Astronaut> astronautTable;
    @FXML
    public Button listButton;
    @FXML
    public Button pauseButton;
    @FXML
    private Button historyButton;
    @FXML
    private MediaPlayer historyPlayer;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private MediaView mediaView;

    final RocketDaoImpl rocketDao = new RocketDaoImpl("rocket",
            "rocketID",
            "rocketName",
            "launchDate",
            "in_orbitTime");
    final EventDao eventDao = new EventDaoImpl();
    final AstronautDao astronautDao = new AstronautDaoImpl();
    final UserDao userDao = new UserDaoImpl("user",
            "uid",
            "name",
            "pswd_sha",
            "is_admin");

    public void historyClick() {
        if (!isHistory) {
            isHistory = true;
            listButton.setVisible(false);
            pauseButton.setVisible(true);
            historyButton.setText("返回");
            mediaView.setVisible(true);
            historyPlayer.play();
        } else {
            isHistory = false;
            listButton.setVisible(true);
            pauseButton.setVisible(false);
            historyButton.setText("历史回顾");
            mediaView.setVisible(false);
            historyPlayer.pause();
        }
    }

    public void pauseButtonClick() {
        if (isHistory && !isPaused) {
            isPaused = true;
            pauseButton.setText("继续");
            historyPlayer.pause();
        } else if (isHistory) {
            isPaused = false;
            pauseButton.setText("暂停");
            historyPlayer.play();
        }
    }

    public void listButtonClick() {
        userBlock.setVisible(isRoot);
        adminButton.setVisible(isAdmin);
        userTable.setVisible(isRoot);
        if (!isList) {
            isList = true;
            initData();
            listButton.setText("返回");
            table.setVisible(true);
        } else {
            isList = false;
            listButton.setText("列表详情");
            table.setVisible(false);
        }
    }

    public void logOut() {
        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "确认注销吗？").showAndWait();
        if (buttonType.isPresent() && buttonType.get() == ButtonType.OK) {
            new Alert(Alert.AlertType.INFORMATION, "注销成功").showAndWait();
            rootPane.getScene().getWindow().hide();
        }
    }

    public void initializeRocket(List<Rocket> list) {
        rocketsList = FXCollections.observableArrayList();
        rID.setCellValueFactory(new PropertyValueFactory<>("rocketID"));
        rName.setCellValueFactory(new PropertyValueFactory<>("rocketName"));
        rTime.setCellValueFactory(new PropertyValueFactory<>("launchDate"));
        rOrbit.setCellValueFactory(new PropertyValueFactory<>("inOrbitTime"));
        rocketsList.addAll(list);
        rocketTable.setItems(rocketsList);
    }

    public void initializeEvent(List<Event> list) {
        eventList = FXCollections.observableArrayList();
        eID.setCellValueFactory(new PropertyValueFactory<>("id"));
        eName.setCellValueFactory(new PropertyValueFactory<>("title"));
        eRoc.setCellValueFactory(new PropertyValueFactory<>("rocketName"));
        eTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        eAstn.setCellValueFactory(new PropertyValueFactory<>("astronauts"));
        eMean.setCellValueFactory(new PropertyValueFactory<>("mean"));
        eventList.addAll(list);
        eventTable.setItems(eventList);
    }

    public void initializeAstronaut(List<Astronaut> list) {
        astronautsList = FXCollections.observableArrayList();
        aID.setCellValueFactory(new PropertyValueFactory<>("id"));
        aName.setCellValueFactory(new PropertyValueFactory<>("name"));
        aAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        aSex.setCellValueFactory(new PropertyValueFactory<>("sex"));
        astronautsList.addAll(list);
        astronautTable.setItems(astronautsList);
    }

    public void initializeUser(List<User> list) {
        users = FXCollections.observableArrayList();
        uid.setCellValueFactory(new PropertyValueFactory<>("id"));
        uName.setCellValueFactory(new PropertyValueFactory<>("name"));
        uPswd.setCellValueFactory(new PropertyValueFactory<>("password"));
//        uIsAdmin.setCellValueFactory(new PropertyValueFactory<>("isAdmin"));
        users.addAll(list);
        userTable.setItems(users);
    }

    public void initData() {
        try {
            initializeRocket(rocketDao.getAll());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            initializeEvent(eventDao.getAll());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            initializeAstronaut(astronautDao.getAll());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (isRoot) {
            try {
                initializeUser(userDao.getAll());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void refreshData() {
        initData();
        new Alert(Alert.AlertType.INFORMATION, "刷新成功").showAndWait();
    }

    public void searchByID() {
        FlowPane pane = new FlowPane();
        pane.setPadding(new Insets(11, 12, 13, 14));
        pane.setVgap(5);
        pane.setHgap(5);
        Label label = new Label("查询对象：");
        MenuItem item1 = new MenuItem("事件");
        MenuItem item2 = new MenuItem("火箭");
        MenuItem item3 = new MenuItem("宇航员");
        MenuItem item4 = new MenuItem("用户");
        item4.setVisible(isRoot);
        MenuButton menuButton = new MenuButton("请选择", null, item1, item2, item3, item4);
        TextField textField = new TextField();
        Button button = new Button("查询");
        textField.setPromptText("请输入查询ID");
        textField.setPrefColumnCount(2);
        textField.setPrefWidth(100.0);
        pane.getChildren().addAll(label, menuButton, textField, button);

        Stage stage = new Stage();
        stage.setTitle("ID查询");
        stage.setScene(new Scene(pane, 180, 90));
        stage.show();

        item1.setOnAction(e -> menuButton.setText(item1.getText()));

        item2.setOnAction(e -> menuButton.setText(item2.getText()));

        item3.setOnAction(e -> menuButton.setText(item3.getText()));

        item4.setOnAction(e -> menuButton.setText(item4.getText()));

        button.setOnAction(e -> {
            var text = menuButton.getText();
            switch (text) {
                case "事件":
                    try {
                        initializeEvent(eventDao.getAll(Integer.parseInt(textField.getText())));
                    } catch (Exception ex) {
                        new Alert(Alert.AlertType.ERROR, "查询失败").showAndWait();
                        throw new RuntimeException(ex);
                    }
                    new Alert(Alert.AlertType.INFORMATION, "查询成功，请前往列表详情查看").showAndWait();
                    break;
                case "火箭":
                    try {
                        initializeRocket(rocketDao.getAll(Integer.parseInt(textField.getText())));
                    } catch (Exception ex) {
                        new Alert(Alert.AlertType.ERROR, "查询失败").showAndWait();
                        throw new RuntimeException(ex);
                    }
                    new Alert(Alert.AlertType.INFORMATION, "查询成功，请前往列表详情查看").showAndWait();
                    break;
                case "宇航员":
                    try {
                        initializeAstronaut(astronautDao.getAll(Integer.parseInt(textField.getText())));
                    } catch (Exception ex) {
                        new Alert(Alert.AlertType.ERROR, "查询失败").showAndWait();
                        throw new RuntimeException(ex);
                    }
                    new Alert(Alert.AlertType.INFORMATION, "查询成功，请前往列表详情查看").showAndWait();
                    break;
                case "用户":
                    try {
                        initializeUser(userDao.getAll(Integer.parseInt(textField.getText())));
                    } catch (Exception ex) {
                        new Alert(Alert.AlertType.ERROR, "查询失败").showAndWait();
                        throw new RuntimeException(ex);
                    }
                    new Alert(Alert.AlertType.INFORMATION, "查询成功，请前往列表详情查看").showAndWait();
                    break;
                default:
                    new Alert(Alert.AlertType.ERROR, "请选择查询对象").showAndWait();
                    break;
            }
        });
    }

    public void searchByName() {
        FlowPane pane = new FlowPane();
        pane.setPadding(new Insets(11, 12, 13, 14));
        pane.setVgap(5);
        pane.setHgap(5);
        Label label = new Label("查询对象：");
        MenuItem item1 = new MenuItem("事件");
        MenuItem item2 = new MenuItem("火箭");
        MenuItem item3 = new MenuItem("宇航员");
        MenuItem item4 = new MenuItem("用户");
        item4.setVisible(isRoot);
        MenuButton menuButton = new MenuButton("请选择", null, item1, item2, item3, item4);
        TextField textField = new TextField();
        Button button = new Button("查询");
        textField.setPromptText("请输入查询内容");
        textField.setPrefColumnCount(2);
        textField.setPrefWidth(100.0);
        pane.getChildren().addAll(label, menuButton, textField, button);

        Stage stage = new Stage();
        stage.setTitle("内容查询");
        stage.setScene(new Scene(pane, 180, 90));
        stage.show();

        item1.setOnAction(e -> menuButton.setText(item1.getText()));

        item2.setOnAction(e -> menuButton.setText(item2.getText()));

        item3.setOnAction(e -> menuButton.setText(item3.getText()));

        item4.setOnAction(e -> menuButton.setText(item4.getText()));

        button.setOnAction(e -> {
            var text = menuButton.getText();
            switch (text) {
                case "事件":
                    try (EventDaoImpl eventDao = new EventDaoImpl()) {
                        initializeEvent(eventDao.getAll(textField.getText()));
                    } catch (Exception ex) {
                        new Alert(Alert.AlertType.ERROR, "查询失败").showAndWait();
                        throw new RuntimeException(ex);
                    }
                    stage.close();
                    new Alert(Alert.AlertType.INFORMATION, "查询成功，请前往列表详情查看").showAndWait();
                    break;
                case "火箭":
                    try (RocketDaoImpl rocketDao = new RocketDaoImpl("rocket",
                            "rocketID",
                            "rocketName",
                            "launchDate",
                            "in_orbitTime")) {
                        try {
                            initializeRocket(rocketDao.getAll(textField.getText()));
                        } catch (Exception ex) {
                            new Alert(Alert.AlertType.ERROR, "查询失败").showAndWait();
                            throw new RuntimeException(ex);
                        }
                    }
                    stage.close();
                    new Alert(Alert.AlertType.INFORMATION, "查询成功，请前往列表详情查看").showAndWait();
                    break;
                case "宇航员":
                    try (AstronautDaoImpl astronautDao = new AstronautDaoImpl()) {
                        initializeAstronaut(astronautDao.getAll(textField.getText()));
                    } catch (Exception ex) {
                        new Alert(Alert.AlertType.ERROR, "查询失败").showAndWait();
                        throw new RuntimeException(ex);
                    }
                    new Alert(Alert.AlertType.INFORMATION, "查询成功，请前往列表详情查看").showAndWait();
                    break;
                case "用户":
                    try (UserDaoImpl userDao = new UserDaoImpl("user",
                            "uid",
                            "name",
                            "pswd_sha",
                            "is_admin")) {
                        initializeUser(userDao.getAll(textField.getText()));
                    } catch (Exception ex) {
                        new Alert(Alert.AlertType.ERROR, "查询失败").showAndWait();
                        throw new RuntimeException(ex);
                    }
                    stage.close();
                    new Alert(Alert.AlertType.INFORMATION, "查询成功，请前往列表详情查看").showAndWait();
                    break;
                default:
                    new Alert(Alert.AlertType.ERROR, "请选择查询对象").showAndWait();
                    break;
            }
        });
    }

    public void addData() {
//        Tab tab = tabPane.getSelectionModel().getSelectedItem();
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefHeight(385.0);
        anchorPane.setPrefWidth(337.0);
        TextField textField1 = new TextField();
        textField1.setLayoutX(69.0);
        textField1.setLayoutY(86.0);
        textField1.setPrefHeight(26.0);
        textField1.setPrefWidth(201.0);
        TextField textField2 = new TextField();
        textField2.setLayoutX(69.0);
        textField2.setLayoutY(138.0);
        textField2.setPrefHeight(26.0);
        textField2.setPrefWidth(201.0);
        TextField textField3 = new TextField();
        textField3.setLayoutX(69.0);
        textField3.setLayoutY(191.0);
        textField3.setPrefHeight(26.0);
        textField3.setPrefWidth(201.0);
        TextField textField4 = new TextField();
        textField4.setLayoutX(69.0);
        textField4.setLayoutY(243.0);
        textField4.setPrefHeight(26.0);
        textField4.setPrefWidth(201.0);
        TextField textField5 = new TextField();
        textField5.setLayoutX(69.0);
        textField5.setLayoutY(295.0);
        textField5.setPrefHeight(26.0);
        textField5.setPrefWidth(201.0);
        MenuItem item1 = new MenuItem("事件");
        MenuItem item2 = new MenuItem("火箭");
        MenuItem item3 = new MenuItem("宇航员");
        MenuItem item4 = new MenuItem("用户");
        item4.setVisible(isRoot);
        MenuButton menuButton = new MenuButton("请选择", null, item1, item2, item3, item4);
        menuButton.setLayoutX(196.0);
        menuButton.setLayoutY(35.0);
        menuButton.setMnemonicParsing(false);
        textField4.setVisible(menuButton.getText().equals(item1.getText()));
        textField5.setVisible(menuButton.getText().equals(item1.getText()));
        Label label1 = new Label("请选择添加类型：");
        label1.setLayoutX(61.0);
        label1.setLayoutY(35.0);
        label1.setPrefHeight(20.0);
        label1.setPrefWidth(109.0);
        Label label2 = new Label();
        label2.setLayoutX(68.0);
        label2.setLayoutY(61.0);
        label2.setPrefHeight(20.0);
        label2.setPrefWidth(100.0);
        Label label3 = new Label();
        label3.setLayoutX(68.0);
        label3.setLayoutY(116.0);
        label3.setPrefHeight(20.0);
        label3.setPrefWidth(100.0);
        Label label4 = new Label();
        label4.setLayoutX(68.0);
        label4.setLayoutY(170.0);
        label4.setPrefHeight(20.0);
        label4.setPrefWidth(100.0);
        Label label5 = new Label();
        label5.setLayoutX(68.0);
        label5.setLayoutY(224.0);
        label5.setPrefHeight(20.0);
        label5.setPrefWidth(100.0);
        Label label6 = new Label();
        label6.setLayoutX(68.0);
        label6.setLayoutY(278.0);
        label6.setPrefHeight(20.0);
        label6.setPrefWidth(100.0);
        label6.setVisible(menuButton.getText().equals(item1.getText()));
        Button button = new Button("添加");
        button.setLayoutX(140.0);
        button.setLayoutY(333.0);
        button.setMnemonicParsing(false);
        button.setPrefHeight(32.0);
        button.setPrefWidth(57.0);
        button.setStyle("-fx-font-family: 'Songti SC'; -fx-font-size: 13.0");
        anchorPane.getChildren().addAll(textField1, textField2, textField3, textField4, textField5, menuButton, label1, label2, label3, label4, label5, label6, button);

        Scene scene = new Scene(anchorPane, 337, 385);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("添加数据");
        stage.setScene(scene);
        stage.show();

        item1.setOnAction(event -> {
            menuButton.setText(item1.getText());
            textField4.setVisible(menuButton.getText().equals(item1.getText()));
            textField5.setVisible(menuButton.getText().equals(item1.getText()));
            label5.setVisible(menuButton.getText().equals(item1.getText()));
            label6.setVisible(menuButton.getText().equals(item1.getText()));
            label2.setText("事件描述：");
            textField1.setPromptText("请填写事件描述");
            label3.setText("相关火箭：");
            textField2.setPromptText("请填写事件相关火箭，以空格分隔，若没有填写“无”");
            label4.setText("参与航天员：");
            textField3.setPromptText("请填写参与航天员，以空格分隔，若没有填写“无”");
            label5.setText("发生时间：");
            textField4.setPromptText("请填写事件发生时间");
            label6.setText("事件意义：");
            textField5.setPromptText("请填写事件意义");
        });

        item2.setOnAction(event -> {
            menuButton.setText(item2.getText());
            textField4.setVisible(menuButton.getText().equals(item1.getText()));
            textField5.setVisible(menuButton.getText().equals(item1.getText()));
            label5.setVisible(menuButton.getText().equals(item1.getText()));
            label6.setVisible(menuButton.getText().equals(item1.getText()));
            label2.setText("火箭名称：");
            textField1.setPromptText("请填写火箭名称");
            label3.setText("发射时间：");
            textField2.setPromptText("请填写火箭发射时间，如未发射请填写“未发射”");
            label4.setText("在轨时间（天）：");
            textField3.setPromptText("请填写火箭在轨时间，未知填写-1");
        });

        item3.setOnAction(event -> {
            menuButton.setText(item3.getText());
            textField4.setVisible(menuButton.getText().equals(item1.getText()));
            textField5.setVisible(menuButton.getText().equals(item1.getText()));
            label5.setVisible(menuButton.getText().equals(item1.getText()));
            label6.setVisible(menuButton.getText().equals(item1.getText()));
            label2.setText("航天员姓名：");
            textField1.setPromptText("请填写航天员姓名");
            label3.setText("航天员年龄：");
            textField2.setPromptText("请填写航天员年龄");
            label4.setText("航天员性别：");
            textField3.setPromptText("请填写航天员性别");
        });

        item4.setOnAction(event -> {
            menuButton.setText(item4.getText());
            textField4.setVisible(menuButton.getText().equals(item1.getText()));
            textField5.setVisible(menuButton.getText().equals(item1.getText()));
            label5.setVisible(menuButton.getText().equals(item1.getText()));
            label6.setVisible(menuButton.getText().equals(item1.getText()));
            label2.setText("用户名：");
            textField1.setPromptText("请填写用户名");
            label3.setText("密码：");
            textField2.setPromptText("请填写新增用户的密码");
            label4.setText("是否为管理员：");
            textField3.setPromptText("请用数字0，1表示是否为管理员，0表示不是，1表示是");
        });

        button.setOnAction(e -> {
            var text = menuButton.getText();
            switch (text) {
                case "事件":
                    try (EventDaoImpl eventDao = new EventDaoImpl()) {
                        if (eventDao.add(new Event(textField1.getText(), textField2.getText(), textField3.getText(), textField4.getText(), textField5.getText()))) {
                            new Alert(Alert.AlertType.INFORMATION, "添加成功").showAndWait();
                            initData();
                            stage.close();
                        } else {
                            new Alert(Alert.AlertType.ERROR, "添加失败").showAndWait();
                        }
                    } catch (Exception ex) {
                        new Alert(Alert.AlertType.ERROR, "添加失败").showAndWait();
                        throw new RuntimeException(ex);
                    }
                    break;
                case "火箭":
                    try {
                        Rocket rocket = new Rocket(textField1.getText(), textField2.getText(), Integer.parseInt(textField3.getText()));
                        if (rocketDao.add(rocket)) {
                            new Alert(Alert.AlertType.INFORMATION, "添加成功").showAndWait();
                            initData();
                            stage.close();
                        } else {
                            new Alert(Alert.AlertType.ERROR, "添加失败").showAndWait();
                        }
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                case "宇航员":
                    try {
                        if (astronautDao.add(new Astronaut(textField1.getText(), Integer.parseInt(textField2.getText()), textField3.getText()))) {
                            new Alert(Alert.AlertType.INFORMATION, "添加成功").showAndWait();
                            initData();
                            stage.close();
                        } else {
                            new Alert(Alert.AlertType.ERROR, "添加失败").showAndWait();
                        }
                    } catch (Exception ex) {
                        new Alert(Alert.AlertType.ERROR, "添加失败").showAndWait();
                        throw new RuntimeException(ex);
                    }
                    break;
                case "用户":
                    try {
                        if (userDao.add(new User(textField1.getText(), textField2.getText(), Integer.parseInt(textField3.getText()) == 1))) {
                            new Alert(Alert.AlertType.INFORMATION, "添加成功").showAndWait();
                            initData();
                            stage.close();
                        } else {
                            new Alert(Alert.AlertType.ERROR, "添加失败").showAndWait();
                        }
                    } catch (Exception ex) {
                        new Alert(Alert.AlertType.ERROR, "添加失败").showAndWait();
                        throw new RuntimeException(ex);
                    }
                    break;
                default:
                    new Alert(Alert.AlertType.ERROR, "请选择添加类型").showAndWait();
                    break;
            }
        });
    }

    public void delData() {
        Tab tab = tabPane.getSelectionModel().getSelectedItem();
        switch (tab.getText()) {
            case "事件表" -> {
                Event event = eventTable.getSelectionModel().getSelectedItem();
                if (event != null) {
                    if (new Alert(Alert.AlertType.CONFIRMATION, "确定要删除该条记录吗？").showAndWait().get() != ButtonType.OK) {
                        return;
                    }
                    try {
                        if (eventDao.delete(event.getId())) {
                            new Alert(Alert.AlertType.INFORMATION, "删除成功").showAndWait();
                            initData();
                        } else {
                            new Alert(Alert.AlertType.ERROR, "删除失败").showAndWait();
                        }
                    } catch (Exception ex) {
                        new Alert(Alert.AlertType.ERROR, "删除失败").showAndWait();
                        throw new RuntimeException(ex);
                    }
                }
            }
            case "火箭表" -> {
                Rocket rocket = rocketTable.getSelectionModel().getSelectedItem();
                if (rocket != null) {
                    if (new Alert(Alert.AlertType.CONFIRMATION, "确定要删除该条记录吗？").showAndWait().get() != ButtonType.OK) {
                        return;
                    }
                    try (RocketDaoImpl rocketDao = new RocketDaoImpl("rocket",
                            "rocketID",
                            "rocketName",
                            "launchDate",
                            "in_orbitTime")) {
                        if (rocketDao.delete(rocket.getRocketID())) {
                            new Alert(Alert.AlertType.INFORMATION, "删除成功").showAndWait();
                            initData();
                        } else {
                            new Alert(Alert.AlertType.ERROR, "删除失败").showAndWait();
                        }
                    } catch (Exception ex) {
                        new Alert(Alert.AlertType.ERROR, "删除失败").showAndWait();
                        throw new RuntimeException(ex);
                    }
                }
            }
            case "宇航员表" -> {
                Astronaut astronaut = astronautTable.getSelectionModel().getSelectedItem();
                if (astronaut != null) {
                    if (new Alert(Alert.AlertType.CONFIRMATION, "确定要删除该条记录吗？").showAndWait().get() != ButtonType.OK) {
                        return;
                    }
                    try (AstronautDaoImpl astronautDao = new AstronautDaoImpl()) {
                        if (astronautDao.delete(astronaut.getId())) {
                            new Alert(Alert.AlertType.INFORMATION, "删除成功").showAndWait();
                            initData();
                        } else {
                            new Alert(Alert.AlertType.ERROR, "删除失败").showAndWait();
                        }
                    } catch (Exception ex) {
                        new Alert(Alert.AlertType.ERROR, "删除失败").showAndWait();
                        throw new RuntimeException(ex);
                    }
                }
            }
            case "用户表" -> {
                User user = userTable.getSelectionModel().getSelectedItem();
                if (user != null) {
                    if (new Alert(Alert.AlertType.CONFIRMATION, "确定要删除该条记录吗？").showAndWait().get() != ButtonType.OK) {
                        return;
                    }
                    try (UserDaoImpl userDao = new UserDaoImpl("user",
                            "uid",
                            "name",
                            "pswd_sha",
                            "is_admin")) {
                        if (userDao.delete(user.getId())) {
                            new Alert(Alert.AlertType.INFORMATION, "删除成功").showAndWait();
                            initData();
                        } else {
                            new Alert(Alert.AlertType.ERROR, "删除失败").showAndWait();
                        }
                    } catch (Exception ex) {
                        new Alert(Alert.AlertType.ERROR, "删除失败").showAndWait();
                        throw new RuntimeException(ex);
                    }
                }
            }
        }
    }

    public void corData() {
        Tab tab = tabPane.getSelectionModel().getSelectedItem();
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefHeight(385.0);
        anchorPane.setPrefWidth(337.0);
        TextField textField1 = new TextField();
        textField1.setLayoutX(69.0);
        textField1.setLayoutY(86.0);
        textField1.setPrefHeight(26.0);
        textField1.setPrefWidth(201.0);
        TextField textField2 = new TextField();
        textField2.setLayoutX(69.0);
        textField2.setLayoutY(138.0);
        textField2.setPrefHeight(26.0);
        textField2.setPrefWidth(201.0);
        TextField textField3 = new TextField();
        textField3.setLayoutX(69.0);
        textField3.setLayoutY(191.0);
        textField3.setPrefHeight(26.0);
        textField3.setPrefWidth(201.0);
        TextField textField4 = new TextField();
        textField4.setLayoutX(69.0);
        textField4.setLayoutY(243.0);
        textField4.setPrefHeight(26.0);
        textField4.setPrefWidth(201.0);
        TextField textField5 = new TextField();
        textField5.setLayoutX(69.0);
        textField5.setLayoutY(295.0);
        textField5.setPrefHeight(26.0);
        textField5.setPrefWidth(201.0);
        textField4.setVisible(false);
        textField5.setVisible(false);
        Label label2 = new Label();
        label2.setLayoutX(68.0);
        label2.setLayoutY(61.0);
        label2.setPrefHeight(20.0);
        label2.setPrefWidth(100.0);
        Label label3 = new Label();
        label3.setLayoutX(68.0);
        label3.setLayoutY(116.0);
        label3.setPrefHeight(20.0);
        label3.setPrefWidth(100.0);
        Label label4 = new Label();
        label4.setLayoutX(68.0);
        label4.setLayoutY(170.0);
        label4.setPrefHeight(20.0);
        label4.setPrefWidth(100.0);
        Label label5 = new Label();
        label5.setLayoutX(68.0);
        label5.setLayoutY(224.0);
        label5.setPrefHeight(20.0);
        label5.setPrefWidth(100.0);
        Label label6 = new Label();
        label6.setLayoutX(68.0);
        label6.setLayoutY(278.0);
        label6.setPrefHeight(20.0);
        label6.setPrefWidth(100.0);
        label6.setVisible(false);
        Button button = new Button("修改");
        button.setLayoutX(140.0);
        button.setLayoutY(333.0);
        button.setMnemonicParsing(false);
        button.setPrefHeight(32.0);
        button.setPrefWidth(57.0);
        button.setStyle("-fx-font-family: 'Songti SC'; -fx-font-size: 13.0");
        anchorPane.getChildren().addAll(textField1, textField2, textField3, textField4, textField5, label2, label3, label4, label5, label6, button);

        Scene scene = new Scene(anchorPane, 337, 385);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("修改数据");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        switch (tab.getText()) {
            case "事件表" -> {
                Event event = eventTable.getSelectionModel().getSelectedItem();
                if (event == null) {
                    new Alert(Alert.AlertType.ERROR, "请选择事件").showAndWait();
                    return;
                }
                textField4.setVisible(true);
                textField5.setVisible(true);
                label5.setVisible(true);
                label6.setVisible(true);
                label2.setText("事件描述：");
                textField1.setPromptText("请填写事件描述");
                textField1.setText(event.getTitle());
                label3.setText("相关火箭：");
                textField2.setPromptText("请填写事件相关火箭，以空格分隔，若没有填写“无”");
                textField2.setText(event.getRocketName());
                label4.setText("参与航天员：");
                textField3.setPromptText("请填写参与航天员，以空格分隔，若没有填写“无”");
                textField3.setText(event.getAstronauts());
                label5.setText("发生时间：");
                textField4.setPromptText("请填写事件发生时间");
                textField4.setText(event.getTime());
                label6.setText("事件意义：");
                textField5.setPromptText("请填写事件意义");
                textField5.setText(event.getMean());
            }
            case "火箭表" -> {
                Rocket rocket = rocketTable.getSelectionModel().getSelectedItem();
                if (rocket == null) {
                    new Alert(Alert.AlertType.ERROR, "请选择火箭").showAndWait();
                    return;
                }
                textField4.setVisible(false);
                textField5.setVisible(false);
                label5.setVisible(false);
                label6.setVisible(false);
                label2.setText("火箭名称：");
                textField1.setPromptText("请填写火箭名称");
                textField1.setText(rocket.getRocketName());
                label3.setText("发射时间：");
                textField2.setPromptText("请填写火箭发射时间，如未发射请填写“未发射”");
                textField2.setText(rocket.getLaunchDate());
                label4.setText("在轨时间（天）：");
                textField3.setPromptText("请填写火箭在轨时间，未知填写-1");
                textField3.setText(String.valueOf(rocket.getInOrbitTime()));
            }
            case "宇航员表" -> {
                Astronaut astronaut = astronautTable.getSelectionModel().getSelectedItem();
                if (astronaut == null) {
                    new Alert(Alert.AlertType.ERROR, "请选择航天员").showAndWait();
                    return;
                }
                textField4.setVisible(false);
                textField5.setVisible(false);
                label5.setVisible(false);
                label6.setVisible(false);
                label2.setText("航天员姓名：");
                textField1.setPromptText("请填写航天员姓名");
                textField1.setText(astronaut.getName());
                label3.setText("航天员年龄：");
                textField2.setPromptText("请填写航天员年龄");
                textField2.setText(String.valueOf(astronaut.getAge()));
                label4.setText("航天员性别：");
                textField3.setPromptText("请填写航天员性别");
                textField3.setText(astronaut.getSex());
            }
            case "用户表" -> {
                User user = userTable.getSelectionModel().getSelectedItem();
                textField4.setVisible(false);
                textField5.setVisible(false);
                label5.setVisible(false);
                label6.setVisible(false);
                label2.setText("用户名：");
                textField1.setPromptText("请填写用户名");
                textField1.setText(user.getName());
                label3.setText("密码：");
                textField2.setPromptText("请填写新增用户的密码");
                textField2.setText(user.getPassword());
                textField2.setEditable(false);
                label4.setText("是否为管理员：");
                textField3.setPromptText("请用数字0，1表示是否为管理员，0表示不是，1表示是");
                textField3.setText(String.valueOf(user.isAdmin()));
            }
        }
        button.setOnAction(e -> {
            switch (tab.getText()) {
                case "事件表":
                    try {
                        if (eventDao.update(new Event(textField1.getText(), textField2.getText(), textField3.getText(), textField4.getText(), textField5.getText()))) {
                            new Alert(Alert.AlertType.INFORMATION, "修改成功").showAndWait();
                            initData();
                            stage.close();
                        } else {
                            new Alert(Alert.AlertType.ERROR, "修改失败").showAndWait();
                        }
                    } catch (Exception ex) {
                        new Alert(Alert.AlertType.ERROR, "修改失败").showAndWait();
                        throw new RuntimeException(ex);
                    }
                    break;
                case "火箭表":
                    int rocketID = rocketTable.getSelectionModel().getSelectedItem().getRocketID();
                    try {
                        if (rocketDao.update(new Rocket(rocketID, textField1.getText(), textField2.getText(), Integer.parseInt(textField3.getText())))) {
                            new Alert(Alert.AlertType.INFORMATION, "修改成功").showAndWait();
                            initData();
                            stage.close();
                        } else {
                            new Alert(Alert.AlertType.ERROR, "修改失败").showAndWait();
                        }
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                case "宇航员表":
                    int id = astronautTable.getSelectionModel().getSelectedItem().getId();
                    try {
                        if (astronautDao.update(new Astronaut(id, textField1.getText(), Integer.parseInt(textField2.getText()), textField3.getText()))) {
                            new Alert(Alert.AlertType.INFORMATION, "修改成功").showAndWait();
                            initData();
                            stage.close();
                        } else {
                            new Alert(Alert.AlertType.ERROR, "修改失败").showAndWait();
                        }
                    } catch (Exception ex) {
                        new Alert(Alert.AlertType.ERROR, "修改失败").showAndWait();
                        throw new RuntimeException(ex);
                    }
                    break;
                case "用户表":
                    try {
                        if (userDao.update(new User(textField1.getText(), textField2.getText(), Integer.parseInt(textField3.getText()) == 1))) {
                            new Alert(Alert.AlertType.INFORMATION, "修改成功").showAndWait();
                            initData();
                            stage.close();
                        } else {
                            new Alert(Alert.AlertType.ERROR, "修改失败").showAndWait();
                        }
                    } catch (Exception ex) {
                        new Alert(Alert.AlertType.ERROR, "修改失败").showAndWait();
                        throw new RuntimeException(ex);
                    }
                    break;
                default:
                    new Alert(Alert.AlertType.ERROR, "请选择修改类型").showAndWait();
                    break;
            }
        });
    }
}

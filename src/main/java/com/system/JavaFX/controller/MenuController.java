package com.system.JavaFX.controller;

import com.system.DAO.polo.Astronaut;
import com.system.DAO.polo.Rocket;
import com.system.Service.RocketDaoImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.util.Optional;

import com.system.DAO.polo.Event;

import static com.system.JavaFX.view.ProjectApplication.isAdmin;

public class MenuController {
    private boolean isHistory = false;
    private boolean isPaused = false;
    private boolean isList = false;
    @FXML
    public Menu adminButton;
    @FXML
    public ObservableList<Event> eventList;
    @FXML
    public ObservableList<Rocket> rocketsList;
    @FXML
    public ObservableList<Astronaut> astronautsList = FXCollections.observableArrayList();
    @FXML
    public TableColumn eID;
    @FXML
    public TableColumn eName;
    @FXML
    public TableColumn eRoc;
    @FXML
    public TableColumn eTime;
    @FXML
    public TableColumn eAstn;
    @FXML
    public TableColumn eMean;
    @FXML
    public TableColumn rID;
    @FXML
    public TableColumn rName;
    @FXML
    public TableColumn rTime;
    @FXML
    public TableColumn rPeo;
    @FXML
    public TableColumn rOrbit;
    @FXML
    public TableColumn aName;
    @FXML
    public TableColumn aAge;
    @FXML
    public TableColumn aSex;
    @FXML
    public Pane table;
    @FXML
    public TableView<Event> eventTable;
    @FXML
    public TableView<Rocket> rocketTable;
    @FXML
    public TableView<Event> astronautTable;
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

    public void historyClick() {
        if (!isHistory) {
            isHistory = true;
            pauseButton.setVisible(true);
            historyButton.setText("返回");
            mediaView.setVisible(true);
            historyPlayer.play();
        } else {
            isHistory = false;
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
        adminButton.setVisible(isAdmin);
        if (!isList) {
            isList = true;
            initializeRocket();
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
        if (buttonType.get() == ButtonType.OK) {
            new Alert(Alert.AlertType.INFORMATION, "注销成功").showAndWait();
            rootPane.getScene().getWindow().hide();
        }
    }

    public void initializeRocket() {
        rocketsList = FXCollections.observableArrayList();
        rID.setCellValueFactory(new PropertyValueFactory<>("rocketID"));
        rName.setCellValueFactory(new PropertyValueFactory<>("rocketName"));
        rTime.setCellValueFactory(new PropertyValueFactory<>("launchDate"));
        rPeo.setCellValueFactory(new PropertyValueFactory<>("carryPeople"));
        rOrbit.setCellValueFactory(new PropertyValueFactory<>("inOrbitTime"));
        try (RocketDaoImpl rd = new RocketDaoImpl("rocket",
                "rocketID",
                "rocketName",
                "launchDate",
                "in_orbitTime");
        ) {
            rocketsList.addAll(rd.getAllRockets());
            for (var i :
                    rocketsList) {
                System.out.println(i);
            }
            rocketTable.setItems(rocketsList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void initializeEvent() {
        eventList = FXCollections.observableArrayList();
        eID.setCellValueFactory(new PropertyValueFactory<>("eventID"));
        eName.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        eRoc.setCellValueFactory(new PropertyValueFactory<>("rocketID"));
        eTime.setCellValueFactory(new PropertyValueFactory<>("eventDate"));
        eAstn.setCellValueFactory(new PropertyValueFactory<>("astronautID"));
        eMean.setCellValueFactory(new PropertyValueFactory<>("eventMean"));
    }

    public void initializeAstronaut() {

    }
}

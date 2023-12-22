package com.system.JavaFX.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class MenuController {
    private boolean isHistory = false;
    @FXML
    private Button historyButton;
    @FXML
    private MediaPlayer historyPlayer;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private MediaView mediaView;

    public void historyClick(ActionEvent actionEvent) {
        if (!isHistory) {
            isHistory = true;
            historyButton.setText("返回");
            mediaView.setVisible(true);
            historyPlayer.play();
        } else {
            isHistory = false;
            historyButton.setText("历史回顾");
            mediaView.setVisible(false);
            historyPlayer.pause();
        }
    }
}

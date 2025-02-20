package com.system.JavaFX.controller;

import com.system.DAO.entity.Astronaut;
import com.system.JavaFX.pagemodule.SceneLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PhotoController {
    private final String imageBasePath = "/image/%s";
    @FXML
    public ImageView imageView;
    @FXML
    public Label label;

    public void initialize() {
        Astronaut selectedAstronaut = (Astronaut) SceneLoader.object;
        String astronautName = selectedAstronaut.getName();
        String astronautPhotoUrl = selectedAstronaut.getPhotoUrl();
        String url = String.valueOf(PhotoController.class.getResource(String.format(imageBasePath, astronautPhotoUrl)));
        Image image = new Image(url);
        imageView.imageProperty().set(image);
        label.setText(String.format("宇航员 %s 的照片展示：", astronautName));
    }
}

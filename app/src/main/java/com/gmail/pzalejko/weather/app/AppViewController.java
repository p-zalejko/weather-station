package com.gmail.pzalejko.weather.app;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class AppViewController {

    @FXML
    private Label title;

    @FXML
    private HBox sensorBox;

    // called by the FXML loader.
    public void initialize() {
        title.setText("Hello for the FXML controller!");
        AppUiInitializer.init(sensorBox);
    }
}

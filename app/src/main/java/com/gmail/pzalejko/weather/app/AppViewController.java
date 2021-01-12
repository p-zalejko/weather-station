package com.gmail.pzalejko.weather.app;

import eu.hansolo.tilesfx.Tile;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;

public class AppViewController {

    @FXML
    private Label title;

    @FXML
    private HBox sensorBox;

    // called by the FXML loader.
    public void initialize() {
        title.setText("Hello for the FXML controller!");

        sensorBox.setPadding(new Insets(5));
        sensorBox.setBackground(new Background(new BackgroundFill(Tile.BACKGROUND.darker(), CornerRadii.EMPTY, Insets.EMPTY)));

        AppUiInitializer.init(sensorBox);
    }
}

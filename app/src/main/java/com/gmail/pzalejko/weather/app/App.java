package com.gmail.pzalejko.weather.app;

import eu.hansolo.tilesfx.Tile;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class App extends Application {

    private AppSensorView appSensorView;

    @Override
    public void init() {
        appSensorView = new AppSensorView();
        appSensorView.init();
    }

    @Override
    public void start(Stage stage) {
        Node[] nodes = appSensorView.getNodes();
        HBox pane = new HBox();
        pane.setPadding(new Insets(5));
        pane.setBackground(new Background(new BackgroundFill(Tile.BACKGROUND.darker(), CornerRadii.EMPTY, Insets.EMPTY)));
        pane.getChildren().addAll(nodes);

        Scene scene = new Scene(pane);
        stage.setTitle("Temperature");
        stage.setScene(scene);
        stage.setMinHeight(200);
        stage.setMinWidth(300);
        stage.show();

        appSensorView.startProcessing();
    }

    @Override
    public void stop() {
        System.exit(0);
    }

    public static void main(String[] args) {
        launch(args);
    }


}
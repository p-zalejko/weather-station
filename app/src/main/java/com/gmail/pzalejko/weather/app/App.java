package com.gmail.pzalejko.weather.app;

import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.tools.FlowGridPane;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
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
        FlowGridPane pane = new FlowGridPane(3, 1, nodes);
        pane.setHgap(5);
        pane.setVgap(5);
        pane.setPadding(new Insets(5));
        pane.setBackground(new Background(new BackgroundFill(Tile.BACKGROUND.darker(), CornerRadii.EMPTY, Insets.EMPTY)));

        Scene scene = new Scene(pane);
        stage.setTitle("TilesFX Dashboard using Medusa and Regulators");
        stage.setScene(scene);
        stage.setHeight(400);
        stage.setWidth(300);
        stage.show();
    }

    @Override
    public void stop() {
        System.exit(0);
    }

    public static void main(String[] args) {
        launch(args);
    }


}
package com.gmail.pzalejko.weather.app;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

class AppUiInitializer {

    static void init(Pane box) {
        List<Node> widgets = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            widgets.add(new Button(Integer.toString(i)));
        }
        box.getChildren().addAll(widgets);
    }
}

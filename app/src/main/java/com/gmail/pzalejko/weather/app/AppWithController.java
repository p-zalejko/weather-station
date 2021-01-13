package com.gmail.pzalejko.weather.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;

public class AppWithController extends Application {

    @Override
    public void start(Stage stage) {
        try {
            URL location = getClass().getResource("hello-with-controller.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(location);
            fxmlLoader.setClassLoader(getClass().getClassLoader());
            VBox box = fxmlLoader.load();

            Scene scene = new Scene(box);
            stage.setTitle("Hello");
            stage.setScene(scene);
            stage.setMinHeight(200);
            stage.setMinWidth(300);
            stage.show();

        } catch (Throwable e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void stop() {
        System.exit(0);
    }

    public static void main(String[] args) {
        Application.launch(AppWithController.class, args);
    }


}
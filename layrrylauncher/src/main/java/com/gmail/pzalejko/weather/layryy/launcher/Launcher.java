package com.gmail.pzalejko.weather.layryy.launcher;

import org.moditect.layrry.Layers;

public class Launcher {

    public static void main(String[] args) {
        Layers layers = getLayersWithJavaFX();
        layers.run("pzalejko.weatherstation/com.gmail.pzalejko.weather.app.App");
    }

    private static Layers getLayersWithJavaFX() {
        return Layers.builder()
                .layer("javafxDeps")
                .withModule("org.openjfx:javafx-base:jar:linux:11")
                .withModule("org.openjfx:javafx-controls:jar:linux:11")
                .withModule("org.openjfx:javafx-graphics:jar:linux:11")
                .withModule("org.openjfx:javafx-web:jar:linux:11")
                .withModule("org.openjfx:javafx-media:jar:linux:11")
                .withModule("org.openjfx:javafx-fxml:jar:linux:11")
                .layer("app")
                .withParent("javafxDeps")
                .withModule("com.gmail.pzalejko.weather:app:1.0-SNAPSHOT")
                .build();
    }

    /**
     * If you have JDE that contains JavaFX then JavaFX modules does not have to be included in the configuration.
     * <p>
     * You can download JDK+JavaFX from this page: https://www.azul.com/downloads/zulu-community/?version=java-15-mts&package=jdk-fx
     */
    private static Layers getLayersWithoutJavaFX() {
        return Layers.builder()
                .layer("app")
                .withModule("com.gmail.pzalejko.weather:app:1.0-SNAPSHOT")
                .build();
    }
}

package com.gmail.pzalejko.weather.layryy.launcher;

import org.moditect.layrry.Layers;

public class Launcher {

    public static void main(String[] args) {
        Layers layers = Layers.builder()
                .layer("javafxDeps")
                .withModule("eu.hansolo:Medusa:11.5")
                .withModule("eu.hansolo:tilesfx:11.47")
                .layer("app")
                .withParent("javafxDeps")
                .withModule("com.gmail.pzalejko.weather:app:1.0-SNAPSHOT")
                .build();
        layers.run("pzalejko.weatherstation/com.gmail.pzalejko.weather.app.App");
    }
}

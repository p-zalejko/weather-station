package com.gmail.pzalejko.weather.layryy.launcher;

import org.moditect.layrry.Layers;

public class Launcher {

    public static void main(String[] args) {
        Layers layers = Layers.builder()
                .layer("javafxDeps")
                .withModule("eu.hansolo:Medusa:11.5")
                .withModule("eu.hansolo:tilesfx:11.47")
                .layer("hardwareDeps")
                .withModule("com.pi4j:pi4j-core:1.2")
                .withModule("com.pi4j:pi4j-device:1.2")
                .withModule("com.pi4j:pi4j-gpio-extension:1.2")
                .withModule("com.gmail.pzalejko.weather:core:1.0-SNAPSHOT")
                .layer("app")
                .withParent("javafxDeps")
                .withParent("hardwareDeps")
                .withModule("com.gmail.pzalejko.weather:app:1.0-SNAPSHOT")
                .build();
        layers.run("pzalejko.weatherstation/com.gmail.pzalejko.weather.app.App");
    }
}

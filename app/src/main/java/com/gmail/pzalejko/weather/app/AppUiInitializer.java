package com.gmail.pzalejko.weather.app;

import eu.hansolo.medusa.Gauge;
import eu.hansolo.medusa.GaugeBuilder;
import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class AppUiInitializer {

    static void init(Pane box) {
        List<Node> widgets = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            var digitalGauge = createGauge();
            digitalGauge.setValue(new Random().nextDouble() * 100);

            var digitalTile = TileBuilder.create()
                    .prefSize(300, 200)
                    .skinType(Tile.SkinType.CUSTOM)
                    .title(Integer.toString(i))
                    .text("Temperature")
                    .graphic(digitalGauge)
                    .build();
            widgets.add(digitalTile);
        }
        box.getChildren().addAll(widgets);
    }


    private static Gauge createGauge() {
        return GaugeBuilder.create()
                .skinType(Gauge.SkinType.DIGITAL)
                .prefSize(300, 200)
                .animated(true)
                .unit("\u00B0C")
                .valueColor(Tile.FOREGROUND)
                .titleColor(Tile.FOREGROUND)
                .unitColor(Tile.FOREGROUND)
                .barColor(Tile.BLUE)
                .needleColor(Tile.FOREGROUND)
                .barColor(Tile.BLUE)
                .barBackgroundColor(Tile.BACKGROUND.darker())
                .tickLabelColor(Tile.FOREGROUND)
                .majorTickMarkColor(Tile.FOREGROUND)
                .minorTickMarkColor(Tile.FOREGROUND)
                .mediumTickMarkColor(Tile.FOREGROUND)
                .maxValue(50)
                .minValue(-10)
                .value(0)
                .build();
    }
}

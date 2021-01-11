package com.gmail.pzalejko.weather.app;

import com.gmail.pzalejko.weather.core.SensorServiceFactory;
import eu.hansolo.medusa.Gauge;
import eu.hansolo.medusa.GaugeBuilder;
import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;
import javafx.scene.Node;

import java.util.ArrayList;
import java.util.List;

class AppSensorView {

    private static final short CHECK_INTERVAL = 25;
    private static final short INITIAL_DELAY = 10;
    private static final double TILE_SIZE = 300;
    private List<TemperatureControlWrapper> widgets;
    private AppSensorService appSensorService;

    void init() {
        var sensorService = SensorServiceFactory.create();
        appSensorService = new AppSensorService(INITIAL_DELAY, CHECK_INTERVAL, sensorService);
        appSensorService.init();
        var sensors = appSensorService.getSensors();

        widgets = new ArrayList<>(sensors.size());
        for (var sensor : sensors) {
            var digitalGauge = createGauge();
            var digitalTile = TileBuilder.create()
                    .prefSize(TILE_SIZE, TILE_SIZE)
                    .skinType(Tile.SkinType.CUSTOM)
                    .title(sensor.getId())
                    .text("Temperature")
                    .graphic(digitalGauge)
                    .build();

            appSensorService.registerTemperatureConsumer(sensor, value -> updateTemperature(digitalGauge, value));
            widgets.add(new TemperatureControlWrapper(digitalGauge, digitalTile));
        }
    }

    void startProcessing() {
        appSensorService.startProcessing();
    }

    Node[] getNodes() {
        return widgets.stream()
                .map(TemperatureControlWrapper::getDigitalTile)
                .toArray(Node[]::new);
    }

    private void updateTemperature(Gauge digitalGauge, Double value) {
        try {
            digitalGauge.setValue(value);
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    private Gauge createGauge() {
        return GaugeBuilder.create()
                .skinType(Gauge.SkinType.DIGITAL)
                .prefSize(TILE_SIZE, TILE_SIZE)
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

    private static class TemperatureControlWrapper {
        private final Gauge digitalGauge;
        private final Tile digitalTile;

        private TemperatureControlWrapper(Gauge digitalGauge, Tile digitalTile) {
            this.digitalGauge = digitalGauge;
            this.digitalTile = digitalTile;
        }

        public Gauge getDigitalGauge() {
            return digitalGauge;
        }

        public Tile getDigitalTile() {
            return digitalTile;
        }
    }
}

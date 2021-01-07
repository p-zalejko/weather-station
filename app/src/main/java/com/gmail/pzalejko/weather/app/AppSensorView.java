package com.gmail.pzalejko.weather.app;

import com.gmail.pzalejko.weather.SensorService;
import com.gmail.pzalejko.weather.SensorServiceFactory;
import eu.hansolo.medusa.Gauge;
import eu.hansolo.medusa.GaugeBuilder;
import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;
import javafx.scene.Node;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

public class AppSensorView {
    private static final double TILE_SIZE = 300;
    private List<TemperatureControlWrapper> widgets;

    void init() {
        SensorService sensorService = SensorServiceFactory.create();
        AppSensorService appSensorService = new AppSensorService(5, sensorService);
        appSensorService.init();
        List<SensorDescriptor> sensors = appSensorService.getSensors();
        widgets = new ArrayList<>(sensors.size());
        for (SensorDescriptor sensor : sensors) {
            var digitalGauge = createGauge(Gauge.SkinType.DIGITAL);
            var digitalTile = TileBuilder.create()
                    .prefSize(TILE_SIZE, TILE_SIZE)
                    .skinType(Tile.SkinType.CUSTOM)
                    .title(sensor.getId())
                    .text("Temperature")
                    .graphic(digitalGauge)
                    .build();

            appSensorService.registerTemperatureConsumer(sensor, digitalGauge::setValue);
            widgets.add(new TemperatureControlWrapper(digitalGauge, digitalTile));
        }
    }

    Node[] getNodes() {
        return widgets.stream().map(TemperatureControlWrapper::getDigitalTile).toArray(Node[]::new);
    }

    private Gauge createGauge(final Gauge.SkinType TYPE) {
        return GaugeBuilder.create()
                .skinType(TYPE)
                .prefSize(TILE_SIZE, TILE_SIZE)
                .animated(true)
                //.title("")
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
                .build();
    }

    @RequiredArgsConstructor
    @Getter
    private static class TemperatureControlWrapper {
        private final Gauge digitalGauge;
        private final Tile digitalTile;
    }
}

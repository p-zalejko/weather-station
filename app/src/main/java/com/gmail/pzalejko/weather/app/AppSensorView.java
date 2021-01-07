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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class AppSensorView {

    private static final Logger LOG = LoggerFactory.getLogger(AppSensorView.class);

    public static final short CHECK_INTERVAL = 10;
    private static final double TILE_SIZE = 300;
    private List<TemperatureControlWrapper> widgets;
    private AppSensorService appSensorService;

    void init() {
        SensorService sensorService = SensorServiceFactory.create();
        appSensorService = new AppSensorService(CHECK_INTERVAL, sensorService);
        appSensorService.init();
        List<SensorDescriptor> sensors = appSensorService.getSensors();

        widgets = new ArrayList<>(sensors.size());
        for (SensorDescriptor sensor : sensors) {
            var digitalGauge = createGauge();
            var digitalTile = TileBuilder.create()
                    .prefSize(TILE_SIZE, TILE_SIZE)
                    .skinType(Tile.SkinType.CUSTOM)
                    .title(sensor.getId())
                    .text("Temperature")
                    .graphic(digitalGauge)
                    .build();

            appSensorService.registerTemperatureConsumer(sensor, value -> {
                try {
                    digitalGauge.setValue(value);
                } catch (Exception e) {
                    LOG.error("Could not update temperature: {}", e.getMessage(), e);
                }
            });
            widgets.add(new TemperatureControlWrapper(digitalGauge, digitalTile));
        }
    }

    void startProcessing() {
        LOG.info("Starting temperature monitoring");
        appSensorService.startProcessing();
    }

    Node[] getNodes() {
        return widgets.stream()
                .map(TemperatureControlWrapper::getDigitalTile)
                .toArray(Node[]::new);
    }

    private Gauge createGauge() {
        return GaugeBuilder.create()
                .skinType(Gauge.SkinType.DIGITAL)
                .prefSize(TILE_SIZE, TILE_SIZE)
                .animated(false)
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

    @RequiredArgsConstructor
    @Getter
    private static class TemperatureControlWrapper {
        private final Gauge digitalGauge;
        private final Tile digitalTile;
    }
}

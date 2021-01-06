package com.gmail.pzalejko.weather.app;

import eu.hansolo.medusa.Gauge;
import eu.hansolo.medusa.GaugeBuilder;
import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.Tile.SkinType;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.TimeSection;
import eu.hansolo.tilesfx.TimeSectionBuilder;
import eu.hansolo.tilesfx.tools.Country;
import eu.hansolo.tilesfx.tools.FlowGridPane;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.time.LocalTime;
import java.util.Random;


/**
 * User: hansolo
 * Date: 08.01.17
 * Time: 07:33
 */
public class Main extends Application {
    private static final double TILE_SIZE = 300;
    private static final Random RND       = new Random();

    private Tile              switchTile;
    private Gauge             digitalGauge;
    private Tile              digitalTile;
    private Gauge             simpleDigitalGauge;
    private Tile              simpleDigitalTile;
    private long              lastTimerCall;
    private AnimationTimer    timer;


    @Override public void init() {
        // WorldMap Data
        for (int i = 0; i < Country.values().length ; i++) {
            double value = RND.nextInt(10);
            Color  color;
            if (value > 8) {
                color = Tile.RED;
            } else if (value > 6) {
                color = Tile.ORANGE;
            } else if (value > 4) {
                color = Tile.YELLOW_ORANGE;
            } else if (value > 2) {
                color = Tile.GREEN;
            } else {
                color = Tile.BLUE;
            }
            Country.values()[i].setColor(color);
        }

        // TimeControl Data
        TimeSection timeSection = TimeSectionBuilder.create()
                                                    .start(LocalTime.now().plusSeconds(20))
                                                    .stop(LocalTime.now().plusHours(1))
                                                    //.days(DayOfWeek.MONDAY, DayOfWeek.FRIDAY)
                                                    .color(Tile.GRAY)
                                                    .highlightColor(Tile.RED)
                                                    .build();

        timeSection.setOnTimeSectionEntered(e -> System.out.println("Section ACTIVE"));
        timeSection.setOnTimeSectionLeft(e -> System.out.println("Section INACTIVE"));

        switchTile = TileBuilder.create()
                                .prefSize(TILE_SIZE, TILE_SIZE)
                                .skinType(SkinType.SWITCH)
                                .title("Switch Tile")
                                .text("Whatever text")
                                //.description("Test")
                                .build();

        switchTile.setOnSwitchPressed(e -> System.out.println("Switch pressed"));
        switchTile.setOnSwitchReleased(e -> System.out.println("Switch released"));

        digitalGauge = createGauge(Gauge.SkinType.DIGITAL);
        digitalTile  = TileBuilder.create()
                                  .prefSize(TILE_SIZE, TILE_SIZE)
                                  .skinType(SkinType.CUSTOM)
                                  .title("Medusa Digital")
                                  .text("Temperature")
                                  .graphic(digitalGauge)
                                  .build();

        simpleDigitalGauge = createGauge(Gauge.SkinType.SIMPLE_DIGITAL);
        simpleDigitalTile  = TileBuilder.create()
                                        .prefSize(TILE_SIZE, TILE_SIZE)
                                        .skinType(SkinType.CUSTOM)
                                        .title("Medusa SimpleDigital")
                                        .text("Temperature")
                                        .graphic(simpleDigitalGauge)
                                        .build();

        lastTimerCall = System.nanoTime();
        timer = new AnimationTimer() {
            @Override public void handle(final long now) {
                if (now > lastTimerCall + 2_000_000_000) {
                    digitalGauge.setValue(RND.nextDouble() * 100 * -1);
                    simpleDigitalGauge.setValue(RND.nextDouble() * 100);

                    lastTimerCall = now;
                }
            }
        };
    }

    @Override public void start(Stage stage) {
        FlowGridPane pane = new FlowGridPane(7, 6,
                                       digitalTile,    simpleDigitalTile );
        pane.setHgap(5);
        pane.setVgap(5);
        pane.setPadding(new Insets(5));
        pane.setBackground(new Background(new BackgroundFill(Tile.BACKGROUND.darker(), CornerRadii.EMPTY, Insets.EMPTY)));

        Scene scene = new Scene(pane);

        stage.setTitle("TilesFX Dashboard using Medusa and Regulators");
        stage.setScene(scene);
        stage.show();

        timer.start();
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

    @Override public void stop() {
        System.exit(0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
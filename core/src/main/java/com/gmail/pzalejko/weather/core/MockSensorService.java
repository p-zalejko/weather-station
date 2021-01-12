package com.gmail.pzalejko.weather.core;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * A "simulator" of the sensor service - provides dummy implementation that can be used on devices without any sensors
 * (mainly for development purposes).
 */
class MockSensorService implements SensorService {

    @Override
    public List<Sensor> getSensors() {
        return IntStream.range(0, 3)
                .mapToObj(this::createSensor)
                .collect(Collectors.toUnmodifiableList());
    }

    private Sensor createSensor(final int i) {
        return new Sensor() {
            @Override
            public double getValue() {
                return new Random().nextDouble() * 100;
            }

            @Override
            public String getId() {
                return Integer.toString(i);
            }
        };
    }
}

package com.gmail.pzalejko.weather;

import com.pi4j.component.temperature.TemperatureSensor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DefaultSensor implements Sensor {

    private final TemperatureSensor sensor;

    @Override
    public double getValue() {
        return sensor.getTemperature();
    }

    @Override
    public String getId() {
        return sensor.getName();
    }
}

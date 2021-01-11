package com.gmail.pzalejko.weather.app;

import com.gmail.pzalejko.weather.core.Sensor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SensorDescriptor {

    private final Sensor sensor;

    public String getId() {
        return sensor.getId();
    }
}

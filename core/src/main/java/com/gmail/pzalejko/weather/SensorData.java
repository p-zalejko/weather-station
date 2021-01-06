package com.gmail.pzalejko.weather;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class SensorData {

    private final double temperature;
    private final String sensorId;
}

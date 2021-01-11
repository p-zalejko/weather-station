package com.gmail.pzalejko.weather.core;

import java.util.List;

public class CoreLauncher {

    public static void main(String[] args) {
        SensorService sensorService = SensorServiceFactory.create();
        List<Sensor> sensors = sensorService.getSensors();

        for (Sensor sensor : sensors) {
            System.out.printf("Temperature on the %s sensor: %f\n", sensor.getId(), sensor.getValue());
        }
    }
}

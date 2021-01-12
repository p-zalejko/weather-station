package com.gmail.pzalejko.weather.core;

public class SensorServiceFactory {

    public static SensorService create() {
        String underTest = System.getenv("underTest");
        if (Boolean.parseBoolean(underTest)) {
            return new MockSensorService();
        }
        return new DefaultSensorService();
    }
}

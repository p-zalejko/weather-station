package com.gmail.pzalejko.weather.core;

public class SensorServiceFactory {

    public static SensorService create() {
        String underTest = System.getenv("underTest");
        if (underTest != null && Boolean.getBoolean(underTest)) {
            return new MockSensorService();
        }
        return new DefaultSensorService();
    }
}

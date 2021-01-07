package com.gmail.pzalejko.weather;

import lombok.experimental.UtilityClass;

@UtilityClass
public class SensorServiceFactory {

    public static SensorService create( ) {
        return new DefaultSensorService();
    }
}

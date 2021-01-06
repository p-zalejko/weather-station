package com.gmail.pzalejko.weather;

import com.pi4j.io.w1.W1Master;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.util.Properties;

@UtilityClass
public class SensorServiceFactory {

    public static SensorService create(@NonNull Properties properties) {
        String checkInterval = properties.getProperty("checkInterval", "5");
        return new DefaultSensorService(Integer.parseInt(checkInterval), new W1Master());
    }
}

package com.gmail.pzalejko.weather.core;

import com.pi4j.component.temperature.TemperatureSensor;
import com.pi4j.component.temperature.impl.TmpDS18B20DeviceType;
import com.pi4j.io.w1.W1Master;

import java.util.List;
import java.util.stream.Collectors;

class DefaultSensorService implements SensorService {

    @Override
    public List<Sensor> getSensors() {
        System.out.println("Searching sensors...");
        var w1Devices = new W1Master().getDevices(TmpDS18B20DeviceType.FAMILY_CODE);
        var list = w1Devices.stream()
                .map(i -> (TemperatureSensor) i)
                .collect(Collectors.toList());

        System.out.printf("Found %d sensors%n", list.size());
        for (var sensor : list) {
            System.out.printf("Found sensor: %s%n", sensor.getName());
        }

        return list.stream()
                .map(DefaultSensor::new)
                .collect(Collectors.toList());
    }
}

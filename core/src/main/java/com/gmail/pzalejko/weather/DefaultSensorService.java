package com.gmail.pzalejko.weather;

import com.pi4j.component.temperature.TemperatureSensor;
import com.pi4j.component.temperature.impl.TmpDS18B20DeviceType;
import com.pi4j.io.w1.W1Master;

import java.util.List;
import java.util.stream.Collectors;

class DefaultSensorService implements SensorService {

    @Override
    public List<Sensor> getSensors() {
        var w1Devices = new W1Master().getDevices(TmpDS18B20DeviceType.FAMILY_CODE);
        return w1Devices.stream()
                .map(i -> (TemperatureSensor) i)
                .map(DefaultSensor::new)
                .collect(Collectors.toList());
    }
}

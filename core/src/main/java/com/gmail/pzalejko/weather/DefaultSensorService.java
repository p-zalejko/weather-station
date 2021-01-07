package com.gmail.pzalejko.weather;

import com.pi4j.component.temperature.TemperatureSensor;
import com.pi4j.component.temperature.impl.TmpDS18B20DeviceType;
import com.pi4j.io.w1.W1Master;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

class DefaultSensorService implements SensorService {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultSensorService.class);

    @Override
    public List<Sensor> getSensors() {
        LOG.debug("Searching sensors...");
        var w1Devices = new W1Master().getDevices(TmpDS18B20DeviceType.FAMILY_CODE);
        var list = w1Devices.stream()
                .map(i -> (TemperatureSensor) i)
                .collect(Collectors.toList());

        LOG.debug("Found {} sensors", list.size());
        for (var sensor : list) {
            LOG.debug("Found sensor: {}", sensor.toString());
        }

        return list.stream()
                .map(DefaultSensor::new)
                .collect(Collectors.toList());
    }
}

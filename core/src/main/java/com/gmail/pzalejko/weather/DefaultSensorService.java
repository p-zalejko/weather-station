package com.gmail.pzalejko.weather;

import com.pi4j.component.temperature.TemperatureSensor;
import com.pi4j.component.temperature.impl.TmpDS18B20DeviceType;
import com.pi4j.io.w1.W1Device;
import com.pi4j.io.w1.W1Master;
import lombok.NonNull;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

class DefaultSensorService implements SensorService {

    private final List<SensorListener> listeners = new CopyOnWriteArrayList<>();
    private final ScheduledExecutorService executorService;
    private final int checkInterval;
    private final W1Master master;

    DefaultSensorService(int checkInterval, @NonNull W1Master master) {
        this.checkInterval = checkInterval;
        this.executorService = Executors.newScheduledThreadPool(1);
        this.master = master;
    }

    @Override
    public void start() {
        executorService.scheduleAtFixedRate(this::process, checkInterval, checkInterval, TimeUnit.SECONDS);
    }

    @Override
    public void stop() {
        executorService.shutdownNow();
    }

    @Override
    public void addListener(@NonNull SensorListener sensorListener) {
        listeners.add(sensorListener);
    }

    private void process() {
        List<W1Device> w1Devices = master.getDevices(TmpDS18B20DeviceType.FAMILY_CODE);
        List<SensorData> data = w1Devices.stream()
                .map(w1Device -> {
                    var temperature = ((TemperatureSensor) w1Device).getTemperature();
                    var id = w1Device.getId();
                    return new SensorData(temperature, id);
                })
                .collect(Collectors.toList());

      listeners.forEach(i -> i.on(data));
    }
}

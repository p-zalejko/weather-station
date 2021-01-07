package com.gmail.pzalejko.weather.app;

import com.gmail.pzalejko.weather.SensorService;
import lombok.NonNull;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class AppSensorService {

    private final ScheduledExecutorService executorService;
    private final int checkInterval;
    private final SensorService sensorService;
    private List<SensorDataFetcher> sensors;

    public AppSensorService(int checkInterval, @NonNull SensorService sensorService) {
        this.checkInterval = checkInterval;
        this.sensorService = sensorService;
        this.executorService = Executors.newScheduledThreadPool(1);
    }

    public void init() {
        sensors = sensorService.getSensors()
                .stream()
                .map(SensorDataFetcher::new)
                .collect(Collectors.toUnmodifiableList());

        executorService.scheduleAtFixedRate(this::fetchNewData, checkInterval, checkInterval, TimeUnit.SECONDS);
    }

    public List<SensorDescriptor> getSensors() {
        return sensors.stream()
                .map(SensorDataFetcher::getSensor)
                .map(SensorDescriptor::new)
                .collect(toList());
    }

    public void registerTemperatureConsumer(SensorDescriptor descriptor, Consumer<Double> consumer) {
        var sensor = sensors.stream()
                .filter(i -> i.getSensor().getId().equals(descriptor.getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Sensor not known: %s", descriptor.getId())));

        sensor.addConsumer(consumer);
    }

    private void fetchNewData() {
        sensors.forEach(SensorDataFetcher::fetchData);
    }

}

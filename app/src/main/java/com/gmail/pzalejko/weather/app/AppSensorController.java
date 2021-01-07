package com.gene.pw.rm.integrity.scanner;

import lombok.NonNull;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class AppSensorController {

    private final ScheduledExecutorService executorService;
    private final int checkInterval;
    private SensorService sensorService;
    private List<Sensor> sensors;

    public AppSensorController(int checkInterval, @NonNull SensorService sensorService) {
        this.checkInterval = checkInterval;
        this.executorService = Executors.newScheduledThreadPool(1);
    }

    public void init() {
        sensors = new SensorDataFetcher(sensorService.getSensors());
    }

    public SensorDescriptor getSensors() {
        return sensors.stream()
                .map(SensorDescriptor::new)
                .collect(toList());
    }

    public void registerTemperatureConsumer(SensorDescriptor descriptor, Consumer<Double> consumer) {
        var sensor = sensors.stream()
                .filter(i -> i.sensor.getId().equals(descriptor.getId()))
                .findFirst()
                .orElseThrow( () -> new IllegalArgumentException(String.format("Sensor not known: %s",descriptor.getId())))

        sensor.addConsumer(consumer);
    }

    @Value
    private static class SensorDataFetcher {
        private final Sensor sensor;
        private final List<Consumer<Double>> consumers;

        SensorDataFetcher(@NonNull Sensor sensor) {
            this.sensor = sensor;
            this.consumers = new ArrayList<>();
        }

        void addConsumer(@NonNull Consumer<Double> consumer) {
            consumers.add(consumer);
        }

        void fetchData() {
            double value = sensor.getValue();
            consumers.forEach(i -> i.accept(value));
        }
    }

}

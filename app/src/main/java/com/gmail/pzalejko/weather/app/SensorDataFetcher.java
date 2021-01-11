package com.gmail.pzalejko.weather.app;

import com.gmail.pzalejko.weather.core.Sensor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

class SensorDataFetcher {
    private final Sensor sensor;
    private final List<Consumer<Double>> consumers;

    SensorDataFetcher(Sensor sensor) {
        this.sensor = sensor;
        this.consumers = new ArrayList<>();
    }

    void addConsumer(Consumer<Double> consumer) {
        consumers.add(consumer);
    }

    void fetchData() {
        double value = sensor.getValue();
        consumers.forEach(i -> i.accept(value));
    }

    public Sensor getSensor() {
        return sensor;
    }
}
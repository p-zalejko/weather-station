package com.gmail.pzalejko.weather.app;

import com.gmail.pzalejko.weather.Sensor;
import lombok.NonNull;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Value
class SensorDataFetcher {
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
package com.gmail.pzalejko.weather;

public interface SensorService {

    void start();

    void stop();

    void addListener(SensorListener sensorListener);
}

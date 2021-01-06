package com.gmail.pzalejko.weather;

import java.util.List;

public interface SensorListener {

    void on(List<SensorData> data);
}

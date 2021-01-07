package com.gmail.pzalejko.weather;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class MockSensorService implements SensorService {

    private static final Logger LOG = LoggerFactory.getLogger(MockSensorService.class);

    @Override
    public List<Sensor> getSensors() {
        List<Sensor> sensors = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            final  int ii = i;
            sensors.add(
                    new Sensor() {
                        @Override
                        public double getValue() {
                            return new Random().nextDouble() * 100;
                        }

                        @Override
                        public String getId() {
                            return "" + ii;
                        }
                    }
            );
        }
        return sensors;
    }
}

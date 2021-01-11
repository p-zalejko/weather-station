module pzalejko.weatchersensor {
    requires static lombok;

    requires pi4j.core;
    requires pi4j.device;
    requires pi4j.gpio.extension;

    exports com.gmail.pzalejko.weather.core;
}
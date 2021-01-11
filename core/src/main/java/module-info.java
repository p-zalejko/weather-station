import com.gmail.pzalejko.weather.core.ModuleExportedTmpDS18B20DeviceType;
import com.pi4j.io.w1.W1DeviceType;

module pzalejko.weatchersensor {
    requires static lombok;

    requires pi4j.core;
    requires pi4j.device;
    requires pi4j.gpio.extension;
    provides W1DeviceType with ModuleExportedTmpDS18B20DeviceType;
    exports com.gmail.pzalejko.weather.core;
}
module pzalejko.weatherstation {
    requires static lombok;

    // Java
    requires java.base;
    requires slf4j.api;

    // Java-FX
    requires transitive javafx.base;
    requires transitive javafx.graphics;
    requires transitive javafx.controls;
    requires transitive javafx.web;

    requires pzalejko.weatchersensor;
    requires eu.hansolo.tilesfx;
    requires eu.hansolo.medusa;

    exports com.gmail.pzalejko.weather.app;
}
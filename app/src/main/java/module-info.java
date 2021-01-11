module pzalejko.weatherstation {
    requires static lombok;

    // Java-FX
    requires transitive javafx.controls;
    requires transitive javafx.web;

    requires pzalejko.weatchersensor;
    requires eu.hansolo.tilesfx;
    requires eu.hansolo.medusa;

    exports com.gmail.pzalejko.weather.app;
}
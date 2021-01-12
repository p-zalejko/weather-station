module pzalejko.weatherstation {
    requires static lombok;

    // Java-FX
    requires transitive javafx.controls;
    requires transitive javafx.web;
    requires transitive javafx.fxml;

    requires eu.hansolo.tilesfx;
    requires eu.hansolo.medusa;

    opens com.gmail.pzalejko.weather.app to javafx.fxml;
    exports com.gmail.pzalejko.weather.app;
}
module pzalejko.weatherstation {
    requires static lombok;

    // Java-FX
    requires transitive javafx.controls;
    requires transitive javafx.web;
    requires transitive javafx.graphics;
    requires transitive javafx.fxml;

    opens com.gmail.pzalejko.weather.app to javafx.fxml;
    exports com.gmail.pzalejko.weather.app;
}
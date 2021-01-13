# Layrry and JavaFX

This branch contains a PoC JavaFX application that can be launched via layryy. There are two Java main classes:

- one (`com.gmail.pzalejko.weather.app.AppWithController`) that uses FXML file with an associated controller
- the second (`com.gmail.pzalejko.weather.app.App`) that uses FXML without the controller class

# How to use FXML files and layrry

In order to let FXMLLoader load and construct a content of the FXML file it must be configured to use the same class
loader that is associated with the main class. The class loader is important: it provides access to the JavaFX classes
provided by other modules as well as is able to load controllers that can be used within FXML files (which are classes
from your application module).

A classLoader can be configured in the following way:

``` 
            URL location = getClass().getResource("...");
            FXMLLoader fxmlLoader = new FXMLLoader(location);
            fxmlLoader.setClassLoader(getClass().getClassLoader());  // <!-- provide the class loader
            VBox box = fxmlLoader.load();
```

# How to build

It is important to install artifacts in the local maven repository:

``
mvn clean install
``

# How to launch

Go to the `<project-location>/layrrylauncher/launcher` directory and execute:

```
./run-layrry-with-fxml-controller.sh
```

OR

```
./run-layrry-without-fxml-controller.sh
```

# Layrry and JavaFX 

This branch contains a PoC JavaFX application that can be launched via layryy. There are two Java main classes:
- one (`com.gmail.pzalejko.weather.app.AppWithController`) that uses FXML file and an associated controller
- the second (`com.gmail.pzalejko.weather.app.App`) that uses FXML without controller class

# How to use FXML files and layrry

## When JRE contains JavaFX
In case your JDK/JRE contains JavaFX (e.g. https://www.azul.com/downloads/zulu-community/?version=java-15-mts&package=jdk-fx ) then working with JavaFX is much simpler. You do not have to include JavaFX modules in layryy configuration files. You even do not have to pass a classLoader to the FXMLLoader instance.

``` 
            URL location = getClass().getResource("...");
            FXMLLoader fxmlLoader = new FXMLLoader(location);
            VBox box = fxmlLoader.load();
```

## When JRE does not provide JavaFX

In order to let FXMLLoader load and construct a content of the FXML file it must be configured to use the same class loader that is associated with the main class.

It can be done in the following way:

``` 
            URL location = getClass().getResource("...");
            FXMLLoader fxmlLoader = new FXMLLoader(location);
            fxmlLoader.setClassLoader(getClass().getClassLoader());  // <!-- provide the class loader
            VBox box = fxmlLoader.load();
```

#How to build
It is important to install artifacts in the local maven repository:

``
mvn clean install
``

#How to launch

Go to the <project-location>/layrrylauncher/launcher directory and execute:

```
./run-layrry-with-fxml-controller.sh
```

OR

```
./run-layrry-without-fxml-controller.sh
```

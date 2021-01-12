# Weather station

A simple PoC app that uses JavaFX (11) and Java Modules (JPMS) on Raspberry Pi. The application shows the current temperature provided by the DS18B20 temperature sensor.

# Tech stack
- [JPMS](https://openjdk.java.net/projects/jigsaw/spec/)
- [Java 11](https://openjdk.java.net/projects/jdk/11/)
- [JavaFX (11)](https://openjfx.io/)
- [TilesFX](https://github.com/HanSolo/tilesfx)  
- [Pi4J](https://pi4j.com/1.2/index.html)
- [Layryy](https://github.com/moditect/layrry)

# JavaFX Installation

In order to launch a JavaFX application, Java and JavaFX must be installed. See
the [installation script](install-java-and-javafx.sh).

# How to build
It is important to `install` artifacts in the local maven repository. These artifacts will be used for launching the app using e.g. layryy:


``
mvn clean install
``

# How to launch on Raspberry Pi

As a [Java modular application](https://www.oracle.com/corporate/features/understanding-java-9-modules.html):

Go to `<project-location>/app/target` directory and execute:
```
java --module-path /home/pi/armv6hf-sdk/lib --add-modules=javafx.controls,javafx.web -jar /home/pi/app-1.0-SNAPSHOT-jar-with-dependencies.jar
```

Using  [layrry](https://github.com/moditect/layrry):

Go to the `<project-location>/layrrylauncher/launcher` directory and execute:
```
java --module-path /home/pi/armv6hf-sdk/lib --add-modules=javafx.controls,javafx.web -jar layrry-launcher-1.0-SNAPSHOT-all.jar --layers-config layers.toml
```

# Result

![Alt text](IMG20210111231312.jpg?raw=true "")
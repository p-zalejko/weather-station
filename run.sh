#!/bin/bash

cd /home/pi
sudo java --module-path /home/pi/armv6hf-sdk/lib --add-modules=javafx.controls -jar /home/pi/app-1.0-SNAPSHOT-jar-with-dependencies.jar
#!/bin/bash

sudo mkdir -p /usr/lib/jvm
cd /usr/lib/jvm
sudo wget https://cdn.azul.com/zulu-embedded/bin/zulu11.43.100-ca-jdk11.0.9.1-linux_aarch32hf.tar.gz
sudo tar -xzvf zulu11.43.100-ca-jdk11.0.9.1-linux_aarch32hf.tar.gz
sudo rm zulu11.43.100-ca-jdk11.0.9.1-linux_aarch32hf.tar.gz
sudo update-alternatives --install /usr/bin/java java /usr/lib/jvm/zulu11.43.100-ca-jdk11.0.9.1-linux_aarch32hf/bin/java 1
sudo update-alternatives --install /usr/bin/javac javac /usr/lib/jvm/zulu11.43.100-ca-jdk11.0.9.1-linux_aarch32hf/bin/javac 1

cd /home/pi
wget -O javafx.zip https://gluonhq.com/download/javafx-11-0-2-sdk-armv6hf/
unzip javafx.zip
rm javafx.zip

wget https://github.com/p-zalejko/weather-station/releases/download/0.0.1/app-1.0-SNAPSHOT-jar-with-dependencies.jar

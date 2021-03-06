#!/bin/bash
# see https://webtechie.be/post/2020-08-27-azul-zulu-java-11-and-gluon-javafx-11-on-armv6-raspberry-pi/

# needed in case you will want to compile source code directly on the pi (e.g. using maven)
sudo mkdir -p /usr/lib/jvm
cd /usr/lib/jvm
sudo wget https://cdn.azul.com/zulu-embedded/bin/zulu11.43.100-ca-jdk11.0.9.1-linux_aarch32hf.tar.gz
sudo tar -xzvf zulu11.43.100-ca-jdk11.0.9.1-linux_aarch32hf.tar.gz
sudo rm zulu11.43.100-ca-jdk11.0.9.1-linux_aarch32hf.tar.gz
sudo update-alternatives --install /usr/bin/java java /usr/lib/jvm/zulu11.43.100-ca-jdk11.0.9.1-linux_aarch32hf/bin/java 1
sudo update-alternatives --install /usr/bin/javac javac /usr/lib/jvm/zulu11.43.100-ca-jdk11.0.9.1-linux_aarch32hf/bin/javac 1

#JvaFX for ARMv6
cd /home/pi
wget -O javafx.zip https://gluonhq.com/download/javafx-11-0-2-sdk-armv6hf/
unzip javafx.zip
rm javafx.zip

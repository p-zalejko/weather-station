#!/bin/bash

sudo mkdir -p /usr/lib/jvm
cd /usr/lib/jvm
sudo wget https://cdn.azul.com/zulu-embedded/bin/zulu11.43.100-ca-jdk11.0.9.1-linux_aarch32hf.tar.gz

sudo tar -xzvf zulu11.43.100-ca-jdk11.0.9.1-linux_aarch32hf.tar.gz
sudo rm zulu11.43.100-ca-jdk11.0.9.1-linux_aarch32hf.tar.gz
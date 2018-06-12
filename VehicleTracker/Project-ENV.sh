#!/bin/sh
export POSTGRES_HOME=/Applications/Postgres.app/Contents/Versions/10
export POSTGRES_BIN=$POSTGRES_HOME/bin
export JETTY_HOME=/Volumes/Data-HD3TB/Tools/jetty-distribution-9.4.7.v20170914
export JETTY_BASE=/Volumes/Data-HD3TB/Dev/VehicleTrackerDemo/VehicleTracker/jetty

#Comment-out or remove these lines if you already have a java and maven and postgres environment
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_144.jdk/Contents/Home

export PATH=$PATH:$POSTGRES_BIN
export PATH=$PATH:$JAVA_HOME/bin
export PATH=$PATH:/Volumes/Data-HD3TB/Tools/apache-maven-3.5.3/bin


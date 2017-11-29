#!/bin/bash
### shell scripts to start the derby database server.

echo Start Derby...
cd ..
CONFIG_BASE=$PWD
echo CONFIG_BASE folder = $CONFIG_BASE
DERBY_LIB_DIR=$CONFIG_BASE/sql/derby/lib
CLASSPATH=.:$DERBY_LIB_DIR/*
JAVA_HOME=/opt/IBM/WLP85/java/java_1.7.1_64
PATH=$JAVA_HOME/bin:$PATH

cd $CONFIG_BASE
java -Xms256m -Xmx256m -server -cp $CLASSPATH org.apache.derby.drda.NetworkServerControl start -noSecurityManager



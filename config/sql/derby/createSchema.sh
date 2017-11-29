#!/bin/bash
### to create the DB schema
echo ------creating schema of DB------


CLASSPATH=lib/derby.jar:lib/derbyclient.jar:lib/derbytools.jar:lib/derbyrun.jar:$CLASSPATH
#JAVA_HOME=/opt/IBM/WLP85/java/java_1.7.1_64
PATH=$JAVA_HOME/bin:$PATH
echo Create  derby database...
java -Xmx128m -Xms128m -jar lib/derbyrun.jar ij -p smpdb.properties schema.sql
echo Done

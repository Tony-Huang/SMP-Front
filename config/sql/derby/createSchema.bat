@REM echo off
@REM echo ------creating schema of DB------


@set CLASSPATH=lib\derby.jar;lib\derbyclient.jar;lib\derbytools.jar;lib\derbyrun.jar;%CLASSPATH%
@echo Create  derby database...
@java -Xmx128m -Xms128m -jar lib\derbyrun.jar ij -p smpdb.properties schema.sql
@echo Done

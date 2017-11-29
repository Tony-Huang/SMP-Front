@REM ###############################################################

@SETLOCAL

@ECHO Stop Derby...

@cd ..
@set CONFIG_BASE=%CD%
@set DERBY_LIB_DIR=%CONFIG_BASE%\sql\derby\lib
@set CLASSPATH=.;%DERBY_LIB_DIR%\*
@set PATH=%JAVA_HOME%\bin;%PATH%

@cd "%CONFIG_BASE%"
@java -Xms256m -Xmx256m -server -cp "%CLASSPATH%" org.apache.derby.drda.NetworkServerControl shutdown -noSecurityManager
@echo Done

@ENDLOCAL

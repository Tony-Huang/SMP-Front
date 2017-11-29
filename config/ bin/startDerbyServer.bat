@REM ###############################################################
@rem Haina Confidential
@rem
@rem OCO Source Materials
@rem
@rem Haina Products: Spindle monitor platform
@rem
@rem (C) Copyright Haina Corp. 2015
@rem
@rem The source code for this program is not published or otherwise
@rem divested of its trade secrets, irrespective of what has been
@rem deposited with the China Copyright Office.
@REM ###############################################################

@SETLOCAL

@ECHO Start Derby...

@cd ..
@set CONFIG_BASE=%CD%
@set DERBY_LIB_DIR=%CONFIG_BASE%\sql\derby\lib
@set CLASSPATH=.;%DERBY_LIB_DIR%\*
@set PATH=%JAVA_HOME%\bin;%PATH%

@cd "%CONFIG_BASE%"
@java -Xms256m -Xmx256m -server -cp "%CLASSPATH%" org.apache.derby.drda.NetworkServerControl start -noSecurityManager

@ENDLOCAL

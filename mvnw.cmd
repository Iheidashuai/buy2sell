@echo off
setlocal
set BASE_DIR=%~dp0
set WRAPPER_DIR=%BASE_DIR%.mvn\wrapper
set WRAPPER_JAR=%WRAPPER_DIR%\maven-wrapper.jar
set PROPERTIES_FILE=%WRAPPER_DIR%\maven-wrapper.properties

if not exist "%WRAPPER_JAR%" (
  echo Downloading Maven Wrapper...
  powershell -NoProfile -ExecutionPolicy Bypass -Command "(Get-Content '%PROPERTIES_FILE%' | Select-String '^wrapperUrl=').ToString().Split('=',2)[1] | ForEach-Object { Invoke-WebRequest -Uri $_ -OutFile '%WRAPPER_JAR%' }"
)

java -Dmaven.multiModuleProjectDirectory="%BASE_DIR%" -classpath "%WRAPPER_JAR%" org.apache.maven.wrapper.MavenWrapperMain %*
endlocal

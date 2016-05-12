#!/bin/bash

echo "[INFO] run install."

cd ..

set MAVEN_OPTS=%MAVEN_OPTS% -XX:MaxPermSize=128m

mvn clean install

cd bin

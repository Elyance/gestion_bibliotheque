#!/bin/bash

# Configuration
TOMCAT_HOME="/home/elyance/Documents/apache-tomcat-10.1.28"  # Modifie ceci si ton Tomcat est ailleurs
PROJECT_NAME="Bibliotheque"
VERSION="1.0"
WAR_FILE="target/${PROJECT_NAME}-${VERSION}.war"

echo "[INFO] Stopping Tomcat..."
$TOMCAT_HOME/bin/shutdown.sh

echo "[INFO] Cleaning Tomcat webapps and target directory..."
rm -rf "$TOMCAT_HOME/webapps/${PROJECT_NAME}-${VERSION}"
rm -f "$TOMCAT_HOME/webapps/${PROJECT_NAME}-${VERSION}.war"
rm -rf target/

echo "[INFO] Building project with Maven..."
mvn clean package

echo "[INFO] Copying WAR file to Tomcat..."

if [ -f "$WAR_FILE" ]; then
    cp "$WAR_FILE" "$TOMCAT_HOME/webapps/"
    echo "[SUCCESS] WAR file copied successfully to Tomcat webapps folder."
else
    echo "[ERROR] WAR file not found in target directory."
    echo "[INFO] Listing available WAR files:"
    ls target/*.war
fi

echo "[INFO] Starting Tomcat..."
$TOMCAT_HOME/bin/startup.sh

echo
echo "[INFO] If the copy was successful, access your app at:"
echo "http://localhost:8080/${PROJECT_NAME}-${VERSION}/"
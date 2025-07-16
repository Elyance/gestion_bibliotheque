#!/bin/bash

# Création du répertoire lib et copie du servlet-api.jar
mkdir -p lib

projectName="entreprise"

# Création des répertoires Java et WEB-INF
javaPath="src/main/java"
mkdir -p "$javaPath"

controllerPath="src/main/java"

ressourcesPath="src/main/ressources"
mkdir -p "$ressourcesPath"

webInfPath="src/main/webapp/WEB-INF"
mkdir -p "$webInfPath"

# Création du fichier web.xml
xmlPath="src/main/webapp/WEB-INF/web.xml"


cat > "$xmlPath" << 'EOF'
<?xml version="1.0" encoding="UTF-8"?>
    <web-app xmlns="http://java.sun.com/xml/ns/j2ee"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://java.sun.com/xml/ns/j2ee 
        http://java.sun.com/xml/ns/j2ee/web-app_2_4.xsd" 
        version="2.4">
        <display-name>Application WEB affichant HelloWorld</display-name> 
        <servlet> 
            <servlet-name>HelloWorldServlet</servlet-name> 
            <servlet-class>hello.HelloWorld</servlet-class> 
        </servlet>
        <servlet-mapping> 
            <servlet-name>HelloWorldServlet</servlet-name> 
            <url-pattern>/Nomena</url-pattern> 
        </servlet-mapping> 
    </web-app>
EOF

echo "Fichier XML créé : $xmlPath"

# Création du fichier pom.xml
pomPath="pom.xml"

cat > "$pomPath" << 'EOF'
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
    http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.example</groupId>
    <artifactId>HelloWorldApp</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>war</packaging>
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-war-plugin</artifactId>
                <version>3.3.2</version>
            </plugin>
        </plugins>
    </build>
</project>
EOF

echo "Fichier POM créé : $pomPath"
read -p "Appuyez sur une touche pour continuer..."
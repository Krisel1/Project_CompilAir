FROM openjdk:17-jdk-slim

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el archivo JAR de la aplicación al contenedor Animal-Shelter-0.0.1-SNAPSHOT.jar
COPY target/CompilAir-0.0.1-SNAPSHOT.jar CompilAir.jar

# Exponer el puerto en el que la aplicación se ejecuta
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "CompilAir.jar"]
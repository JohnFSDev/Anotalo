# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Añadimos la aplicación al contenedor.
COPY target/Anotalo-0.0.1-SNAPSHOT.jar Anotalo.jar

# ENV PORT = 8089.
EXPOSE 8089

# Corremos el Jar File.
ENTRYPOINT ["java", "-jar", "Anotalo.jar"]

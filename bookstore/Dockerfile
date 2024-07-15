# Use the OpenJDK image as the base image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file into the container
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Expose the Spring Boot port
EXPOSE 5000

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

# Use an official Java runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot jar file into the container
COPY target/jpl-ferrari-jobhunt-0.0.1-SNAPSHOT.jar app.jar

# Expose the port that the application will run on
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]

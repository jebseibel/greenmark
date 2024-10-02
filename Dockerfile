FROM ubuntu:latest
LABEL authors="jeb"

ENTRYPOINT ["top", "-b"]
FROM openjdk:21

# Copy the built JAR file from the target directory to the container
COPY build/libs/greenmark-1.0-plain.jar ./app-greenmark.jar

# Expose the application's port (default for Spring Boot is 8080)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app-greenmark.jar"]
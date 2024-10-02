FROM ubuntu:22.04
LABEL authors="jeb"

ENTRYPOINT ["top", "-b"]
FROM openjdk:21

RUN mkdir /apd

# Copy the application JAR file to the container
COPY build/libs/greenmark-1.0.jar /apd

WORKDIR /apd

# Expose the port on which the application runs (e.g., 8080 for Spring Boot)
#EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "greenmark-1.0.jar"]
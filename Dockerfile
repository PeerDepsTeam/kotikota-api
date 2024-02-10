FROM ubuntu:latest AS base

FROM gradle:8.5-jdk17 AS build
WORKDIR /app
COPY . /app
ARG DB_URL
ENV DB_URL=$DB_URL
ARG DB_NAME
ENV DB_NAME=$DB_NAME
ARG DB_PASSWORD
ENV DB_PASSWORD=$DB_PASSWORD
ARG FB_KEY
ENV FB_KEY=$FB_KEY

# Change permissions for gradlew
RUN chmod +x ./gradlew

# Copy the publish script into the Docker image
COPY publish_gen_to_maven_local.sh /app/

# Ensure script is executable
RUN chmod +x /app/publish_gen_to_maven_local.sh

# Run the script with debugging output
RUN mkdir -p build/gen && bash -x /app/publish_gen_to_maven_local.sh

RUN ./gradlew clean build

# Use the official OpenJDK 17 base image
FROM openjdk:17 AS final

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/build/libs/*.jar /app/kotikota.jar

EXPOSE 8080

# Command to run your application
CMD ["java", "-jar", "/app/kotikota.jar"]
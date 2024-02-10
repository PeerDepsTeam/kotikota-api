# Use the official Maven image to build the application
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

# Run the Gradle build
RUN ./gradlew clean build -x test

# Use the official Maven image to generate and install Maven artifacts
FROM maven:3.8.4 AS maven_build
WORKDIR /app
COPY --from=build /app/build/gen /app/gen
RUN cd /app/gen && mvn clean install

# Use the official OpenJDK 17 base image
FROM openjdk:17 AS final

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the Gradle build stage
COPY --from=build /app/build/libs/*.jar /app/kotikota.jar

# Copy the Maven artifacts from the Maven build stage
COPY --from=maven_build /app/gen/target/*.jar /app/

EXPOSE 8080

# Command to run your application
CMD ["java", "-jar", "/app/kotikota.jar"]

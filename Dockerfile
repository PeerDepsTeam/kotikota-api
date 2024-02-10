FROM docker.io/library/gradle:8.5-jdk17@sha256:7704366590930c03de7e514008ba3d7b7031b92591bd5a74fae79c16f3a17726 AS build

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

# Initialize Maven repository inside the build container
RUN gradle -q initMavenRepo || true

# Run the script with debugging output
RUN apt-get update && \
        apt-get install -y maven && \
        mkdir -p build/gen && \
        bash -x /app/publish_gen_to_maven_local.sh

RUN ./gradlew clean build

FROM openjdk:17 AS final

WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/build/libs/*.jar /app/kotikota.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/kotikota.jar"]
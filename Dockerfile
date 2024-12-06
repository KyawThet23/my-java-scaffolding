FROM amazoncorretto:21-alpine AS builder
WORKDIR /app
COPY gradlew gradlew.bat settings.gradle build.gradle /app/
COPY gradle /app/gradle
COPY src /app/src

RUN chmod +x gradlew

# Build the application
RUN ./gradlew bootJar

# Use a smaller base image for the final artifact
FROM amazoncorretto:21-alpine

WORKDIR /app
COPY --from=builder /app/build/libs/*.jar test-dock.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/test-dock.jar"]

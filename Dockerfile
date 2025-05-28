# Use a lightweight OpenJDK base image
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the built JAR into the image
COPY target/JAVA_TRAINING-*.jar app.jar

# Expose the application port
EXPOSE 8088

# Run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]

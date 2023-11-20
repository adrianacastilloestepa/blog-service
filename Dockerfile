# Use an official Maven image as the base image
FROM maven:3.8.4-openjdk-17-slim AS build
# Set the working directory in the container
WORKDIR /app
# Copy the pom.xml and the project files to the container
COPY pom.xml .
COPY src ./src
# Build the application using Maven
RUN mvn clean package
# Use an official OpenJDK image as the base image
FROM amazoncorretto:17-alpine-jdk
# Set the working directory in the container
WORKDIR /app
# Copy the built JAR file from the previous stage to the container
COPY --from=build /app/target/blog-service-0.0.1-SNAPSHOT.jar .
RUN chmod +x blog-service-0.0.1-SNAPSHOT.jar
# Set the command to run the application
CMD ["java", "-jar", "blog-service-0.0.1-SNAPSHOT.jar"]
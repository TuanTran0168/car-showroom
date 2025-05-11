#
# Stage 1: build
# Start with a Maven image that includes JDK 21
#
FROM maven:3.9.9-amazoncorretto-21-alpine AS build

# Copy source code and pom.xml file to /app folder
WORKDIR /app
COPY pom.xml .
COPY src ./src

# Build the application with Maven
RUN mvn clean package -DskipTests

#Stage 2: create image
# Start with Amazon Correto JDK 21
FROM amazoncorretto:24.0.1-alpine3.21

# Set working folder to App and copy complied file from above step
WORKDIR /app

# Copy the built JAR file from the previous stage
COPY --from=build /app/target/*.jar app.jar

# Command to run the application
# java -jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

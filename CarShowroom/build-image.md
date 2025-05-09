
# Car Showroom - Spring Boot + Docker + MySQL

This is a car showroom management application built with Spring Boot. The following guide will help you build the project, create Docker images, and run the application with MySQL using Docker network.

---

## 1. Check Java Version

```bash
java --version
```

> Example output:
```
java 23.0.2 2025-01-21
Java(TM) SE Runtime Environment (build 23.0.2+7-58)
Java HotSpot(TM) 64-Bit Server VM (build 23.0.2+7-58, mixed mode, sharing)
```

---

## 2. Build Spring Application (Skip Unit Tests)

```bash
./mvnw clean
./mvnw package -DskipTests
```

> The JAR file will be created in the `target/` directory.

---

## 3. Run the Application with JAR File

```bash
cd target
java -jar CarShowroom-0.0.1-SNAPSHOT.jar
```

> Make sure MySQL is running and the database is properly configured.

---

## 4. Create Dockerfile

```Dockerfile
# Stage 1: Build
FROM maven:3.9.9-amazoncorretto-23 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run image
FROM amazoncorretto:8-al2023-jdk
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
```

---

## 5. Build Docker Image

```bash
docker build -t car-showroom:0.0.1 .
docker images
```

---

## 6. Create Docker Network

```bash
docker network create car-showroom-network
docker network ls
```

---

## 7. Run MySQL Container

```bash
docker run --network car-showroom-network --name spring-mysql-network -e MYSQL_ROOT_PASSWORD=Admin@123 -d -p 3307:3306 mysql:8.0.31
```

> After running, enter the container and create a database named `car-showroom`.

---

## 8. Run Spring Boot Container

```bash
docker run --network car-showroom-network --name car-showroom-app -p 8080:8080 -e DBMS_CONNECTION=jdbc:mysql://spring-mysql-network:3306/car-showroom car-showroom:0.0.1
```

---

## 9. Configure `application.yaml`

```yaml
spring:
  datasource:
    url: ${DBMS_CONNECTION:jdbc:mysql://localhost:3306/car-showroom}
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: root
    password: Admin@123
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        format_sql: true
        use_sql_comments: true
```

> Note: `spring-mysql-network` is the name of the MySQL container. The port inside the container is `3306`.

---


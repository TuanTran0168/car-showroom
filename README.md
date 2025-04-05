
# Car Showroom Project


The Car Showroom project is an application designed to manage the operations of a car dealership. It facilitates the management of various car models, their features, and brand details, making it easier for users to explore and manage cars in the showroom. The project includes features such as brand management, car model categorization, and configuration of specific car attributes.



## Run Locally

**Clone the project**

```bash
  git clone https://github.com/TuanTran0168/car-showroom.git
```

**Config your Database (MySQL, PostgreSQL,...)**

- Before running the project, configure the application to connect to your local database.

- Open the application.yml file located in src/main/resources.


```bash
  spring:
    datasource:
        url: jdbc:postgresql://localhost:5432/database-name
        driver-class-name: org.postgresql.Driver
        username: your-username
        password: your-password
```

**Go to the project directory**

```bash
  cd CarShowroom
```

**Clean & Build Maven Package**

```bash
  mvnw clean
```

```bash
  mvnw install
```

**Run the Application**

```bash
  mvnw spring-boot:run
```
## Tech Stack

**Server:** Spring Boot

**Client:** React




# CompilAIR

## Team 1

- Francisco José Charneco Cano https://github.com/FranciscoJChCano (Product Owner)
- Eva Martínez https://github.com/EvaMartinez94 (Scrum Master)
- Hilmar Hernández https://github.com/Hilmar09 (Developer)
- Krisel Urdaneta https://github.com/Krisel1 (Developer)
- Jackelin Cochachi https://github.com/JackS1718 (Developer)

## Overview
- The project aims to develop a management system for an airline using a monolithic architecture based on Spring Boot.
- This system will allow the integrated management of users, flights, reservations and destinations, with advanced functionalities such as secure authentication through JWT, automatic deletion of flights without available seats or that have exceeded the deadline.
- The project will be implemented using Java 17, Maven and MySQL, and will focus on modularity, security and efficiency.

## Project objectives

- To reinforce the concepts of API creation.
- Apply DB relationships.
- Data management through concurrency management.
- Establish knowledge of login with Spring Security and JWT.
- Assimilate knowledge of GitHub Actions.

## Tools Used

- **Java**
- **Spring Boot**
- **IntelliJ IDEA**
- **GitHub**
- **Postman**
- **Miro**
- **Trello**
- **Docker**
- **LUCIDCHART**
- **MySQL**
- **PHPMyAdmin**
- **Xampp**


## Project Structure

The project follows a standard Spring Boot application structure:

- **AutomaticSQL** Automatic data creation for the SQL database.
- **Config:** Configuration classes for Spring Security, application settings, and object mapping.
- **Controllers:** REST API endpoints for authentication, booking, flights,routes and user.
- **Dtos:** Data Transfer Objects (DTOs) for handling requests and responses.
- **Jwt:** JWT authentication filter and utilities.
- **Models:** Entity classes representing database tables.
- **Repositories:** JPA repositories for database operations.
- **Services:** Business logic implementation for handling authentication, booking, flights,routes and user functionalities.
- **Test:** Unit and implementation tests for controllers and services.

## Docker Setup

### Docker Compose Configuration

This project includes a `compose.yml` file for setting up the application using Docker:

```yaml
version: "3.9"
services:
  compilAir:
    build: .
    ports:
      - "8080:8080"
    environment:
      SPRING_DATASOURCE_URL: jdbc:mysql://mysql_service:3306/db_compilair
      SPRING_DATASOURCE_USERNAME: root
      SPRING_DATASOURCE_PASSWORD: 1234
      SPRING_JPA_HIBERNATE_DDL_AUTO: update
      SPRING_JPA_PROPERTIES_HIBERNATE_DIALECT: org.hibernate.dialect.MySQL8Dialect
    restart: always
    depends_on:
      mysql_service:
        condition: service_healthy

  mysql_service:
    image: mysql:8.0.33
    ports:
      - "3307:3306"
    environment:
      MYSQL_ROOT_PASSWORD: 1234
      MYSQL_DATABASE: db_compilair
    restart: always
    healthcheck:
      test: ["CMD", "mysqladmin", "ping", "-h", "localhost"]
      timeout: 10s
      retries: 5
```
- **SQL container required on Docker Desktop**
### Dockerfile

The Dockerfile is used to build a Docker image for the CompilAir application:

```dockerfile
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/CompilAir-0.0.1-SNAPSHOT.jar CompilAir.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "CompilAir.jar"]
```

### Running the Application with Docker

1. **Build the Docker image:**
   ```bash
   docker-compose build
   ```

2. **Run the application:**
   ```bash
   docker-compose up
   ```

3. **Access the application:**
   Open your browser and navigate to `http://localhost:8080`.

## Key Components

### Configuration
- **ApplicationConfig:** Configures beans for authentication, password encoding, and user details service.
- **WebSecurityConfig:** Sets up security filters and access rules for different endpoints.
- **JacksonConfig:** Customizes the ObjectMapper for JSON serialization/deserialization.

### Controllers
- **AuthController:** Manages user authentication and registration.
- **BookingController:** Manages CRUD operations related to flight bookings in the application.
- **FlightController:** Manages flight-related CRUD operations in the application
- **RouteController:** Manages the CRUD operations related to flight paths in the application
- **UserController:** Manages user CRUD for get,create,update and delete.

### Models
- **Booking:** Represents booking entities with attributes such as id,name,surname,phone,genre,email,birthdayDate,identificationType,identifiticationNumber,address,zipCode,country, and city.
- **Flight:** Represents flight entities with attributes such as id,flightName,flightStatus,departureDate,returnDate,totalSeats and destination.
- **Route:** Represents route entities with attributes such as id and nameRoute.
- **User:** Represents users and implements `UserDetails` for Spring Security.

### API Endpoints

- **Authentication:** `/api/auth/login`, `/api/auth/register`
- **Booking:** `/api/bookings`
- **Flight:** `/api/flights`
- **Route:** `/api/routes`
- **Users:** `/api/users`


## Setup and Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/Krisel1/Project_CompilAir.git
   ```
2. **Configure your database settings** in `application.properties`.
3. **Run the application** using Maven:
   ```bash
   ./mvnw spring-boot:run
   ```

## Security

This application uses Spring Security with JWT for authentication. Include the JWT token in the `Authorization` header for accessing protected endpoints.

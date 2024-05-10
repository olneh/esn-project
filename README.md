# ESN Project Backend Application

- Java Spring Boot (v3.2.3)
- Java JDK: Version 17.0.2
- Build System: Gradle

- Spring Data JPA
- Tomcat Server on port 8443 with HTTPS
- Spring Boot DevTools for auto-restarts and live reloading
- Security Features: Implements SSL configuration, HTTPS

## Getting Started

To get started with the ESN Project Application:

1. Install Java JDK 17.0.2.
2. Setup Gradle for dependency management.
3. Clone the repository to your local machine.
4. Navigate to the project directory and run the application.

## Database Configuration

The application is configured to use PostgreSQL for its database needs. 

Below are the settings used to connect to local PostgreSQL database:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/esn
spring.datasource.username=postgres
spring.datasource.password=postgres

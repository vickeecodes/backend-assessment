# Backend Assessment API

A modular Spring Boot REST API implementing JWT authentication, role-based access control, rate limiting, input validation, MySQL persistence, and interactive Swagger/OpenAPI documentation.

## Features

- User registration and login
- BCrypt password hashing
- JWT-based authentication
- Role-Based Access Control (RBAC)
- Roles:
  - ADMIN
  - INTERN
  - MEMBER
- Protected user profile endpoint
- Authentication endpoint rate limiting
- Input validation
- Structured JSON validation errors
- MySQL database integration
- Swagger/OpenAPI documentation
- Environment-based configuration
- Docker Compose configuration

## Technology Stack

- Java 17
- Spring Boot 4.1.1
- Spring Security
- Spring Data JPA
- Hibernate
- MySQL
- JWT
- BCrypt
- Bucket4j
- Springdoc OpenAPI / Swagger UI
- Maven
- Docker

## Project Structure

```text
backend-assessment/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/backend/assessment/
│   │   │       ├── config/
│   │   │       │   ├── JwtAuthenticationFilter.java
│   │   │       │   ├── OpenApiConfig.java
│   │   │       │   ├── RateLimitFilter.java
│   │   │       │   ├── SecurityBeansConfig.java
│   │   │       │   └── SecurityConfig.java
│   │   │       │
│   │   │       ├── controller/
│   │   │       │   ├── AdminController.java
│   │   │       │   ├── AuthController.java
│   │   │       │   ├── InternController.java
│   │   │       │   ├── MemberController.java
│   │   │       │   └── UserController.java
│   │   │       │
│   │   │       ├── dto/
│   │   │       ├── entity/
│   │   │       ├── exception/
│   │   │       ├── repository/
│   │   │       └── service/
│   │   │
│   │   └── resources/
│   │       └── application.properties
│   │
│   └── test/
│
├── .env.example
├── .gitignore
├── docker-compose.yml
├── pom.xml
└── README.md
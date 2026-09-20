# Backend Assessment API

A modular Spring Boot REST API implementing JWT authentication, role-based access control, rate limiting, input validation, MySQL persistence, and interactive Swagger/OpenAPI documentation.

## Features

- User registration and login
- BCrypt password hashing
- JWT-based authentication
- Role-Based Access Control (RBAC)
- Three user roles:
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
- Docker support
- Production deployment using Render
- Cloud MySQL database using Aiven

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
- Render
- Aiven MySQL

## Architecture

The application follows a modular layered architecture:

```text
Client
  |
  v
REST Controllers
  |
  v
Security / JWT / Rate Limiting
  |
  v
Service Layer
  |
  v
Repository Layer
  |
  v
MySQL Database

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
├── Dockerfile
├── docker-compose.yml
├── pom.xml
└── README.md

## Live API

Production API:

https://backend-assessment-v84h.onrender.com

## Swagger Documentation

Interactive Swagger UI:

https://backend-assessment-v84h.onrender.com/swagger-ui/index.html

OpenAPI specification:

https://backend-assessment-v84h.onrender.com/v3/api-docs

## API Endpoints

### Authentication

| Method | Endpoint | Authentication |
|---|---|---|
| POST | `/api/auth/register` | Public |
| POST | `/api/auth/login` | Public |

### User

| Method | Endpoint | Authentication |
|---|---|---|
| GET | `/api/users/me` | JWT Required |

### Role-Based APIs

| Method | Endpoint | Required Role |
|---|---|---|
| GET | `/api/admin/test` | ADMIN |
| GET | `/api/intern/test` | INTERN |
| GET | `/api/member/test` | MEMBER |

## Authentication Flow

```text
1. Register user
       |
       v
2. Password is hashed using BCrypt
       |
       v
3. User is stored in MySQL
       |
       v
4. Login using email and password
       |
       v
5. Server validates credentials
       |
       v
6. JWT token is generated
       |
       v
7. Client sends JWT in Authorization header
       |
       v
8. JWT filter validates token
       |
       v
9. Request is authorized based on user role

## Registration

Example request:

```bash
curl -X POST "https://backend-assessment-v84h.onrender.com/api/auth/register" \
-H "Content-Type: application/json" \
-d "{\"name\":\"John Doe\",\"email\":\"john@example.com\",\"password\":\"Password@123\"}"


## Login

Example request:

```bash
curl -X POST "https://backend-assessment-v84h.onrender.com/api/auth/login" \
-H "Content-Type: application/json" \
-d "{\"email\":\"john@example.com\",\"password\":\"Password@123\"}"


## Access Protected User API

Use the JWT returned from the login API.

```bash
curl -X GET "https://backend-assessment-v84h.onrender.com/api/users/me" \
-H "Authorization: Bearer YOUR_JWT_TOKEN"



## Role-Based Access Control

The application implements role-based authorization using Spring Security.

### ADMIN

ADMIN users can access:

```text
/api/admin/**

## Rate Limiting

Rate limiting is implemented using Bucket4j.

The following authentication endpoints are rate limited:

```text
POST /api/auth/register
POST /api/auth/login
## Input Validation

Request payloads are validated using Jakarta Bean Validation.

Invalid input returns a structured JSON response.

Example validation error:

```json
{
  "status": 400,
  "message": "Validation failed",
  "errors": {
    "name": "Name must be between 2 and 100 characters",
    "password": "Password is required",
    "email": "Please provide a valid email address"
  }
}

## Password Security

Passwords are never stored as plain text.

The application uses BCrypt password hashing before storing passwords in MySQL.

```text
Plain Password
      |
      v
BCrypt Hashing
      |
      v
Hashed Password
      |
      v
MySQL

## JWT Security

The application uses JWT for stateless authentication.

Protected requests must include:

```text
Authorization: Bearer YOUR_JWT_TOKEN

## Environment Variables

The application uses environment variables for database and JWT configuration.

Example:

```env
DB_URL=jdbc:mysql://localhost:3306/backend_assessment
DB_USERNAME=root
DB_PASSWORD=your_mysql_password

JWT_SECRET=your_secure_jwt_secret_key
JWT_EXPIRATION=3600000


## Running Locally

### Prerequisites

- Java 17
- Maven
- MySQL
- Git

### Database

Create the MySQL database:

```sql
CREATE DATABASE backend_assessment;

## Docker

Build the Docker image:

```bash
docker build -t backend-assessment .



## Deployment

The application is deployed as a Docker-based Spring Boot application.

### Production Components

- Spring Boot REST API
- Render
- Aiven MySQL
- JWT authentication
- Swagger/OpenAPI documentation

### Production API

https://backend-assessment-v84h.onrender.com

### Production Swagger UI

https://backend-assessment-v84h.onrender.com/swagger-ui/index.html

### Production OpenAPI Specification

https://backend-assessment-v84h.onrender.com/v3/api-docs


## Testing Summary

The following functionality has been tested successfully:

- User registration returns `201 Created`
- User login returns `200 OK`
- JWT token generation works
- JWT authentication works
- `/api/users/me` requires authentication
- MEMBER role access works
- ADMIN role access works
- INTERN role access works
- Cross-role access returns `403 Forbidden`
- Invalid request validation returns `400 Bad Request`
- Authentication rate limiting returns `429 Too Many Requests`
- BCrypt password hashing is implemented
- Swagger documentation is available
- Production deployment is available
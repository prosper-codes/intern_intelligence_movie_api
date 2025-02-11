
# Movie API Project

The **Movie API** is a Spring Boot-based RESTful web service for managing a movie database. This project has been developed in multiple tasks to showcase various aspects of a real-world application, including basic CRUD functionality, performance optimization, automated testing, and security.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [Task Details](#task-details)
  - [Task 1: Basic CRUD API](#task-1-basic-crud-api)
  - [Task 2: Performance Optimization](src/main/java/com/example/movie_api/services)
  - [Task 3: Automated Testing](#task-3-automated-testing)
  - [Task 4: (Optional) Further Test Automation](#task-4-automated-test-scenarios)
  - [Task 5: Security & Authentication](#task-5-security-and-authentication)
- [API Endpoints](#api-endpoints)
- [CI/CD Integration](#cicd-integration)
- [SSL/TLS Configuration](#ssltls-configuration)
- [License](#license)

## Overview

This project implements a RESTful API for managing movies. Users can create, read, update, and delete movie records. In addition, the application demonstrates:

- **Performance Optimization:** Techniques such as pagination, indexing, and caching (using Hibernate second-level cache via JCache with EhCache) are implemented.
- **Automated Testing:** Unit tests (using JUnit 5 and Mockito) and integration tests (using Spring Boot’s testing support and MockMvc) ensure that the API works correctly and reliably.
- **Security & Authentication:** The API is secured using Spring Security with JWT-based authentication. SSL/TLS is configured for secure data transmission.

## Features

- **Basic CRUD Operations:** Manage movie records with endpoints to create, read, update, and delete movies.
- **Performance Enhancements:** Optimized SQL queries, indexing, pagination, and caching improve performance under high data traffic.
- **Automated Testing:** Comprehensive unit and integration tests help detect issues early and maintain high code quality.
- **Security Measures:** JWT authentication restricts API access to authorized users, and SSL/TLS secures data in transit.
- **CI/CD Ready:** A sample GitHub Actions workflow is provided to automate testing and deployment.

## Project Structure

The project follows a standard Maven-based Spring Boot structure:

```
movie-api/
├── src/
│   ├── main/
│   │   ├── java/com/example/movie_api/
│   │   │   ├── config/
│   │   │   │   └── SecurityConfig.java
│   │   │   │   └── SslConfig.java           # (Custom SSL configuration)
│   │   │   ├── controllers/
│   │   │   │   ├── MovieController.java     # CRUD endpoints
│   │   │   │   └── AuthenticationController.java  # Auth endpoints
│   │   │   ├── models/
│   │   │   │   └── Movie.java
│   │   │   ├── repositories/
│   │   │   │   └── MovieRepository.java
│   │   │   ├── security/
│   │   │   │   ├── JwtAuthenticationEntryPoint.java
│   │   │   │   ├── JwtAuthenticationFilter.java
│   │   │   │   └── JwtUtil.java
│   │   │   └── services/
│   │   │       └── MovieService.java
│   │   └── resources/
│   │       ├── application.properties       # SSL, JWT, DB, caching configuration
│   │       └── keystore.p12                 # SSL keystore (if using SSL)
│   └── test/
│       └── java/com/example/movie_api/
│           ├── controllers/
│           │   └── MovieControllerIntegrationTest.java
│           └── services/
│               └── MovieServiceTest.java
├── .github/
│   └── workflows/
│       └── maven.yml                        # Sample CI/CD workflow using GitHub Actions
├── pom.xml
└── README.md
```

## Getting Started

### Prerequisites

- Java 17 or later
- Maven
- (Optional) A valid `keystore.p12` file if you want to run with SSL/TLS enabled

### Installation

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/your-username/movie-api.git
   cd movie-api
   ```

2. **Build the Project:**

   ```bash
   mvn clean install
   ```

3. **Run the Application:**

   ```bash
   mvn spring-boot:run
   ```

4. **Access the API:**

   The API will be available at [http://localhost:8080](http://localhost:8080).  
   Swagger UI is available at [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html) (if enabled).

## Task Details

### Task 1: Basic CRUD API

- **Objective:** Develop a RESTful API for movie records.
- **Implementation:**  
  - Endpoints are provided in `MovieController.java`.
  - Data is managed through the `Movie` entity and `MovieRepository.java`.
  - CRUD operations are handled in `MovieService.java`.

### Task 2: Performance Optimization

- **Objective:** Optimize database queries for high traffic.
- **Techniques Implemented:**  
  - **Indexing:** Applied on frequently queried columns.
  - **Pagination:** Implemented in repository methods and controller endpoints.
  - **Caching:** Hibernate second-level caching is configured using JCache with EhCache.
- **Relevant Files:**  
  - `Movie.java` (with indexing annotations)
  - `MovieService.java` (with paginated methods)

### Task 3: Automated Testing

- **Objective:** Create automated unit and integration tests.
- **Tools:** JUnit 5, Mockito, and Spring Boot Test.
- **Implementation:**  
  - **Unit Tests:** Located in `MovieServiceTest.java`.
  - **Integration Tests:** Located in `MovieControllerIntegrationTest.java`.
- **CI/CD Integration:**  
  - A sample GitHub Actions workflow (`.github/workflows/maven.yml`) runs tests on each push or pull request.

### Task 4: Automated Test Scenarios

- **Objective:** Ensure the API functions correctly by automating test scenarios.
- **Details:**  
  - Additional tests are added as needed and integrated into your CI/CD pipeline.
  - Branching strategy: You may maintain separate branches (e.g., `task-4-tests`) for isolating testing changes.

### Task 5: Security and Authentication

- **Objective:** Secure the API so that only authorized users can access it.
- **Implementation:**  
  - **JWT Authentication:**  
    - `JwtUtil.java` handles token generation.
    - `JwtAuthenticationFilter.java` validates JWTs on incoming requests.
    - `AuthenticationController.java` provides an `/authenticate` endpoint.
  - **Spring Security Configuration:** Implemented in `SecurityConfig.java`.
  - **SSL/TLS:** Configured via `application.properties` and optionally using a custom `SslConfig.java`.
- **Usage:**  
  - To authenticate, send a POST request to `/authenticate` with username and password.  
  - The API endpoints (except those explicitly permitted) require a valid JWT in the `Authorization` header.

## API Endpoints

- **Movies:**  
  - `GET /api/movies` – Retrieve paginated list of movies  
  - `GET /api/movies/{id}` – Retrieve a single movie  
  - `POST /api/movies` – Add a new movie  
  - `PUT /api/movies/{id}` – Update an existing movie  
  - `DELETE /api/movies/{id}` – Delete a movie

- **Authentication:**  
  - `POST /authenticate` – Obtain a JWT token for accessing secured endpoints

## CI/CD Integration

A sample GitHub Actions workflow is included in `.github/workflows/maven.yml` to:
- Checkout code
- Set up Java 17
- Build the project and run tests
- (Optionally) deploy the application if tests pass

## SSL/TLS Configuration

- The application is configured to use SSL/TLS via settings in `application.properties`.
- **Important:** Ensure that a valid `keystore.p12` file exists in `src/main/resources`.  
- If you are testing locally without SSL, comment out the SSL-related properties in `application.properties`.



---

```

---

This README covers all tasks, from basic CRUD operations to performance optimization, automated testing, and security enhancements, while also providing guidance on project setup, API usage, and CI/CD integration.

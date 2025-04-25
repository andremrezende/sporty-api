# Sporty API

This project is a modular, Java 17-based backend built with **Hexagonal Architecture (Ports and Adapters)** and organized by **multi-module Maven structure**. It emphasizes separation of concerns, scalability, and clean domain-driven design.

---

## Project Modules

| Module                | Description                                                                 |
|------------------------|-----------------------------------------------------------------------------|
| **business-logic**     | Contains domain models, enums, and core use cases. It is fully framework-agnostic and focuses on business rules only. |
| **entry-http**         | Exposes REST API endpoints and controllers. Maps requests to use cases and returns responses to clients. |
| **persistence-database** | Implements database access using Spring Data JPA. Responsible for entity mapping, repositories, and database interaction. |
| **initializer**        | Main Spring Boot entrypoint that wires everything together and starts the application. Also responsible for configuration and initial data loading. |

---

## Architecture Overview

- **Hexagonal Architecture (Ports and Adapters)**:
  - Cleanly separates core business logic from infrastructure.
  - Makes it easy to replace adapters (e.g., switch databases or APIs) without impacting core logic.

- **Modular Design**:
  - Each module has a clear responsibility, supporting better testability and maintainability.

- **Database Versioning with Flyway**:
  - Migration scripts versioned and automatically applied on startup.

---

## Features

- Book pricing logic based on:
  - Book type (NEW_RELEASES, REGULAR, OLD_EDITIONS)
  - Bundles (3+ books) and loyalty discounts
- Purchase processing and validation
- Customer loyalty tracking
- Quantity validation for purchases
- REST API with modular controllers and DTO mappers

---

## Tech Stack

- Java 17 + Maven
- Spring Boot
- Spring Data JPA
- H2 in-memory DB (PostgreSQL compatibility mode)
- Flyway for DB migration
- MapStruct
- Lombok
- JUnit + MockMvc for testing
- Docker (multi-stage)
- Postman Collection for API testing

---

## Docker

The project includes a multi-stage `Dockerfile` to reduce image size.

### Build and Run:

```bash
docker build -t sporty-api .
docker run -p 8080:8080 sporty-api
```

---

## ▶️ Running Locally

```bash
cd initializer
./mvnw clean spring-boot:run -pl initializer
```

Or from the root if configured:

```bash
mvn spring-boot:run -pl initializer
```

---

## Postman Support

You can import the Postman JSON collection to test all endpoints. It includes examples for:

- Book listing
- Purchase simulation
- Loyalty point checking

---

## Initial Data in H2

On application startup, the in-memory H2 database is loaded with:

- A few **books** of various types
- Several **customers**
- Initial **loyalty points** per customer

---

## Improvements for the Future

Given time constraints, here are suggestions to evolve the project:

- Add full CRUD endpoints for entities
- Implement pagination in list endpoints
- Create base repository classes to avoid code duplication
- Add caching for frequently accessed data
- Integrate messaging queue for async processing (e.g., RabbitMQ, Kafka)
- Use GraalVM to compile into a native image for fast startup
- Add metrics and APM (e.g., Prometheus + Grafana)
- Deploy with Cloud Run or AWS Fargate for scalability
- Include rate limiting, API keys, authentication
- Document with OpenAPI (Swagger)

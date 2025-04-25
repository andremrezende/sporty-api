# Online Bookstore Backend

This project implements a modular Java 17 backend for an online bookstore using Hexagonal Architecture, TDD, and Spring Boot.

## 📦 Modules
- `bookstore-domain`: Core domain logic and interfaces
- `bookstore-application`: Use case services
- `bookstore-adapters`: REST and persistence adapters
- `bookstore-infrastructure`: Spring Boot app, configuration, and entrypoint

## 🚀 Architecture Strategy
Hexagonal architecture with DDD principles. Core logic is isolated from frameworks and infrastructure concerns.

## 🔧 How to Run
### Requirements
- Java 17+
- Docker & Docker Compose

### Build & Run with Docker
```bash
docker build -t bookstore-app .
docker run -p 8080:8080 bookstore-app
```

### Run with Maven
```bash
./mvnw clean install
java -jar bookstore-infrastructure/target/*.jar
```

## 🧪 Testing
```bash
./mvnw clean verify
open target/surefire-reports/index.html
```

## 📌 API Endpoints
- `GET /api/v1/books`: List all books
- `POST /api/v1/books`: Add new book

## ⚙️ Deployment Strategies
- **Canary**
- **Blue/Green**
- **Rolling Deploy**

## 💡 Suggestions for Improvement
- Use Redis for caching
- Add async purchase processing
- Deploy with GraalVM native image
- Add Prometheus + Grafana observability
- Use Cloud Run or AWS Fargate for scalability

# CashViewer 💰

A web application for managing personal finances and tracking expenses.

## 📋 Project Description

**CashViewer** is a RESTful application built with Spring Boot that allows users to:
- 📊 Track their expenses and income
- 🏷️ Organize transactions by categories
- 🔐 Securely store financial data
- 📈 Analyze their financial habits

> ⚠️ **Status**: The project is under active development. Current version includes category management functionality and comprehensive test suite.
## Category Module Roadmap

### Completed
- Create category
- Update category
- Get category by id
- Get all categories

### In Progress
- Delete category
- Filter categories by type

### Planned
- Subcategories
- Category hierarchy
- Category analytics
## 🛠️ Technology Stack

### Backend
- **Java** 21
- **Spring Boot** 4.0.6
- **Spring Security** — authentication and authorization
- **Spring Data JPA** — database operations
- **Spring Validation** — data validation

### Database
- **PostgreSQL** 16

### Additional Libraries
- **JWT (java-jwt 4.0.0)** — authentication tokens
- **MapStruct 1.6.3** — object mapping (DTO ↔ Entity)
- **Lombok 1.18.36** — code generation
- **Flyway 12.7.0** — database migrations
- **Swagger/OpenAPI 2.8.5** — API documentation

### Testing
- **JUnit 5** (included in Spring Boot Test)
- **Spring Security Test**

## 📦 Requirements

- **Java 21+**
- **Maven 3.6+** (or use the built-in Maven Wrapper)
- **Docker & Docker Compose** (for PostgreSQL)

# Example REST API - Multi-Module Maven Project

A production-ready REST API example built with Spring Boot 3.x and Java 17+, following the multi-module architecture pattern inspired by best practices.

## 📁 Project Structure

```
example-rest-api/
├── pom.xml                          # Parent POM with dependency management
├── mvnw / mvnw.cmd                  # Maven Wrapper
├── .mvn/wrapper/                    # Maven Wrapper files
├── .gitignore
│
├── api/                             # REST API Layer
│   ├── pom.xml
│   ├── src/main/
│   │   ├── java/com/example/api/
│   │   │   ├── ApiApplication.java  # Spring Boot entry point
│   │   │   ├── controller/          # REST Controllers
│   │   │   │   ├── PostController.java
│   │   │   │   └── UserController.java
│   │   │   ├── dto/                 # Data Transfer Objects
│   │   │   │   ├── PostDto.java
│   │   │   │   ├── UserDto.java
│   │   │   │   ├── CommentDto.java
│   │   │   │   └── CreatePostRequest.java
│   │   │   └── exception/           # Exception handling
│   │   │       └── GlobalExceptionHandler.java
│   │   └── resources/
│   │       └── application.yml      # Application configuration
│   └── src/test/                    # Integration tests
│
├── service/                        # Service Layer (Business Logic)
│   ├── pom.xml
│   └── src/main/java/com/example/
│       ├── service/
│       │   ├── PostService.java     # Service interface
│       │   └── UserService.java
│       ├── service/impl/
│       │   ├── PostServiceImpl.java
│       │   └── UserServiceImpl.java
│       └── model/                   # Domain models
│           ├── Post.java
│           ├── User.java
│           └── Comment.java
│
├── persistence/                    # Persistence Layer (JPA)
│   ├── pom.xml
│   └── src/main/java/com/example/
│       ├── persistence/
│       │   ├── entity/              # JPA Entities
│       │   │   ├── PostEntity.java
│       │   │   └── UserEntity.java
│       │   └── repository/          # Spring Data Repositories
│       │       ├── PostRepository.java
│       │       └── UserRepository.java
│
└── integration/                    # Integration Layer (External API)
    ├── pom.xml
    └── src/main/java/com/example/
        ├── integration/
        │   ├── client/              # HTTP Clients
        │   │   ├── JsonPlaceholderClient.java
        │   │   └── JsonPlaceholderClientImpl.java
        │   ├── config/
        │   │   └── WebClientConfig.java
        │   └── mapper/
        │       └── JsonPlaceholderMapper.java
```

## 🏗️ Architecture

This project follows a **layered architecture** with clear separation of concerns:

### Module Dependencies

```
api ─────► service ─────► persistence
                │
                ▼
            integration
```

1. **api** - REST controllers, DTOs, and Spring Boot application entry point
2. **service** - Business logic, domain models, service interfaces and implementations
3. **persistence** - JPA entities and Spring Data repositories (H2 in-memory database)
4. **integration** - HTTP client for external APIs (JSONPlaceholder)

### External API Integration

The project integrates with [JSONPlaceholder](https://jsonplaceholder.typicode.com) - a free fake REST API for testing and prototyping.

## 🚀 Build Instructions

### Prerequisites

- Java 17 or higher
- Maven 3.9+ (or use the included Maven Wrapper)

### Build the Project

```bash
# Clone or navigate to the project directory
cd example-rest-api

# Build all modules
mvn clean install

# Or skip tests if you just want to compile
mvn clean install -DskipTests
```

## ▶️ Run Instructions

### Run the Application

```bash
# From the api module directory
cd api
mvn spring-boot:run
```

Or from the root directory:

```bash
mvn spring-boot:run -pl api
```

The application will start on `http://localhost:8080`

### Run Tests

```bash
# Run all tests
mvn test

# Run only integration tests in api module
cd api
```

## 📡 API Endpoints

mvn test
### Posts

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/posts` | Get all posts from local database |
| GET | `/api/posts/{id}` | Get a post by ID |
| GET | `/api/posts/user/{userId}` | Get posts by user ID |
| POST | `/api/posts` | Create a new post |
| PUT | `/api/posts/{id}` | Update a post |
| DELETE | `/api/posts/{id}` | Delete a post |
| GET | `/api/posts/external` | Get all posts from JSONPlaceholder |
| GET | `/api/posts/external/{id}` | Get a post from JSONPlaceholder |
| GET | `/api/posts/{id}/comments` | Get comments for a post |
| GET | `/api/posts/external/{id}/comments` | Get comments from JSONPlaceholder |

### Users

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/users` | Get all users from local database |
| GET | `/api/users/{id}` | Get a user by ID |
| GET | `/api/users/external` | Get all users from JSONPlaceholder |
| GET | `/api/users/external/{id}` | Get a user from JSONPlaceholder |

### Actuator

| Endpoint | Description |
|----------|-------------|
| `/actuator/health` | Health check |
| `/actuator/info` | Application info |

## 🧪 Testing the External API Integration

### Example: Get External Posts

```bash
curl http://localhost:8080/api/posts/external
```

Response:
```json
[
  {
    "id": 1,
    "userId": 1,
    "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
    "body": "..."
  },
  ...
]
```

### Example: Get External Users

```bash
curl http://localhost:8080/api/users/external
```

### Example: Create a Post

```bash
curl -X POST http://localhost:8080/api/posts \
  -H "Content-Type: application/json" \
  -d '{
    "userId": 1,
    "title": "My New Post",
    "body": "This is the body of my post"
  }'
```

### Example: Get Comments for a Post

```bash
curl http://localhost:8080/api/posts/external/1/comments
```

## ⚙️ Configuration

The application uses an H2 in-memory database. Default configuration in `api/src/main/resources/application.yml`:

```yaml
server:
  port: 8080

spring:
  datasource:
    url: jdbc:h2:mem:testdb
    driver-class-name: org.h2.Driver
  h2:
    console:
      enabled: true
      path: /h2-console
  jpa:
    hibernate:
      ddl-auto: create-drop
```

## 📝 Notes

- The H2 console is available at `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:testdb`
- No password required (empty)
- External API: [JSONPlaceholder](https://jsonplaceholder.typicode.com)

## 🛠️ Technologies Used

- **Spring Boot 3.3.5**
- **Java 17+**
- **Maven**
- **Spring Data JPA** (with H2 database)
- **Spring WebFlux** (for WebClient)
- **Lombok** (for reducing boilerplate)
- **MapStruct** (for DTO mapping)
- **H2 Database** (in-memory)
- **REST-assured** (for testing)

---

*This project is inspired by the [jnie/multi-module-architecture](https://github.com/jnie/multi-module-architecture) repository.*

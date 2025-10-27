# UberManager Project Structure

```
JAVA DBMS Project/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── ubermanager/
│   │   │           ├── config/
│   │   │           │   └── WebConfig.java
│   │   │           ├── controller/
│   │   │           │   ├── DriverController.java
│   │   │           │   ├── PaymentController.java
│   │   │           │   ├── RatingController.java
│   │   │           │   ├── RideController.java
│   │   │           │   ├── UserController.java
│   │   │           │   └── WebController.java
│   │   │           ├── dto/
│   │   │           │   ├── PaymentRequestDTO.java
│   │   │           │   ├── RatingRequestDTO.java
│   │   │           │   └── RideRequestDTO.java
│   │   │           ├── exception/
│   │   │           │   └── GlobalExceptionHandler.java
│   │   │           ├── model/
│   │   │           │   ├── Driver.java
│   │   │           │   ├── Payment.java
│   │   │           │   ├── Rating.java
│   │   │           │   ├── Ride.java
│   │   │           │   └── User.java
│   │   │           ├── repository/
│   │   │           │   ├── DriverRepository.java
│   │   │           │   ├── PaymentRepository.java
│   │   │           │   ├── RatingRepository.java
│   │   │           │   ├── RideRepository.java
│   │   │           │   └── UserRepository.java
│   │   │           ├── service/
│   │   │           │   ├── DriverService.java
│   │   │           │   ├── PaymentService.java
│   │   │           │   ├── RatingService.java
│   │   │           │   ├── RideService.java
│   │   │           │   └── UserService.java
│   │   │           └── UberManagerApplication.java
│   │   │
│   │   └── resources/
│   │       ├── templates/
│   │       │   ├── dashboard.html
│   │       │   ├── drivers.html
│   │       │   ├── payments.html
│   │       │   ├── ratings.html
│   │       │   ├── rides.html
│   │       │   └── users.html
│   │       ├── application.properties
│   │       └── data.sql
│   │
│   └── test/
│       └── java/
│           └── com/
│               └── ubermanager/
│
├── .gitignore
├── pom.xml
├── README.md
├── SETUP_GUIDE.md
├── PROJECT_STRUCTURE.md
├── run.bat
├── test-api.ps1
└── UberManager-Postman-Collection.json
```

## Package Description

### 1. **config/** - Configuration Classes
- `WebConfig.java` - CORS and web MVC configuration

### 2. **controller/** - REST & Web Controllers
- `UserController.java` - REST API for user management
- `DriverController.java` - REST API for driver management
- `RideController.java` - REST API for ride operations
- `PaymentController.java` - REST API for payment processing
- `RatingController.java` - REST API for rating management
- `WebController.java` - Thymeleaf web pages controller

### 3. **dto/** - Data Transfer Objects
- `RideRequestDTO.java` - DTO for ride requests
- `PaymentRequestDTO.java` - DTO for payment requests
- `RatingRequestDTO.java` - DTO for rating submissions

### 4. **exception/** - Exception Handlers
- `GlobalExceptionHandler.java` - Global exception handling

### 5. **model/** - JPA Entities
- `User.java` - User/Rider entity
- `Driver.java` - Driver entity
- `Ride.java` - Ride entity
- `Payment.java` - Payment entity
- `Rating.java` - Rating entity

### 6. **repository/** - Data Access Layer
- `UserRepository.java` - User data access
- `DriverRepository.java` - Driver data access
- `RideRepository.java` - Ride data access
- `PaymentRepository.java` - Payment data access
- `RatingRepository.java` - Rating data access

### 7. **service/** - Business Logic Layer
- `UserService.java` - User business logic
- `DriverService.java` - Driver business logic
- `RideService.java` - Ride business logic
- `PaymentService.java` - Payment business logic
- `RatingService.java` - Rating business logic

### 8. **resources/** - Application Resources
- **templates/** - Thymeleaf HTML templates
- **application.properties** - Spring Boot configuration
- **data.sql** - Sample data initialization

## Key Features by Layer

### Presentation Layer (Controllers)
- RESTful API endpoints
- Web UI with Thymeleaf
- Request/Response handling
- Cross-Origin Resource Sharing (CORS)

### Business Layer (Services)
- Transaction management
- Business rules validation
- Complex calculations (fare, ratings)
- State management (ride status)

### Data Layer (Repositories)
- CRUD operations
- Custom queries
- Aggregation functions
- Relationship management

### Data Model (Entities)
- JPA annotations
- Relationships (One-to-Many, Many-to-One, One-to-One)
- Validation rules
- Lifecycle callbacks

## Database Relationships

```
User (1) ----< (M) Ride (M) >---- (1) Driver
                    |
                    | (1)
                    |
                (1) +---- Payment
                    |
                (1) +---- Rating
```

## Technology Stack

- **Framework**: Spring Boot 3.2.0
- **ORM**: Hibernate / Spring Data JPA
- **Database**: MySQL 8.0
- **Template Engine**: Thymeleaf
- **Build Tool**: Maven
- **Utilities**: Lombok, Validation API

## File Counts

- Java Classes: 24
- HTML Templates: 6
- Configuration Files: 3
- Documentation: 3
- Scripts: 2
- Total Files: ~38

This structure follows the standard Spring Boot layered architecture pattern with clear separation of concerns.

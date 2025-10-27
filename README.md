# UberManager - Ride Management System

A comprehensive ride management system built with Spring Boot, MySQL, and Thymeleaf.

## 🚀 Features

- **User Management**: Add, view, update, and delete users (riders)
- **Driver Management**: Manage drivers with vehicle information and ratings
- **Ride Management**: Complete ride lifecycle from request to completion
- **Payment Processing**: Handle payments with multiple payment methods
- **Rating System**: Rate drivers and maintain average ratings
- **Beautiful Dashboard**: Modern, animated UI with real-time statistics

## 🛠️ Tech Stack

- **Backend**: Spring Boot 3.2.0
- **Database**: MySQL
- **ORM**: Spring Data JPA / Hibernate
- **Frontend**: Thymeleaf, HTML5, CSS3, JavaScript
- **Build Tool**: Maven
- **Utilities**: Lombok, Validation

## 📋 Prerequisites

- Java 17 or higher
- MySQL 8.0 or higher
- Maven 3.6 or higher

## ⚙️ Configuration

The application is configured to connect to MySQL with the following settings:

```properties
Database: ubermanager (auto-created)
Host: localhost:3306
Username: root
Password: Abi@6369487158
```

You can modify these settings in `src/main/resources/application.properties`

## 🚀 Getting Started

### 1. Clone the repository
```bash
cd "d:\JAVA DBMS Project"
```

### 2. Build the project
```bash
mvn clean install
```

### 3. Run the application
```bash
mvn spring-boot:run
```

### 4. Access the application

- **Web Dashboard**: http://localhost:8080
- **API Base URL**: http://localhost:8080/api

## 📡 REST API Endpoints

### Users
- `POST /api/users` - Create new user
- `GET /api/users` - Get all users
- `GET /api/users/{id}` - Get user by ID
- `PUT /api/users/{id}` - Update user
- `DELETE /api/users/{id}` - Delete user

### Drivers
- `POST /api/drivers` - Create new driver
- `GET /api/drivers` - Get all drivers
- `GET /api/drivers/{id}` - Get driver by ID
- `GET /api/drivers/top-rated` - Get top rated drivers
- `PUT /api/drivers/{id}` - Update driver
- `DELETE /api/drivers/{id}` - Delete driver

### Rides
- `POST /api/rides` - Request a new ride
- `GET /api/rides` - Get all rides
- `GET /api/rides/{id}` - Get ride by ID
- `GET /api/rides/user/{userId}` - Get rides by user
- `GET /api/rides/driver/{driverId}` - Get rides by driver
- `GET /api/rides/status/{status}` - Get rides by status
- `PUT /api/rides/{rideId}/accept/{driverId}` - Accept ride
- `PUT /api/rides/{rideId}/start` - Start ride
- `PUT /api/rides/{rideId}/complete` - Complete ride
- `PUT /api/rides/{rideId}/cancel` - Cancel ride

### Payments
- `POST /api/payments` - Create payment
- `GET /api/payments` - Get all payments
- `GET /api/payments/{id}` - Get payment by ID
- `GET /api/payments/ride/{rideId}` - Get payment by ride ID
- `PUT /api/payments/{paymentId}/process` - Process payment

### Ratings
- `POST /api/ratings` - Create rating
- `GET /api/ratings` - Get all ratings
- `GET /api/ratings/{id}` - Get rating by ID
- `GET /api/ratings/driver/{driverId}` - Get ratings for driver
- `GET /api/ratings/driver/{driverId}/average` - Get average rating for driver

## 📊 Database Schema

### Users Table
- user_id (PK)
- name, email, contact_no, join_date

### Drivers Table
- driver_id (PK)
- name, email, contact_no, vehicle_no, rating_avg, join_date

### Rides Table
- ride_id (PK)
- user_id (FK), driver_id (FK)
- pickup_location, drop_location, request_time, start_time, end_time, fare, status

### Payments Table
- payment_id (PK)
- ride_id (FK), amount, payment_method, payment_date, status

### Ratings Table
- rating_id (PK)
- ride_id (FK), user_id (FK), driver_id (FK)
- rating, review, rating_date

## 🎨 UI Features

- Modern gradient design
- Smooth animations
- Responsive layout
- Real-time statistics dashboard
- Interactive tables with hover effects
- Status badges with color coding
- Floating action buttons

## 📝 Sample API Requests

### Create a User
```json
POST /api/users
{
  "name": "John Doe",
  "email": "john@example.com",
  "contactNo": "+91 9876543210"
}
```

### Create a Driver
```json
POST /api/drivers
{
  "name": "Mike Driver",
  "email": "mike@example.com",
  "contactNo": "+91 9876543211",
  "vehicleNo": "KA-01-AB-1234"
}
```

### Request a Ride
```json
POST /api/rides
{
  "user": {
    "userId": 1
  },
  "pickupLocation": "Koramangala, Bangalore",
  "dropLocation": "Electronic City, Bangalore"
}
```

### Create Payment
```json
POST /api/payments
{
  "ride": {
    "rideId": 1
  },
  "paymentMethod": "CARD",
  "status": "PAID"
}
```

### Rate a Driver
```json
POST /api/ratings
{
  "ride": {
    "rideId": 1
  },
  "rating": 5,
  "review": "Excellent service! Very professional driver."
}
```

## 🔧 Development

To run in development mode with auto-reload:

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

## 📦 Project Structure

```
com.ubermanager
├── controller/          # REST Controllers and Web Controllers
├── service/            # Business logic layer
├── repository/         # Data access layer
├── model/             # Entity models
└── UberManagerApplication.java
```

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## 📄 License

This project is licensed under the MIT License.

## 👨‍💻 Author

Built with ❤️ for modern ride management

---

**Happy Coding! 🚗💨**

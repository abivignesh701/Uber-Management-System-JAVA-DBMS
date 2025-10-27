## Step-by-Step Guide to Run UberManager

### Prerequisites Check
1. ✅ Java 17+ installed
2. ✅ MySQL 8.0+ installed and running
3. ✅ Maven installed
4. ✅ Port 8080 available

### Quick Start

#### Step 1: Verify MySQL Connection
Open MySQL and verify it's running:
```sql
mysql -u root -p
-- Enter password: Abi@6369487158
```

#### Step 2: Navigate to Project Directory
```bash
cd "d:\JAVA DBMS Project"
```

#### Step 3: Build the Project
```bash
mvn clean install
```

#### Step 4: Run the Application
```bash
mvn spring-boot:run
```

#### Step 5: Access the Application
Open your browser and navigate to:
- **Dashboard**: http://localhost:8080
- **API Documentation**: Use the provided Postman collection

### Testing the Application

#### Using the Web UI
1. Go to http://localhost:8080
2. Navigate through different sections:
   - Users: View all riders
   - Drivers: View all drivers with ratings
   - Rides: See all ride requests and their status
   - Payments: Track payment transactions
   - Ratings: View driver ratings and reviews

#### Using Postman
1. Import `UberManager-Postman-Collection.json`
2. Test the following workflow:

**Step 1: Create a User**
```
POST http://localhost:8080/api/users
Body:
{
  "name": "Test User",
  "email": "test@example.com",
  "contactNo": "+91 9999999999"
}
```

**Step 2: Create a Driver**
```
POST http://localhost:8080/api/drivers
Body:
{
  "name": "Test Driver",
  "email": "driver@example.com",
  "contactNo": "+91 8888888888",
  "vehicleNo": "KA-01-TEST-1234"
}
```

**Step 3: Request a Ride**
```
POST http://localhost:8080/api/rides
Body:
{
  "user": {
    "userId": 1
  },
  "pickupLocation": "Location A",
  "dropLocation": "Location B"
}
```

**Step 4: Accept the Ride**
```
PUT http://localhost:8080/api/rides/1/accept/1
```

**Step 5: Start the Ride**
```
PUT http://localhost:8080/api/rides/1/start
```

**Step 6: Complete the Ride**
```
PUT http://localhost:8080/api/rides/1/complete
```

**Step 7: Make Payment**
```
POST http://localhost:8080/api/payments
Body:
{
  "ride": {
    "rideId": 1
  },
  "paymentMethod": "CARD",
  "status": "PAID"
}
```

**Step 8: Rate the Driver**
```
POST http://localhost:8080/api/ratings
Body:
{
  "ride": {
    "rideId": 1
  },
  "rating": 5,
  "review": "Excellent service!"
}
```

### Troubleshooting

**Problem**: Cannot connect to MySQL
**Solution**: 
- Check if MySQL is running
- Verify credentials in application.properties
- Check if port 3306 is available

**Problem**: Port 8080 already in use
**Solution**: 
- Change port in application.properties: `server.port=8081`

**Problem**: Build fails
**Solution**: 
- Run `mvn clean` first
- Check Java version: `java -version`
- Ensure Maven is properly configured

### Sample Data
The application comes with sample data in `data.sql`:
- 5 Users
- 5 Drivers
- 7 Rides (various statuses)
- 4 Payments
- 4 Ratings

### Next Steps
1. Explore the beautiful dashboard at http://localhost:8080
2. Test all CRUD operations using the UI
3. Experiment with the REST API using Postman
4. Check the database to see real-time updates
5. Monitor application logs for debugging

### Features to Explore
- ✨ Animated dashboard with statistics
- 🚗 Real-time ride status tracking
- 💳 Multiple payment methods
- ⭐ Driver rating system
- 📊 Revenue tracking
- 🎨 Modern, responsive UI

Enjoy your UberManager application! 🎉

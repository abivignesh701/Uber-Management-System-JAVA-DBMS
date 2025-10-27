# 🚗 UberManager - Quick Start Guide

## ⚡ FASTEST WAY TO RUN

### Windows Users (Easiest):
1. **Double-click `run.bat`** in the project folder
2. Wait for the application to start
3. Open browser: **http://localhost:8080**

That's it! 🎉

---

## 🔧 Manual Start (Alternative)

### Step 1: Open PowerShell/Command Prompt
```powershell
cd "d:\JAVA DBMS Project"
```

### Step 2: Run the Application
```powershell
mvn spring-boot:run
```

### Step 3: Access Dashboard
Open: **http://localhost:8080**

---

## 📊 What You'll See

### Beautiful Animated Dashboard
- **Total Users** - Number of registered riders
- **Total Drivers** - Number of registered drivers
- **Total Rides** - All ride requests
- **Completed Rides** - Successfully completed journeys
- **Total Revenue** - Earnings from all rides

### Navigation Menu
- 🏠 **Dashboard** - Overview and statistics
- 👥 **Users** - Manage riders
- 🚗 **Drivers** - Manage drivers
- 🛣️ **Rides** - Track all rides
- 💳 **Payments** - Payment transactions
- ⭐ **Ratings** - Driver ratings and reviews

---

## 🧪 Testing the Application

### Option 1: Use the Web UI
1. Go to **http://localhost:8080**
2. Navigate through the menu
3. View all data in beautiful tables

### Option 2: Test REST APIs
Run the PowerShell test script:
```powershell
.\test-api.ps1
```

### Option 3: Use Postman
1. Import `UberManager-Postman-Collection.json`
2. Test all API endpoints
3. See responses in real-time

---

## 🎯 Complete Workflow Example

### 1. View Dashboard
- **URL**: http://localhost:8080
- See all statistics

### 2. Check Users
- **URL**: http://localhost:8080/users
- View all registered riders

### 3. Check Drivers
- **URL**: http://localhost:8080/drivers
- View all drivers with ratings

### 4. View Rides
- **URL**: http://localhost:8080/rides
- See ride history with status

### 5. Check Payments
- **URL**: http://localhost:8080/payments
- View payment transactions

### 6. See Ratings
- **URL**: http://localhost:8080/ratings
- View driver ratings and reviews

---

## 🔌 REST API Quick Reference

### Base URL
```
http://localhost:8080/api
```

### Key Endpoints

#### Create User (Rider)
```http
POST /api/users
Content-Type: application/json

{
  "name": "John Doe",
  "email": "john@example.com",
  "contactNo": "+91 9876543210"
}
```

#### Create Driver
```http
POST /api/drivers
Content-Type: application/json

{
  "name": "Mike Driver",
  "email": "mike@example.com",
  "contactNo": "+91 8888888888",
  "vehicleNo": "KA-01-AB-1234"
}
```

#### Request a Ride
```http
POST /api/rides
Content-Type: application/json

{
  "user": { "userId": 1 },
  "pickupLocation": "Koramangala",
  "dropLocation": "Electronic City"
}
```

#### Accept Ride
```http
PUT /api/rides/{rideId}/accept/{driverId}
```

#### Complete Ride
```http
PUT /api/rides/{rideId}/complete
```

#### Make Payment
```http
POST /api/payments
Content-Type: application/json

{
  "ride": { "rideId": 1 },
  "paymentMethod": "CARD",
  "status": "PAID"
}
```

#### Rate Driver
```http
POST /api/ratings
Content-Type: application/json

{
  "ride": { "rideId": 1 },
  "rating": 5,
  "review": "Excellent service!"
}
```

---

## ⚙️ Configuration

### Database Settings
```properties
Database: ubermanager (auto-created)
Host: localhost:3306
Username: root
Password: Abi@6369487158
```

### Application Port
```properties
Port: 8080
URL: http://localhost:8080
```

---

## 🎨 UI Features

✨ **Modern Gradient Design**
- Purple-Blue gradient theme
- Smooth animations
- Glass-morphism effects

📱 **Responsive Layout**
- Works on desktop, tablet, mobile
- Adaptive grid system
- Touch-friendly

🎭 **Interactive Elements**
- Hover effects on tables
- Animated statistics cards
- Floating action buttons
- Status badges with colors

---

## 🛠️ Troubleshooting

### Problem: Port 8080 is in use
**Solution**: Change port in `application.properties`:
```properties
server.port=8081
```

### Problem: Cannot connect to MySQL
**Solution**: 
1. Check if MySQL is running
2. Verify credentials
3. Create database manually:
```sql
CREATE DATABASE ubermanager;
```

### Problem: Build fails
**Solution**: 
```powershell
mvn clean install -U
```

---

## 📚 Documentation Files

- **README.md** - Complete project overview
- **SETUP_GUIDE.md** - Detailed setup instructions
- **PROJECT_STRUCTURE.md** - Code organization
- **QUICK_START.md** - This file!
- **database-schema.sql** - Database structure

---

## 🎯 Next Steps

1. ✅ **Explore the Dashboard** - See real-time statistics
2. ✅ **Test CRUD Operations** - Create, Read, Update, Delete
3. ✅ **Complete a Ride Flow** - From request to rating
4. ✅ **Check API Documentation** - Test with Postman
5. ✅ **Customize UI** - Modify templates in `src/main/resources/templates/`

---

## 💡 Pro Tips

1. **Use the Dashboard** - It's the fastest way to visualize data
2. **Test with Postman** - Import the collection for quick testing
3. **Check Logs** - Console shows all SQL queries
4. **Sample Data** - Auto-loaded from `data.sql`
5. **Hot Reload** - Use Spring DevTools for instant changes

---

## 🎉 Success Indicators

You'll know it's working when:
- ✅ Application starts without errors
- ✅ Dashboard loads at http://localhost:8080
- ✅ You see statistics on the dashboard
- ✅ Tables show sample data
- ✅ Navigation menu works
- ✅ API calls return JSON responses

---

## 📞 Support

If you encounter issues:
1. Check application logs in console
2. Verify MySQL is running
3. Ensure Java 17+ is installed
4. Check Maven is configured
5. Review `SETUP_GUIDE.md` for detailed help

---

**Made with ❤️ for Modern Ride Management**

🚗 Happy Coding! 💨

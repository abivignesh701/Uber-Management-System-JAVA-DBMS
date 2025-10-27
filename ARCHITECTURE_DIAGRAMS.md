# 🚗 UberManager - System Architecture & Flow Diagrams

## 🏗️ System Architecture

```
┌─────────────────────────────────────────────────────────────┐
│                     CLIENT LAYER                             │
│  ┌─────────────┐          ┌──────────────┐                 │
│  │  Web Browser│          │  REST Client │                 │
│  │  (Thymeleaf)│          │  (Postman)   │                 │
│  └──────┬──────┘          └──────┬───────┘                 │
└─────────┼─────────────────────────┼─────────────────────────┘
          │                         │
          ├─────────────────────────┤
          │    HTTP/HTTPS           │
          └─────────────┬───────────┘
                        │
┌─────────────────────────────────────────────────────────────┐
│                  PRESENTATION LAYER                          │
│  ┌──────────────────┐      ┌──────────────────┐            │
│  │  WebController   │      │  REST Controllers│            │
│  │  (Thymeleaf)     │      │  (JSON APIs)     │            │
│  │  - dashboard()   │      │  - UserController│            │
│  │  - users()       │      │  - DriverCtrl    │            │
│  │  - drivers()     │      │  - RideCtrl      │            │
│  │  - rides()       │      │  - PaymentCtrl   │            │
│  │  - payments()    │      │  - RatingCtrl    │            │
│  └─────────┬────────┘      └─────────┬────────┘            │
└────────────┼─────────────────────────┼──────────────────────┘
             │                         │
             └────────────┬────────────┘
                          │
┌─────────────────────────────────────────────────────────────┐
│                   SERVICE LAYER                              │
│  ┌────────────┐  ┌────────────┐  ┌────────────┐            │
│  │UserService │  │DriverSvc   │  │ RideSvc    │            │
│  └─────┬──────┘  └─────┬──────┘  └─────┬──────┘            │
│        │               │               │                    │
│  ┌─────┴──────┐  ┌─────┴──────┐                            │
│  │PaymentSvc  │  │ RatingSvc  │                            │
│  └─────┬──────┘  └─────┬──────┘                            │
└────────┼───────────────┼─────────────────────────────────────┘
         │               │
         └───────┬───────┘
                 │
┌─────────────────────────────────────────────────────────────┐
│                 REPOSITORY LAYER                             │
│  ┌────────────┐  ┌────────────┐  ┌────────────┐            │
│  │UserRepo    │  │DriverRepo  │  │ RideRepo   │            │
│  └─────┬──────┘  └─────┬──────┘  └─────┬──────┘            │
│        │               │               │                    │
│  ┌─────┴──────┐  ┌─────┴──────┐                            │
│  │PaymentRepo │  │ RatingRepo │                            │
│  └─────┬──────┘  └─────┬──────┘                            │
└────────┼───────────────┼─────────────────────────────────────┘
         │               │
         └───────┬───────┘
                 │  JPA/Hibernate
┌─────────────────────────────────────────────────────────────┐
│                    DATA LAYER                                │
│  ┌─────────────────────────────────────────────────────┐   │
│  │              MySQL Database                          │   │
│  │  ┌───────┐  ┌────────┐  ┌───────┐  ┌─────────┐     │   │
│  │  │ users │  │drivers │  │ rides │  │payments │     │   │
│  │  └───────┘  └────────┘  └───────┘  └─────────┘     │   │
│  │                    ┌─────────┐                       │   │
│  │                    │ ratings │                       │   │
│  │                    └─────────┘                       │   │
│  └─────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────┘
```

---

## 📊 Database Entity-Relationship Diagram

```
┌──────────────┐
│    USERS     │
├──────────────┤
│ user_id (PK) │
│ name         │
│ email        │
│ contact_no   │
│ join_date    │
└───────┬──────┘
        │
        │ 1:M
        │
        ▼
┌──────────────┐         ┌──────────────┐
│    RIDES     │ M:1     │   DRIVERS    │
├──────────────┤────────▶├──────────────┤
│ ride_id (PK) │         │driver_id (PK)│
│ user_id (FK) │         │ name         │
│driver_id(FK) │         │ email        │
│pickup_loc    │         │ contact_no   │
│drop_location │         │ vehicle_no   │
│request_time  │         │ rating_avg   │
│start_time    │         │ join_date    │
│end_time      │         └───────┬──────┘
│ fare         │                 │
│ status       │                 │ 1:M
└───────┬──────┘                 │
        │                        │
        │ 1:1                    ▼
        │              ┌──────────────┐
        ├─────────────▶│   RATINGS    │
        │              ├──────────────┤
        │              │rating_id (PK)│
        │              │ ride_id (FK) │
        │              │ user_id (FK) │
        │              │driver_id(FK) │
        │              │ rating (1-5) │
        │              │ review       │
        │              │ rating_date  │
        │              └──────────────┘
        │
        │ 1:1
        │
        ▼
┌──────────────┐
│   PAYMENTS   │
├──────────────┤
│payment_id(PK)│
│ ride_id (FK) │
│ amount       │
│payment_method│
│payment_date  │
│ status       │
└──────────────┘
```

---

## 🔄 Complete Ride Flow Diagram

```
┌─────────────┐
│   START     │
└──────┬──────┘
       │
       ▼
┌─────────────────────┐
│ User Requests Ride  │
│ POST /api/rides     │
└──────┬──────────────┘
       │
       ▼
┌─────────────────────┐
│ Ride Status:        │
│   REQUESTED         │
│ Auto-calculate fare │
└──────┬──────────────┘
       │
       ▼
┌─────────────────────┐
│ Driver Accepts      │
│ PUT /accept/{id}    │
└──────┬──────────────┘
       │
       ▼
┌─────────────────────┐
│ Ride Status:        │
│   ACCEPTED          │
│ Driver assigned     │
└──────┬──────────────┘
       │
       ▼
┌─────────────────────┐
│ Driver Starts       │
│ PUT /start          │
└──────┬──────────────┘
       │
       ▼
┌─────────────────────┐
│ Ride Status:        │
│   IN_PROGRESS       │
│ Start time recorded │
└──────┬──────────────┘
       │
       ▼
┌─────────────────────┐
│ Driver Completes    │
│ PUT /complete       │
└──────┬──────────────┘
       │
       ▼
┌─────────────────────┐
│ Ride Status:        │
│   COMPLETED         │
│ End time recorded   │
└──────┬──────────────┘
       │
       ├──────────────────────┐
       │                      │
       ▼                      ▼
┌─────────────────┐  ┌──────────────────┐
│ Payment Process │  │  Rate Driver     │
│ POST /payments  │  │  POST /ratings   │
└─────────┬───────┘  └────────┬─────────┘
          │                   │
          ▼                   ▼
┌─────────────────┐  ┌──────────────────┐
│ Payment: PAID   │  │ Rating: 1-5 ⭐   │
│ Amount: ₹XXX    │  │ Review: "..."    │
└─────────────────┘  └────────┬─────────┘
                              │
                              ▼
                     ┌──────────────────┐
                     │ Update Driver    │
                     │ Average Rating   │
                     └────────┬─────────┘
                              │
                              ▼
                     ┌──────────────────┐
                     │   END            │
                     └──────────────────┘
```

---

## 🎯 API Request-Response Flow

```
┌─────────────┐
│   CLIENT    │
│ (Browser/   │
│  Postman)   │
└──────┬──────┘
       │
       │ HTTP Request
       │ POST /api/rides
       │ { user: {userId: 1}, pickup: "A", drop: "B" }
       │
       ▼
┌──────────────────┐
│ RideController   │
│ @PostMapping     │
│ @RequestBody     │
└──────┬───────────┘
       │
       │ Validate
       ▼
┌──────────────────┐
│  RideService     │
│ requestRide()    │
│ - Get user       │
│ - Calculate fare │
│ - Set status     │
└──────┬───────────┘
       │
       │ JPA Save
       ▼
┌──────────────────┐
│  RideRepository  │
│ save(ride)       │
└──────┬───────────┘
       │
       │ SQL INSERT
       ▼
┌──────────────────┐
│  MySQL Database  │
│ INSERT INTO rides│
└──────┬───────────┘
       │
       │ Return Entity
       ▼
┌──────────────────┐
│ HTTP Response    │
│ Status: 201      │
│ {                │
│   rideId: 1,     │
│   fare: 350,     │
│   status: "REQ"  │
│ }                │
└──────┬───────────┘
       │
       ▼
┌──────────────────┐
│   CLIENT         │
│ Receives JSON    │
└──────────────────┘
```

---

## 🎨 UI Component Flow

```
┌──────────────────────────────────────────┐
│          Browser: localhost:8080          │
└────────────────┬─────────────────────────┘
                 │
                 ▼
┌──────────────────────────────────────────┐
│         WebController.dashboard()         │
│  - getTotalUsers()                        │
│  - getTotalDrivers()                      │
│  - getTotalRides()                        │
│  - getCompletedRides()                    │
│  - getTotalRevenue()                      │
└────────────────┬─────────────────────────┘
                 │
                 ▼
┌──────────────────────────────────────────┐
│         Thymeleaf Engine                  │
│  Merges data with template                │
└────────────────┬─────────────────────────┘
                 │
                 ▼
┌──────────────────────────────────────────┐
│         dashboard.html                    │
│  ┌────────────────────────────────────┐  │
│  │  📊 Statistics Cards               │  │
│  │  - Total Users: ${totalUsers}      │  │
│  │  - Total Drivers: ${totalDrivers}  │  │
│  │  - Total Rides: ${totalRides}      │  │
│  │  - Revenue: ₹${totalRevenue}       │  │
│  └────────────────────────────────────┘  │
│  ┌────────────────────────────────────┐  │
│  │  📋 Recent Rides Table             │  │
│  │  - User Name                       │  │
│  │  - Driver Name                     │  │
│  │  - Status Badge                    │  │
│  │  - Fare Amount                     │  │
│  └────────────────────────────────────┘  │
└────────────────┬─────────────────────────┘
                 │
                 ▼
┌──────────────────────────────────────────┐
│         Rendered HTML + CSS               │
│  - Gradient background                    │
│  - Animated cards                         │
│  - Interactive table                      │
│  - Responsive layout                      │
└────────────────┬─────────────────────────┘
                 │
                 ▼
┌──────────────────────────────────────────┐
│         User's Browser                    │
│  Beautiful animated dashboard! ✨         │
└──────────────────────────────────────────┘
```

---

## 🔐 Data Validation Flow

```
┌──────────────┐
│ Client Input │
└──────┬───────┘
       │
       ▼
┌────────────────────────┐
│ @Valid Annotation      │
│ on @RequestBody        │
└──────┬─────────────────┘
       │
       ▼
┌────────────────────────┐
│ Bean Validation        │
│ - @NotBlank            │
│ - @Email               │
│ - @Min / @Max          │
│ - @NotNull             │
└──────┬─────────────────┘
       │
       ├─── Valid ─────┐
       │               ▼
       │        ┌────────────┐
       │        │ Process    │
       │        │ Request    │
       │        └────────────┘
       │
       └─── Invalid ───┐
                       ▼
              ┌─────────────────────┐
              │ GlobalException     │
              │ Handler             │
              └──────┬──────────────┘
                     │
                     ▼
              ┌─────────────────────┐
              │ HTTP 400            │
              │ Error Response      │
              │ { field: "msg" }    │
              └─────────────────────┘
```

---

## 🏃 Application Startup Flow

```
1. JVM Starts
   ↓
2. Load UberManagerApplication.main()
   ↓
3. SpringApplication.run()
   ↓
4. Component Scanning
   - Controllers
   - Services
   - Repositories
   ↓
5. Database Connection
   - Connect to MySQL
   - Create/Update schema
   - Load data.sql
   ↓
6. Initialize Beans
   - UserService
   - DriverService
   - RideService
   - PaymentService
   - RatingService
   ↓
7. Start Tomcat Server
   - Port 8080
   - Register endpoints
   ↓
8. Application Ready! 🚀
   - Dashboard: http://localhost:8080
   - APIs: http://localhost:8080/api
```

---

## 📦 Package Dependencies

```
UberManagerApplication
         │
         ├── controllers ──┐
         │                 │
         ├── services ─────┤
         │                 │
         ├── repositories ─┤
         │                 │
         ├── models ───────┤
         │                 │
         ├── dto ──────────┤
         │                 │
         ├── config ───────┤
         │                 │
         └── exception ────┘
                 │
                 ▼
         Spring Boot Auto-Config
                 │
                 ├── Spring Web
                 ├── Spring Data JPA
                 ├── Thymeleaf
                 ├── MySQL Driver
                 ├── Validation
                 └── Lombok
```

---

## 🎭 State Transition Diagram (Ride)

```
        ┌─────────────┐
   ┌───▶│  REQUESTED  │
   │    └──────┬──────┘
   │           │
   │           │ accept()
   │           ▼
   │    ┌─────────────┐
   ├───▶│  ACCEPTED   │
   │    └──────┬──────┘
   │           │
   │           │ start()
   │           ▼
   │    ┌─────────────┐
   ├───▶│ IN_PROGRESS │
   │    └──────┬──────┘
   │           │
   │           │ complete()
   │           ▼
   │    ┌─────────────┐
   │    │  COMPLETED  │◀─── Payment & Rating
   │    └─────────────┘
   │
   │    cancel()
   │    (from any state)
   │           │
   │           ▼
   └────┌─────────────┐
        │  CANCELLED  │
        └─────────────┘
```

---

## 🌟 Feature Architecture

```
┌────────────────────────────────────────┐
│         UBERMANAGER FEATURES           │
├────────────────────────────────────────┤
│                                        │
│  ┌──────────────────────────────────┐ │
│  │  User Management                 │ │
│  │  - Registration                  │ │
│  │  - Profile Management            │ │
│  │  - Ride History                  │ │
│  └──────────────────────────────────┘ │
│                                        │
│  ┌──────────────────────────────────┐ │
│  │  Driver Management               │ │
│  │  - Registration                  │ │
│  │  - Vehicle Info                  │ │
│  │  - Rating Tracking               │ │
│  └──────────────────────────────────┘ │
│                                        │
│  ┌──────────────────────────────────┐ │
│  │  Ride Operations                 │ │
│  │  - Request Ride                  │ │
│  │  - Accept/Start/Complete         │ │
│  │  - Fare Calculation              │ │
│  │  - Status Tracking               │ │
│  └──────────────────────────────────┘ │
│                                        │
│  ┌──────────────────────────────────┐ │
│  │  Payment System                  │ │
│  │  - Multiple Methods              │ │
│  │  - Status Tracking               │ │
│  │  - Revenue Analytics             │ │
│  └──────────────────────────────────┘ │
│                                        │
│  ┌──────────────────────────────────┐ │
│  │  Rating System                   │ │
│  │  - 1-5 Star Rating               │ │
│  │  - Text Reviews                  │ │
│  │  - Auto Average Update           │ │
│  └──────────────────────────────────┘ │
│                                        │
│  ┌──────────────────────────────────┐ │
│  │  Analytics Dashboard             │ │
│  │  - Real-time Statistics          │ │
│  │  - Revenue Tracking              │ │
│  │  - Performance Metrics           │ │
│  └──────────────────────────────────┘ │
│                                        │
└────────────────────────────────────────┘
```

---

**🚗 UberManager - Comprehensive System Architecture**
*Visual diagrams for understanding the complete system* 📊


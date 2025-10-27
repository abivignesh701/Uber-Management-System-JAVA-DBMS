# UberManager Test Script
# Run this script to test all endpoints

# Base URL
$baseUrl = "http://localhost:8080/api"

Write-Host "🚗 UberManager API Test Script" -ForegroundColor Green
Write-Host "================================" -ForegroundColor Green
Write-Host ""

# Test 1: Create User
Write-Host "📝 Test 1: Creating a new user..." -ForegroundColor Cyan
$userBody = @{
    name = "Test User"
    email = "testuser@example.com"
    contactNo = "+91 9999999999"
} | ConvertTo-Json

try {
    $user = Invoke-RestMethod -Uri "$baseUrl/users" -Method Post -Body $userBody -ContentType "application/json"
    Write-Host "✅ User created successfully! User ID: $($user.userId)" -ForegroundColor Green
    $userId = $user.userId
} catch {
    Write-Host "❌ Failed to create user: $_" -ForegroundColor Red
}

Write-Host ""

# Test 2: Create Driver
Write-Host "📝 Test 2: Creating a new driver..." -ForegroundColor Cyan
$driverBody = @{
    name = "Test Driver"
    email = "testdriver@example.com"
    contactNo = "+91 8888888888"
    vehicleNo = "KA-01-TEST-9999"
} | ConvertTo-Json

try {
    $driver = Invoke-RestMethod -Uri "$baseUrl/drivers" -Method Post -Body $driverBody -ContentType "application/json"
    Write-Host "✅ Driver created successfully! Driver ID: $($driver.driverId)" -ForegroundColor Green
    $driverId = $driver.driverId
} catch {
    Write-Host "❌ Failed to create driver: $_" -ForegroundColor Red
}

Write-Host ""

# Test 3: Get All Users
Write-Host "📝 Test 3: Fetching all users..." -ForegroundColor Cyan
try {
    $users = Invoke-RestMethod -Uri "$baseUrl/users" -Method Get
    Write-Host "✅ Found $($users.Count) users" -ForegroundColor Green
} catch {
    Write-Host "❌ Failed to fetch users: $_" -ForegroundColor Red
}

Write-Host ""

# Test 4: Get All Drivers
Write-Host "📝 Test 4: Fetching all drivers..." -ForegroundColor Cyan
try {
    $drivers = Invoke-RestMethod -Uri "$baseUrl/drivers" -Method Get
    Write-Host "✅ Found $($drivers.Count) drivers" -ForegroundColor Green
} catch {
    Write-Host "❌ Failed to fetch drivers: $_" -ForegroundColor Red
}

Write-Host ""

# Test 5: Request a Ride
if ($userId) {
    Write-Host "📝 Test 5: Requesting a ride..." -ForegroundColor Cyan
    $rideBody = @{
        user = @{
            userId = $userId
        }
        pickupLocation = "Koramangala, Bangalore"
        dropLocation = "Electronic City, Bangalore"
    } | ConvertTo-Json

    try {
        $ride = Invoke-RestMethod -Uri "$baseUrl/rides" -Method Post -Body $rideBody -ContentType "application/json"
        Write-Host "✅ Ride requested successfully! Ride ID: $($ride.rideId), Fare: ₹$($ride.fare)" -ForegroundColor Green
        $rideId = $ride.rideId
    } catch {
        Write-Host "❌ Failed to request ride: $_" -ForegroundColor Red
    }

    Write-Host ""
}

# Test 6: Accept Ride
if ($rideId -and $driverId) {
    Write-Host "📝 Test 6: Accepting the ride..." -ForegroundColor Cyan
    try {
        $acceptedRide = Invoke-RestMethod -Uri "$baseUrl/rides/$rideId/accept/$driverId" -Method Put
        Write-Host "✅ Ride accepted! Status: $($acceptedRide.status)" -ForegroundColor Green
    } catch {
        Write-Host "❌ Failed to accept ride: $_" -ForegroundColor Red
    }

    Write-Host ""
}

# Test 7: Start Ride
if ($rideId) {
    Write-Host "📝 Test 7: Starting the ride..." -ForegroundColor Cyan
    try {
        $startedRide = Invoke-RestMethod -Uri "$baseUrl/rides/$rideId/start" -Method Put
        Write-Host "✅ Ride started! Status: $($startedRide.status)" -ForegroundColor Green
    } catch {
        Write-Host "❌ Failed to start ride: $_" -ForegroundColor Red
    }

    Write-Host ""
}

# Test 8: Complete Ride
if ($rideId) {
    Write-Host "📝 Test 8: Completing the ride..." -ForegroundColor Cyan
    try {
        $completedRide = Invoke-RestMethod -Uri "$baseUrl/rides/$rideId/complete" -Method Put
        Write-Host "✅ Ride completed! Status: $($completedRide.status)" -ForegroundColor Green
    } catch {
        Write-Host "❌ Failed to complete ride: $_" -ForegroundColor Red
    }

    Write-Host ""
}

# Test 9: Make Payment
if ($rideId) {
    Write-Host "📝 Test 9: Processing payment..." -ForegroundColor Cyan
    $paymentBody = @{
        ride = @{
            rideId = $rideId
        }
        paymentMethod = "CARD"
        status = "PAID"
    } | ConvertTo-Json

    try {
        $payment = Invoke-RestMethod -Uri "$baseUrl/payments" -Method Post -Body $paymentBody -ContentType "application/json"
        Write-Host "✅ Payment processed! Amount: ₹$($payment.amount)" -ForegroundColor Green
    } catch {
        Write-Host "❌ Failed to process payment: $_" -ForegroundColor Red
    }

    Write-Host ""
}

# Test 10: Rate Driver
if ($rideId) {
    Write-Host "📝 Test 10: Rating the driver..." -ForegroundColor Cyan
    $ratingBody = @{
        ride = @{
            rideId = $rideId
        }
        rating = 5
        review = "Excellent service! Very professional driver."
    } | ConvertTo-Json

    try {
        $rating = Invoke-RestMethod -Uri "$baseUrl/ratings" -Method Post -Body $ratingBody -ContentType "application/json"
        Write-Host "✅ Rating submitted! Rating: $($rating.rating) stars" -ForegroundColor Green
    } catch {
        Write-Host "❌ Failed to submit rating: $_" -ForegroundColor Red
    }

    Write-Host ""
}

Write-Host "================================" -ForegroundColor Green
Write-Host "🎉 All tests completed!" -ForegroundColor Green
Write-Host "Dashboard: http://localhost:8080" -ForegroundColor Yellow
Write-Host "================================" -ForegroundColor Green

# GPS Location Tracking API Test Script
# Test the driver location tracking endpoints

Write-Host "=== UberManager GPS Location Tracking API Test ===" -ForegroundColor Cyan
Write-Host ""

# Base URL
$baseUrl = "http://localhost:8080"

# Test data
$driverId = 1
$location = @{
    latitude = 12.9716
    longitude = 77.5946
} | ConvertTo-Json

Write-Host "1. Testing PUT /api/drivers/$driverId/location - Update Driver Location" -ForegroundColor Yellow
Write-Host "Sending location: Bangalore (12.9716, 77.5946)" -ForegroundColor Gray

try {
    $response = Invoke-RestMethod -Uri "$baseUrl/api/drivers/$driverId/location" `
        -Method Put `
        -Body $location `
        -ContentType "application/json"
    
    Write-Host "✓ Success: $($response.message)" -ForegroundColor Green
} catch {
    Write-Host "✗ Error: $_" -ForegroundColor Red
}

Write-Host ""
Write-Host "2. Testing GET /api/drivers/$driverId/location - Get Driver Location" -ForegroundColor Yellow

try {
    $response = Invoke-RestMethod -Uri "$baseUrl/api/drivers/$driverId/location" `
        -Method Get
    
    Write-Host "✓ Driver Location Retrieved:" -ForegroundColor Green
    Write-Host "  Driver ID: $($response.driverId)" -ForegroundColor White
    Write-Host "  Driver Name: $($response.driverName)" -ForegroundColor White
    Write-Host "  Vehicle No: $($response.vehicleNo)" -ForegroundColor White
    Write-Host "  Latitude: $($response.latitude)" -ForegroundColor White
    Write-Host "  Longitude: $($response.longitude)" -ForegroundColor White
} catch {
    Write-Host "✗ Error: $_" -ForegroundColor Red
}

Write-Host ""
Write-Host "3. Testing with different location - Mumbai" -ForegroundColor Yellow

$mumbaiLocation = @{
    latitude = 19.0760
    longitude = 72.8777
} | ConvertTo-Json

try {
    $response = Invoke-RestMethod -Uri "$baseUrl/api/drivers/$driverId/location" `
        -Method Put `
        -Body $mumbaiLocation `
        -ContentType "application/json"
    
    Write-Host "✓ Success: $($response.message)" -ForegroundColor Green
    
    # Verify the update
    $response = Invoke-RestMethod -Uri "$baseUrl/api/drivers/$driverId/location" -Method Get
    Write-Host "  Updated Location: ($($response.latitude), $($response.longitude))" -ForegroundColor White
} catch {
    Write-Host "✗ Error: $_" -ForegroundColor Red
}

Write-Host ""
Write-Host "4. Testing validation - Invalid coordinates" -ForegroundColor Yellow

$invalidLocation = @{
    latitude = 100.0  # Invalid: > 90
    longitude = 77.5946
} | ConvertTo-Json

try {
    $response = Invoke-RestMethod -Uri "$baseUrl/api/drivers/$driverId/location" `
        -Method Put `
        -Body $invalidLocation `
        -ContentType "application/json"
    
    Write-Host "✗ Validation failed - should have rejected invalid latitude" -ForegroundColor Red
} catch {
    Write-Host "✓ Validation working: Invalid latitude rejected" -ForegroundColor Green
}

Write-Host ""
Write-Host "5. Testing with non-existent driver" -ForegroundColor Yellow

try {
    $response = Invoke-RestMethod -Uri "$baseUrl/api/drivers/999/location" -Method Get
    Write-Host "✗ Should have returned 404" -ForegroundColor Red
} catch {
    Write-Host "✓ 404 error correctly returned for non-existent driver" -ForegroundColor Green
}

Write-Host ""
Write-Host "=== Test Complete ===" -ForegroundColor Cyan

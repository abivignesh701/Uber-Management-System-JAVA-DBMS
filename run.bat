@echo off
echo ========================================
echo  UberManager - Ride Management System
echo ========================================
echo.

echo Checking prerequisites...
echo.

REM Check Java
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo [ERROR] Java is not installed or not in PATH
    echo Please install Java 17 or higher
    pause
    exit /b 1
)
echo [OK] Java found

REM Check Maven
mvn -version >nul 2>&1
if %errorlevel% neq 0 (
    echo [ERROR] Maven is not installed or not in PATH
    echo Please install Maven 3.6 or higher
    pause
    exit /b 1
)
echo [OK] Maven found

echo.
echo Building the application...
echo.

call mvn clean install -DskipTests

if %errorlevel% neq 0 (
    echo [ERROR] Build failed
    pause
    exit /b 1
)

echo.
echo ========================================
echo  Starting UberManager Application
echo ========================================
echo.
echo Dashboard will be available at: http://localhost:8080
echo Press Ctrl+C to stop the server
echo.

call mvn spring-boot:run

pause

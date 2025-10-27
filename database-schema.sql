-- =============================================
-- UberManager Database Schema
-- MySQL Database for Ride Management System
-- =============================================

-- Drop existing database if needed (CAUTION: This will delete all data)
-- DROP DATABASE IF EXISTS ubermanager;

-- Create database
CREATE DATABASE IF NOT EXISTS ubermanager;
USE ubermanager;

-- =============================================
-- TABLE: users
-- Stores information about riders/customers
-- =============================================
CREATE TABLE IF NOT EXISTS users (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    contact_no VARCHAR(20) NOT NULL,
    join_date DATE,
    INDEX idx_email (email),
    INDEX idx_join_date (join_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- =============================================
-- TABLE: drivers
-- Stores information about drivers
-- =============================================
CREATE TABLE IF NOT EXISTS drivers (
    driver_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    contact_no VARCHAR(20) NOT NULL,
    vehicle_no VARCHAR(50) NOT NULL,
    rating_avg DOUBLE DEFAULT 0.0,
    join_date DATE,
    INDEX idx_email (email),
    INDEX idx_rating (rating_avg),
    INDEX idx_join_date (join_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- =============================================
-- TABLE: rides
-- Stores ride request and journey information
-- =============================================
CREATE TABLE IF NOT EXISTS rides (
    ride_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    driver_id BIGINT,
    pickup_location VARCHAR(255) NOT NULL,
    drop_location VARCHAR(255) NOT NULL,
    request_time DATETIME,
    start_time DATETIME,
    end_time DATETIME,
    fare DOUBLE,
    status VARCHAR(20) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (driver_id) REFERENCES drivers(driver_id) ON DELETE SET NULL,
    INDEX idx_user (user_id),
    INDEX idx_driver (driver_id),
    INDEX idx_status (status),
    INDEX idx_request_time (request_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- =============================================
-- TABLE: payments
-- Stores payment transaction information
-- =============================================
CREATE TABLE IF NOT EXISTS payments (
    payment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    ride_id BIGINT NOT NULL UNIQUE,
    amount DOUBLE NOT NULL,
    payment_method VARCHAR(20) NOT NULL,
    payment_date DATETIME,
    status VARCHAR(20) NOT NULL,
    FOREIGN KEY (ride_id) REFERENCES rides(ride_id) ON DELETE CASCADE,
    INDEX idx_ride (ride_id),
    INDEX idx_status (status),
    INDEX idx_payment_date (payment_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- =============================================
-- TABLE: ratings
-- Stores driver ratings and reviews
-- =============================================
CREATE TABLE IF NOT EXISTS ratings (
    rating_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    ride_id BIGINT NOT NULL UNIQUE,
    user_id BIGINT NOT NULL,
    driver_id BIGINT NOT NULL,
    rating INT NOT NULL CHECK (rating >= 1 AND rating <= 5),
    review TEXT,
    rating_date DATETIME,
    FOREIGN KEY (ride_id) REFERENCES rides(ride_id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (driver_id) REFERENCES drivers(driver_id) ON DELETE CASCADE,
    INDEX idx_ride (ride_id),
    INDEX idx_user (user_id),
    INDEX idx_driver (driver_id),
    INDEX idx_rating (rating),
    INDEX idx_rating_date (rating_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- =============================================
-- VIEWS: Useful analytical views
-- =============================================

-- View: Active Rides
CREATE OR REPLACE VIEW active_rides AS
SELECT 
    r.ride_id,
    u.name AS user_name,
    d.name AS driver_name,
    r.pickup_location,
    r.drop_location,
    r.fare,
    r.status,
    r.request_time
FROM rides r
JOIN users u ON r.user_id = u.user_id
LEFT JOIN drivers d ON r.driver_id = d.driver_id
WHERE r.status IN ('REQUESTED', 'ACCEPTED', 'IN_PROGRESS');

-- View: Revenue Summary
CREATE OR REPLACE VIEW revenue_summary AS
SELECT 
    DATE(p.payment_date) AS payment_date,
    COUNT(*) AS total_payments,
    SUM(p.amount) AS total_revenue,
    AVG(p.amount) AS average_fare
FROM payments p
WHERE p.status = 'PAID'
GROUP BY DATE(p.payment_date);

-- View: Driver Performance
CREATE OR REPLACE VIEW driver_performance AS
SELECT 
    d.driver_id,
    d.name,
    d.vehicle_no,
    d.rating_avg,
    COUNT(r.ride_id) AS total_rides,
    COUNT(CASE WHEN r.status = 'COMPLETED' THEN 1 END) AS completed_rides,
    SUM(CASE WHEN r.status = 'COMPLETED' THEN r.fare ELSE 0 END) AS total_earnings
FROM drivers d
LEFT JOIN rides r ON d.driver_id = r.driver_id
GROUP BY d.driver_id, d.name, d.vehicle_no, d.rating_avg;

-- =============================================
-- STORED PROCEDURES
-- =============================================

-- Procedure: Update Driver Average Rating
DELIMITER //
CREATE PROCEDURE update_driver_rating(IN p_driver_id BIGINT)
BEGIN
    UPDATE drivers 
    SET rating_avg = (
        SELECT COALESCE(AVG(rating), 0.0) 
        FROM ratings 
        WHERE driver_id = p_driver_id
    )
    WHERE driver_id = p_driver_id;
END //
DELIMITER ;

-- =============================================
-- TRIGGERS
-- =============================================

-- Trigger: Auto-update driver rating after rating insert
DELIMITER //
CREATE TRIGGER after_rating_insert 
AFTER INSERT ON ratings
FOR EACH ROW
BEGIN
    CALL update_driver_rating(NEW.driver_id);
END //
DELIMITER ;

-- Trigger: Auto-update driver rating after rating update
DELIMITER //
CREATE TRIGGER after_rating_update 
AFTER UPDATE ON ratings
FOR EACH ROW
BEGIN
    CALL update_driver_rating(NEW.driver_id);
END //
DELIMITER ;

-- =============================================
-- INDEXES FOR PERFORMANCE
-- =============================================
-- (Already created in table definitions above)

-- =============================================
-- SAMPLE DATA (Optional - Comment out if not needed)
-- =============================================

-- Sample Users
INSERT INTO users (name, email, contact_no, join_date) VALUES
('John Doe', 'john.doe@example.com', '+91 9876543210', '2024-01-15'),
('Jane Smith', 'jane.smith@example.com', '+91 9876543211', '2024-01-20'),
('Robert Johnson', 'robert.j@example.com', '+91 9876543212', '2024-02-01');

-- Sample Drivers
INSERT INTO drivers (name, email, contact_no, vehicle_no, rating_avg, join_date) VALUES
('Rajesh Kumar', 'rajesh.kumar@example.com', '+91 9988776655', 'KA-01-AB-1234', 4.5, '2024-01-10'),
('Amit Sharma', 'amit.sharma@example.com', '+91 9988776656', 'KA-01-AB-2345', 4.8, '2024-01-12');

-- =============================================
-- VERIFICATION QUERIES
-- =============================================

-- Check table creation
SELECT 'Tables created successfully!' AS Status;

-- Show all tables
SHOW TABLES;

-- Show table structures
DESCRIBE users;
DESCRIBE drivers;
DESCRIBE rides;
DESCRIBE payments;
DESCRIBE ratings;

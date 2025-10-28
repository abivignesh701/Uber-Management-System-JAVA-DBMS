-- UberManager Sample Data

-- Clear existing data first (in correct order due to foreign key constraints)
DELETE FROM ratings;
DELETE FROM payments;
DELETE FROM rides;
DELETE FROM drivers;
DELETE FROM users;

-- Reset auto-increment counters
ALTER TABLE users AUTO_INCREMENT = 1;
ALTER TABLE drivers AUTO_INCREMENT = 1;
ALTER TABLE rides AUTO_INCREMENT = 1;
ALTER TABLE payments AUTO_INCREMENT = 1;
ALTER TABLE ratings AUTO_INCREMENT = 1;

-- Insert sample users (password is 'password123' for all - BCrypt hashed)
INSERT INTO users (name, email, contact_no, password, role, join_date) VALUES
('John Doe', 'john.doe@example.com', '+91 9876543210', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ROLE_USER', '2024-01-15'),
('Jane Smith', 'jane.smith@example.com', '+91 9876543211', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ROLE_USER', '2024-01-20'),
('Robert Johnson', 'robert.j@example.com', '+91 9876543212', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ROLE_USER', '2024-02-01'),
('Emily Davis', 'emily.d@example.com', '+91 9876543213', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ROLE_USER', '2024-02-10'),
('Michael Brown', 'michael.b@example.com', '+91 9876543214', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ROLE_USER', '2024-02-15'),
('Sarah Wilson', 'sarah.wilson@example.com', '+91 9876543215', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ROLE_USER', '2024-03-01'),
('David Lee', 'david.lee@example.com', '+91 9876543216', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ROLE_USER', '2024-03-05'),
('Lisa Anderson', 'lisa.anderson@example.com', '+91 9876543217', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ROLE_USER', '2024-03-10'),
('James Taylor', 'james.taylor@example.com', '+91 9876543218', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ROLE_USER', '2024-03-15'),
('Maria Garcia', 'maria.garcia@example.com', '+91 9876543219', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ROLE_USER', '2024-03-20');

-- Insert sample drivers (password is 'password123' for all - BCrypt hashed)
INSERT INTO drivers (name, email, contact_no, vehicle_no, password, role, rating_avg, join_date) VALUES
('Rajesh Kumar', 'rajesh.kumar@example.com', '+91 9988776655', 'DL01AB1234', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ROLE_DRIVER', 4.8, '2023-06-01'),
('Amit Sharma', 'amit.sharma@example.com', '+91 9988776656', 'DL01AB1235', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ROLE_DRIVER', 4.9, '2023-06-15'),
('Priya Singh', 'priya.singh@example.com', '+91 9988776657', 'DL01AB1236', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ROLE_DRIVER', 4.7, '2023-07-01'),
('Vijay Verma', 'vijay.verma@example.com', '+91 9988776658', 'DL01AB1237', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ROLE_DRIVER', 4.6, '2023-07-15'),
('Sanjay Patel', 'sanjay.patel@example.com', '+91 9988776659', 'DL01AB1238', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ROLE_DRIVER', 4.9, '2023-08-01'),
('Neha Gupta', 'neha.gupta@example.com', '+91 9988776660', 'DL01AB1239', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ROLE_DRIVER', 4.8, '2023-08-15'),
('Rahul Desai', 'rahul.desai@example.com', '+91 9988776661', 'DL01AB1240', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ROLE_DRIVER', 4.5, '2023-09-01'),
('Kavita Reddy', 'kavita.reddy@example.com', '+91 9988776662', 'DL01AB1241', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ROLE_DRIVER', 4.7, '2023-09-15'),
('Suresh Nair', 'suresh.nair@example.com', '+91 9988776663', 'DL01AB1242', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ROLE_DRIVER', 4.9, '2023-10-01'),
('Anita Joshi', 'anita.joshi@example.com', '+91 9988776664', 'DL01AB1243', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ROLE_DRIVER', 4.8, '2023-10-15');

-- Insert sample rides
INSERT INTO rides (user_id, driver_id, pickup_location, drop_location, request_time, start_time, end_time, fare, status) VALUES
(1, 1, 'Koramangala, Bangalore', 'Electronic City, Bangalore', '2024-03-01 09:00:00', '2024-03-01 09:10:00', '2024-03-01 09:45:00', 350.00, 'COMPLETED'),
(2, 2, 'MG Road, Bangalore', 'Whitefield, Bangalore', '2024-03-01 10:00:00', '2024-03-01 10:15:00', '2024-03-01 11:00:00', 450.00, 'COMPLETED'),
(3, 3, 'Indiranagar, Bangalore', 'Hebbal, Bangalore', '2024-03-02 08:30:00', '2024-03-02 08:40:00', '2024-03-02 09:15:00', 280.00, 'COMPLETED'),
(4, 4, 'BTM Layout, Bangalore', 'Kempegowda Airport', '2024-03-02 14:00:00', '2024-03-02 14:10:00', '2024-03-02 15:00:00', 650.00, 'COMPLETED'),
(5, 5, 'Jayanagar, Bangalore', 'HSR Layout, Bangalore', '2024-03-03 11:00:00', '2024-03-03 11:10:00', '2024-03-03 11:40:00', 200.00, 'COMPLETED'),
(6, 6, 'Brigade Road, Bangalore', 'Marathahalli, Bangalore', '2024-03-03 12:00:00', '2024-03-03 12:10:00', '2024-03-03 12:50:00', 320.00, 'COMPLETED'),
(7, 7, 'Yelahanka, Bangalore', 'Banashankari, Bangalore', '2024-03-04 08:00:00', '2024-03-04 08:15:00', '2024-03-04 09:00:00', 420.00, 'COMPLETED'),
(8, 8, 'Malleshwaram, Bangalore', 'Silk Board, Bangalore', '2024-03-04 10:00:00', '2024-03-04 10:10:00', '2024-03-04 10:45:00', 380.00, 'COMPLETED'),
(9, 1, 'JP Nagar, Bangalore', 'Bellandur, Bangalore', '2024-03-05 07:30:00', '2024-03-05 07:40:00', '2024-03-05 08:25:00', 390.00, 'COMPLETED'),
(10, 2, 'Rajajinagar, Bangalore', 'Sarjapur Road, Bangalore', '2024-03-05 09:00:00', '2024-03-05 09:10:00', '2024-03-05 09:55:00', 410.00, 'COMPLETED'),
(1, 3, 'Electronic City, Bangalore', 'Koramangala, Bangalore', '2024-10-27 15:00:00', '2024-10-27 15:10:00', NULL, 350.00, 'IN_PROGRESS'),
(2, 4, 'Whitefield, Bangalore', 'MG Road, Bangalore', '2024-10-27 16:00:00', NULL, NULL, 450.00, 'ACCEPTED'),
(3, NULL, 'Indiranagar, Bangalore', 'Airport, Bangalore', '2024-10-27 17:00:00', NULL, NULL, 600.00, 'REQUESTED');

-- Insert sample payments
INSERT INTO payments (ride_id, amount, payment_method, payment_date, status) VALUES
(1, 350.00, 'CARD', '2024-03-01 09:50:00', 'PAID'),
(2, 450.00, 'WALLET', '2024-03-01 11:05:00', 'PAID'),
(3, 280.00, 'CASH', '2024-03-02 09:20:00', 'PAID'),
(4, 650.00, 'CARD', '2024-03-02 15:05:00', 'PAID'),
(5, 200.00, 'WALLET', '2024-03-03 11:45:00', 'PAID'),
(6, 320.00, 'CARD', '2024-03-03 12:55:00', 'PAID'),
(7, 420.00, 'WALLET', '2024-03-04 09:05:00', 'PAID'),
(8, 380.00, 'CASH', '2024-03-04 10:50:00', 'PAID'),
(9, 390.00, 'WALLET', '2024-03-05 08:30:00', 'PAID'),
(10, 410.00, 'CARD', '2024-03-05 10:00:00', 'PAID'),
(11, 350.00, 'WALLET', NULL, 'PENDING'),
(12, 450.00, 'CARD', NULL, 'PENDING');

-- Insert sample ratings
INSERT INTO ratings (ride_id, user_id, driver_id, rating, review, rating_date) VALUES
(1, 1, 1, 5, 'Excellent service! Very professional driver.', '2024-03-01 10:00:00'),
(2, 2, 2, 5, 'Great experience. Highly recommended!', '2024-03-01 11:30:00'),
(3, 3, 3, 4, 'Good service, but car could be cleaner.', '2024-03-02 09:30:00'),
(4, 4, 4, 5, 'Amazing driver! Very punctual and safe.', '2024-03-02 15:15:00'),
(5, 5, 5, 5, 'Fantastic ride! Will book again.', '2024-03-03 12:00:00'),
(6, 6, 6, 5, 'Nice driver, smooth journey.', '2024-03-03 13:10:00'),
(7, 7, 7, 4, 'Good experience overall.', '2024-03-04 09:20:00'),
(8, 8, 8, 5, 'Best ride ever! Highly professional.', '2024-03-04 11:00:00'),
(9, 9, 1, 5, 'Excellent as always!', '2024-03-05 08:45:00'),
(10, 10, 2, 5, 'Very courteous and helpful driver.', '2024-03-05 10:15:00');

-- SQL Script for Attendance Table
-- This table stores student attendance information for Part c

CREATE TABLE IF NOT EXISTS Attendance (
    AttendanceID INT PRIMARY KEY AUTO_INCREMENT,
    StudentID VARCHAR(50) NOT NULL,
    AttendanceDate DATE NOT NULL,
    Status VARCHAR(20) NOT NULL,
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Sample data for testing
INSERT INTO Attendance (StudentID, AttendanceDate, Status) VALUES
('S001', '2025-11-01', 'Present'),
('S002', '2025-11-01', 'Absent'),
('S003', '2025-11-01', 'Present'),
('S004', '2025-11-01', 'Present'),
('S005', '2025-11-01', 'Absent');

-- Query to view all attendance records
-- SELECT * FROM Attendance;

-- Query to view attendance by date
-- SELECT * FROM Attendance WHERE AttendanceDate = ?;

-- Query to view attendance by student ID
-- SELECT * FROM Attendance WHERE StudentID = ?;

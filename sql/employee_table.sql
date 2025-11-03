-- SQL Script for Employee Table
-- This table stores employee information for Part b

CREATE TABLE IF NOT EXISTS Employee (
    EmpID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(100) NOT NULL,
    Salary DECIMAL(10, 2) NOT NULL
);

-- Sample data for testing
INSERT INTO Employee (Name, Salary) VALUES
('John Doe', 50000.00),
('Jane Smith', 60000.00),
('Michael Johnson', 55000.00),
('Emily Davis', 52000.00),
('David Wilson', 58000.00);

-- Query to view all employees
-- SELECT * FROM Employee;

-- Query to search by EmpID
-- SELECT * FROM Employee WHERE EmpID = ?;
